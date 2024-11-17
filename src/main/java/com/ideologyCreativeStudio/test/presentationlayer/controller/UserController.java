package com.ideologyCreativeStudio.test.presentationlayer.controller;

import com.ideologyCreativeStudio.test.businesslayer.dto.user.LoginResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RegisterUserDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RegisteredUserDTO;
import com.ideologyCreativeStudio.test.businesslayer.impl.UserServiceImpl;
import com.ideologyCreativeStudio.test.businesslayer.interfaces.entities.UserService;
import com.ideologyCreativeStudio.test.presentationlayer.exceptions.ApiValidationException;
import com.ideologyCreativeStudio.test.presentationlayer.exceptions.DisabledUserException;
import com.ideologyCreativeStudio.test.presentationlayer.exceptions.NotFoundException;
import com.ideologyCreativeStudio.test.presentationlayer.models.LoginModel;
import com.ideologyCreativeStudio.test.presentationlayer.models.RegisterUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;


    @GetMapping
    public ResponseEntity<Page<RegisteredUserDTO>> getUsers(Pageable pageable) {
        Page<RegisteredUserDTO> allUsers = userServiceImpl.getAll(pageable);
        System.out.println(allUsers);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Totale", String.valueOf(allUsers.getTotalElements()));
        return new ResponseEntity<>(allUsers, headers, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<RegisteredUserDTO> getUser(@PathVariable Long id) {
        try {
            RegisteredUserDTO userDTO = userServiceImpl.getById(id);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<RegisteredUserDTO> register(@RequestBody @Validated RegisterUserModel model, BindingResult validator) {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }

             RegisteredUserDTO registeredUser = userServiceImpl.register(
                RegisterUserDTO.builder()
                        .withFirstName(model.firstName())
                        .withLastName(model.lastName())
                        .withUsername(model.username())
                        .withEmail(model.email())
                        .withPassword(model.password())
                        .withRole(model.role())
                        .build());

        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Validated LoginModel model, BindingResult validator) {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }

        Optional<LoginResponseDTO> loginResponse = userServiceImpl.login(model.username(), model.password());

        if (loginResponse.isPresent()) {
            LoginResponseDTO responseDTO = loginResponse.get();

            // Check if user is enabled before returning the response
            if (!responseDTO.getUser().isEnabled()) {
                throw new DisabledUserException(responseDTO.getUser().getUsername());
            }

            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @GetMapping("/search")
    public ResponseEntity<List<RegisteredUserDTO>> searchUsers(@RequestParam String firstName) {
        List<RegisteredUserDTO> users = userServiceImpl.searchUsersByFirstName(firstName);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<RegisteredUserDTO> update(@PathVariable Long id, @RequestBody RegisteredUserDTO userDto) {
        var updatedUser = userServiceImpl.update(id, userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PatchMapping("{id}/add-role")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<RegisteredUserDTO> addUserRole(@PathVariable Long id, @RequestParam("role") String role) {
        try {
            RegisteredUserDTO updatedUser = userServiceImpl.addRole(id, role);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("{id}/remove-role")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<RegisteredUserDTO> removeUserRole(@PathVariable Long id, @RequestParam("role") String role) {
        try {
            RegisteredUserDTO updatedUser = userServiceImpl.removeRole(id, role);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RegisteredUserDTO> deleteUser(@PathVariable Long id) {
        try {
            userServiceImpl.delete(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PatchMapping("{id}/profile-image")
//    public ResponseEntity<RegisteredUserDTO> uploadProfileImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
//        try {
//            RegisteredUserDTO updatedUser = userService.(id, file);
//            return ResponseEntity.ok(updatedUser);
//        } catch (IOException e) {
//
//            return ResponseEntity.status(500).body(null);
//        } catch (NotFoundException e) {
//
//            return ResponseEntity.status(404).body(null);
//        }
//    }
}
