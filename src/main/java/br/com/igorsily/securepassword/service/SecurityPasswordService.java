package br.com.igorsily.securepassword.service;

import br.com.igorsily.securepassword.utils.Constantes;
import br.com.igorsily.securepassword.web.dto.SecurityPasswordRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityPasswordService {

    public List<String> validatePassword(SecurityPasswordRequest request) {
        List<String> erros = new ArrayList<>();

        validatePasswordIsNull(request.password(), erros);

        if (!erros.isEmpty()) {
            return erros;
        }


        validateLength(request.password(), erros);
        validateUppercase(request.password(), erros);
        validateLowercase(request.password(), erros);
        validateNumber(request.password(), erros);
        validateSpecialCharacter(request.password(), erros);

        return erros;
    }

    public void validatePasswordIsNull(String password, List<String> erros) {
        if (password == null) {
            erros.add(Constantes.VALIDATE_NULL_PASSWORD);
        }
    }

    public void validateLength(String password, List<String> erros) {
        if (password.length() < 8) {
            erros.add(Constantes.VALIDATE_LENGTH_PASSWORD);
        }
    }

    public void validateUppercase(String password, List<String> erros) {
        if (!password.matches(".*[A-Z].*")) {
            erros.add(Constantes.VALIDATE_UPPERCASE_PASSWORD);
        }
    }

    public void validateLowercase(String password, List<String> erros) {
        if (!password.matches(".*[a-z].*")) {
            erros.add(Constantes.VALIDATE_LOWERCASE_PASSWORD);
        }
    }

    public void validateNumber(String password, List<String> erros) {
        if (!password.matches(".*\\d.*")) {
            erros.add(Constantes.VALIDATE_NUMERIC_PASSWORD);
        }
    }

    public void validateSpecialCharacter(String password, List<String> erros) {
        if (!password.matches(".*\\W.*")) {
            erros.add(Constantes.VALIDATE_SPECIAL_CHARACTER_PASSWORD);
        }

    }
}
