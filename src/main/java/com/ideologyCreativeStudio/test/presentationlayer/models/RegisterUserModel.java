package com.ideologyCreativeStudio.test.presentationlayer.models;

import com.ideologyCreativeStudio.test.businesslayer.dto.user.RoleEntityDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.user.RolesResponseDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record RegisterUserModel(
        @NotBlank(message = "Il nome non può essere vuoto")
        String firstName,

        @NotBlank(message = "Il cognome non può essere vuoto")
        String lastName,

        @NotBlank(message = "Lo username non può contenere spazi vuoti")
        @Size(max = 50, message = "Lo username può contenere al massimo 50 caratteri")
        @Pattern(regexp = "^[\\S]+$", message = "Lo username non può contenere spazi vuoti")
        String username,

        @Email(message = "Inserisci una email valida")
        String email,

        @NotBlank(message = "La password non può contenere solo spazi vuoti")
        @Size(max = 125, message = "La password può contenere al massimo 125 caratteri")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
                message = "Password non valida: deve contenere almeno una maiuscola, una minuscola, un numero, un carattere speciale, e tra 8 e 20 caratteri")
        String password,

        List<String> role
) {
}
