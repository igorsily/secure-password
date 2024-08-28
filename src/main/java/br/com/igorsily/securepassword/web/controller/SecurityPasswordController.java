package br.com.igorsily.securepassword.web.controller;

import br.com.igorsily.securepassword.service.SecurityPasswordService;
import br.com.igorsily.securepassword.web.dto.SecurityPasswordRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/security")
public class SecurityPasswordController {

    private final SecurityPasswordService securityPasswordService;

    public SecurityPasswordController(SecurityPasswordService securityPasswordService) {
        this.securityPasswordService = securityPasswordService;
    }

    @PostMapping("/validate-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<List<String>> validatePassword(@RequestBody SecurityPasswordRequest request) {
        var erros = securityPasswordService.validatePassword(request);

        if (!erros.isEmpty()) {
            return ResponseEntity.badRequest().body(erros);
        }

        return ResponseEntity.noContent().build();
    }
}
