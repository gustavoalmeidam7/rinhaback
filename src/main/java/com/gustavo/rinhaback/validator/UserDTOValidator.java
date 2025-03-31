package com.gustavo.rinhaback.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.gustavo.rinhaback.domain.user.UserDTO;

@Component
public class UserDTOValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;

        if (userDTO.getApelido() == null || userDTO.getApelido().isBlank()) {
            errors.rejectValue("Apelido", "O campo 'apelido' é obrigatorio.");
        }

        if (userDTO.getNome() == null || userDTO.getNome().isBlank()) {
            errors.rejectValue("Nome", "O campo 'nome' é obrigatorio.");
        }

        if (userDTO.getNascimento() == null) {
            errors.rejectValue("Nascimento", "O campo 'nascimento' é obrigatorio.");
        }
    }
    
}
