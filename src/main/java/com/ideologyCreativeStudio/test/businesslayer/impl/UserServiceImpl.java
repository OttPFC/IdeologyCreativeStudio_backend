package com.ideologyCreativeStudio.test.businesslayer.impl;

import com.ideologyCreativeStudio.test.businesslayer.dto.user.LoginResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RegisterUserDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RegisteredUserDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RolesResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.interfaces.entities.UserService;
import com.ideologyCreativeStudio.test.businesslayer.interfaces.generics.Mapper;
import com.ideologyCreativeStudio.test.config.EmailConfig;
import com.ideologyCreativeStudio.test.config.JwtUtils;
import com.ideologyCreativeStudio.test.datalayer.entities.Role;
import com.ideologyCreativeStudio.test.datalayer.entities.User;
import com.ideologyCreativeStudio.test.datalayer.repositories.RoleRepository;
import com.ideologyCreativeStudio.test.datalayer.repositories.UserRepository;
import com.ideologyCreativeStudio.test.presentationlayer.exceptions.InvalidLoginException;
import com.ideologyCreativeStudio.test.presentationlayer.exceptions.NotFoundException;
import com.ideologyCreativeStudio.test.presentationlayer.exceptions.duplicated.DuplicateEmailException;
import com.ideologyCreativeStudio.test.presentationlayer.exceptions.duplicated.DuplicateUsernameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository usersRepository;

    @Autowired
    private JwtUtils jwt;

    @Autowired
    private AuthenticationManager auth;
    @Autowired
    private Pageable defaultPageable;
    @Autowired
    Mapper<RegisterUserDTO, User> mapEntity;

    @Autowired
    Mapper<User, RegisteredUserDTO> mapRegisteredUser;

    @Autowired
    Mapper<User, LoginResponseDTO> mapLogin;

    @Autowired
    private RoleRepository roles;

    @Autowired
    Mapper<List<RolesResponseDTO>, List<Role>> mapRoleDTOListToEntityList;

    @Autowired
    EmailConfig emailConfig;

    @Override
    @Transactional
    public RegisteredUserDTO register(RegisterUserDTO newUser) {
        var emailDuplicated = usersRepository.findByEmail(newUser.getEmail());
        var usernameDuplicated = usersRepository.findOneByUsername(newUser.getUsername());

        if (emailDuplicated.isPresent()) {
            throw new DuplicateEmailException(newUser.getEmail());
        } else if (usernameDuplicated.isPresent()) {
            throw new DuplicateUsernameException(newUser.getUsername());
        } else {
            try {
                var userEntity = mapEntity.map(newUser);
                log.info("User to be registered: {}", newUser);
                System.out.println(newUser);
                var encryptedPassword  = encoder.encode(newUser.getPassword());
                log.info("Password encrypted: {}", encryptedPassword );

                userEntity.setPassword(encryptedPassword );
                log.info("Password encrypted 2: {}", encryptedPassword );
                var totalUsers = this.getAll(defaultPageable);

                log.info("User" + userEntity);
                log.info("User" + totalUsers);
                if (userEntity.getRoles() == null) {
                    log.debug("Initializing roles as userEntity.getRoles() was null");
                    userEntity.setRoles(new ArrayList<>());
                }

                if (totalUsers.getTotalElements() == 0) {
                    userEntity.getRoles().add(
                            Role.builder()
                                    .withRoleType("ADMIN")
                                    .build()
                    );
                } else {
                    userEntity.getRoles().add(
                            Role.builder()
                                    .withRoleType("USER")
                                    .build()
                    );
                }
                userEntity.setEnabled(true);
                log.debug("Saving user: {}", userEntity);
                var savedUser = mapRegisteredUser.map(usersRepository.save(userEntity));

                emailConfig.sendMail(newUser.getEmail(), "Welcome!", "Thank you for registering!");
                return savedUser;
            } catch (Exception e) {
                log.error(String.format("Exception saving user %s", usersRepository), e);
                throw new RuntimeException();
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LoginResponseDTO> login(String username, String password) {
        try {
            var authResult  = auth.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authResult );
            var dto = mapLogin.map(usersRepository.findOneByUsername(username).orElse(null));
            dto.setAccessToken(jwt.generateAccessToken(authResult));
            return Optional.of(dto);
        } catch (NoSuchElementException e) {
            log.error("User not found", e);
            throw new InvalidLoginException(username, password);
        } catch (AuthenticationException e) {
            log.error("Authentication failed", e);
            throw new InvalidLoginException(username, password);
        }
    }

    @Override
    public RegisteredUserDTO getById(long id) {
        return mapRegisteredUser.map(usersRepository.findById(id).orElseThrow(()-> new NotFoundException(id)));
    }

    @Override
    public Page<RegisteredUserDTO> getAll(Pageable pageable) {
        return usersRepository.findAll(pageable).map(mapRegisteredUser::map);
    }

    @Override
    public List<RegisteredUserDTO> searchUsersByFirstName(String firstName) {
        List<User> users = usersRepository.findByFirstNameContainingIgnoreCase(firstName);
        return users.stream()
                .map(mapRegisteredUser::map)
                .collect(Collectors.toList());
    }

    @Override
    public RegisteredUserDTO update(Long id, RegisteredUserDTO userDto) {
        User user = usersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        if (userDto.getFirstName() != null) user.setFirstName(userDto.getFirstName());
        if (userDto.getLastName() != null) user.setLastName(userDto.getLastName());
        if (userDto.getUsername() != null) user.setUsername(userDto.getUsername());
        if (userDto.getRoles() != null) user.setRoles(mapRoleDTOListToEntityList.map(userDto.getRoles()));
        user.setEnabled(userDto.isEnabled());

        User updatedUser = usersRepository.save(user);

        return mapRegisteredUser.map(updatedUser);
    }

    @Override
    public RegisteredUserDTO delete(Long id) {
        try {
            var u = usersRepository.findById(id).orElseThrow();
            usersRepository.delete(u);
            return mapRegisteredUser.map(u);
        } catch (NoSuchElementException e) {
            log.error(String.format("Cannot find user with id = %s", id), e);
            throw new RuntimeException("Cannot find user...");
        } catch (Exception e) {
            log.error(String.format("Error deleting user with id = %s", id), e);
            throw new RuntimeException();
        }
    }

    @Override
    public RegisteredUserDTO addRole(Long id, String role) {
        var roleEntity = roles.findOneByRoleType(role);
        var user = usersRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        if (roleEntity.isEmpty()) {
            throw new RuntimeException("Il ruolo che stai tentando di aggiungere non esiste");
        } else if (user.getRoles().contains(roleEntity.get())) {
            throw new RuntimeException("Il ruolo che stai tentando di aggiungere è gia presente");
        } else {
            user.getRoles().add(roleEntity.get());
            return mapRegisteredUser.map(usersRepository.save(user));

        }
    }

    @Override
    public RegisteredUserDTO removeRole(Long id, String role) {
        var roleEntity = roles.findOneByRoleType(role);
        var user = usersRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        if (roleEntity.isEmpty()) {
            throw new RuntimeException("Il ruolo che stai tentando di rimuovere non esiste");
        }
        if (!user.getRoles().contains(roleEntity.get())) {
            throw new RuntimeException("Il ruolo che stai tentando di rimuovere non è presente");
        }
        if (Objects.equals(role, "ADMIN")) {
            var adminUsers = usersRepository.findByRoles_RoleType(role);
            if (adminUsers.size() == 1) {
                throw new RuntimeException("Deve esistere almeno un admin sul database");
            }
        }
        if (user.getRoles().size() == 1 && user.getRoles().contains(roleEntity.get())) {
            throw new RuntimeException("Un utente deve avere almeno un ruolo");
        }
        user.getRoles().remove(roleEntity.get());
        return mapRegisteredUser.map(usersRepository.save(user));
    }

    @Override
    public RegisteredUserDTO saveAttachment(long id, MultipartFile file) throws IOException {
        return null;
    }
}
