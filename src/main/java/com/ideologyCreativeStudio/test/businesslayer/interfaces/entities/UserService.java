package com.ideologyCreativeStudio.test.businesslayer.interfaces.entities;

import com.ideologyCreativeStudio.test.businesslayer.dto.user.LoginResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RegisterUserDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RegisteredUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    RegisteredUserDTO register(RegisterUserDTO user);

    Optional<LoginResponseDTO> login(String username, String password);

    RegisteredUserDTO getById(long id);

    Page<RegisteredUserDTO> getAll(Pageable p);

    List<RegisteredUserDTO> searchUsersByFirstName(String firstName);

    RegisteredUserDTO update(Long id, RegisteredUserDTO userDto);

    RegisteredUserDTO delete(Long id);

    RegisteredUserDTO addRole(Long id, String role);
    RegisteredUserDTO removeRole(Long id, String role);

    RegisteredUserDTO saveAttachment(long id, MultipartFile file) throws IOException;
}
