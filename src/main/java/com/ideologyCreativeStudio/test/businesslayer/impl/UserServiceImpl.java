package com.ideologyCreativeStudio.test.businesslayer.impl;

import com.ideologyCreativeStudio.test.businesslayer.dto.user.LoginResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RegisterUserDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RegisteredUserDTO;
import com.ideologyCreativeStudio.test.businesslayer.interfaces.entities.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {


    @Override
    public RegisteredUserDTO register(RegisterUserDTO user) {
        return null;
    }

    @Override
    public Optional<LoginResponseDTO> login(String username, String password) {
        return Optional.empty();
    }

    @Override
    public RegisteredUserDTO getById(long id) {
        return null;
    }

    @Override
    public Page<RegisteredUserDTO> getAll(Pageable p) {
        return null;
    }

    @Override
    public List<RegisteredUserDTO> searchUsersByFirstName(String firstName) {
        return null;
    }

    @Override
    public RegisteredUserDTO update(Long id, RegisteredUserDTO userDto) {
        return null;
    }

    @Override
    public RegisteredUserDTO delete(Long id) {
        return null;
    }

    @Override
    public RegisteredUserDTO addRole(Long id, String role) {
        return null;
    }

    @Override
    public RegisteredUserDTO removeRole(Long id, String role) {
        return null;
    }

    @Override
    public RegisteredUserDTO saveAttachment(long id, MultipartFile file) throws IOException {
        return null;
    }
}
