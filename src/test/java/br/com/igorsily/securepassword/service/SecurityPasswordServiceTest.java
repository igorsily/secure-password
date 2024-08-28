package br.com.igorsily.securepassword.service;

import br.com.igorsily.securepassword.utils.Constantes;
import br.com.igorsily.securepassword.web.dto.SecurityPasswordRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SecurityPasswordServiceTest {

    @InjectMocks
    private SecurityPasswordService securityPasswordService;

    private List<String> erros;

    @BeforeEach
    void setUp() {
        this.erros = new ArrayList<>();
    }

    @Test
    void shouldNotAddErrorWhenPasswordIsValid() {
        var password = "Abcde2F@";
        var request = new SecurityPasswordRequest(password);

        var result = this.securityPasswordService.validatePassword(request);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldEarlyAddErrorWhenPasswordIsNull() {
        SecurityPasswordService mock = Mockito.mock(SecurityPasswordService.class);
        var request = new SecurityPasswordRequest(null);

        this.securityPasswordService.validatePassword(request);

        Mockito.verify(mock, Mockito.times(0)).validateLength(Mockito.any(), Mockito.anyList());

    }

    @Test
    void shouldAddErrorWhenPasswordIsTooShort() {
        var password = "1234567";

        this.securityPasswordService.validateLength(password, this.erros);

        var expected = Constantes.VALIDATE_LENGTH_PASSWORD;
        assertFalse(this.erros.isEmpty());
        assertEquals(expected, this.erros.getFirst());
    }

    @Test
    void shouldNotAddErrorWhenPasswordIsValidLength() {
        var password = "12345678";

        this.securityPasswordService.validateLength(password, this.erros);

        assertTrue(this.erros.isEmpty());
    }

    @Test
    void shouldAddErrorWhenPasswordIsNull() {

        this.securityPasswordService.validatePasswordIsNull(null, this.erros);

        var expected = Constantes.VALIDATE_NULL_PASSWORD;
        assertFalse(this.erros.isEmpty());
        assertEquals(expected, this.erros.getFirst());
    }

    @Test
    void shouldNotAddErrorWhenPasswordIsNotNull() {

        this.securityPasswordService.validatePasswordIsNull("dsa", this.erros);

        assertTrue(this.erros.isEmpty());
    }

    @Test
    void shouldAddErrorWhenPasswordIsNotUppercase() {

        var password = "abcde";

        this.securityPasswordService.validateUppercase(password, this.erros);

        var expected = Constantes.VALIDATE_UPPERCASE_PASSWORD;
        assertFalse(this.erros.isEmpty());
        assertEquals(expected, this.erros.getFirst());

    }

    @Test
    void shouldNotAddErrorWhenPasswordIsUppercase() {

        var password = "abCde";

        this.securityPasswordService.validateUppercase(password, this.erros);

        assertTrue(this.erros.isEmpty());

    }

    @Test
    void shouldAddErrorWhenPasswordIsNotLowerCase() {

        var password = "SADAS";

        this.securityPasswordService.validateLowercase(password, this.erros);

        var expected = Constantes.VALIDATE_LOWERCASE_PASSWORD;
        assertFalse(this.erros.isEmpty());
        assertEquals(expected, this.erros.getFirst());

    }

    @Test
    void shouldNotAddErrorWhenPasswordIsLowerCase() {

        var password = "abCde";

        this.securityPasswordService.validateLowercase(password, this.erros);

        assertTrue(this.erros.isEmpty());

    }

    @Test
    void shouldAddErrorWhenPasswordIsNotContainNumeric() {

        var password = "AbCdE";

        this.securityPasswordService.validateNumber(password, this.erros);

        var expected = Constantes.VALIDATE_NUMERIC_PASSWORD;
        assertFalse(this.erros.isEmpty());
        assertEquals(expected, this.erros.getFirst());

    }

    @Test
    void shouldNotAddErrorWhenPasswordIsContainNumeric() {

        var password = "abCde2F";

        this.securityPasswordService.validateNumber(password, this.erros);

        assertTrue(this.erros.isEmpty());

    }

    @Test
    void shouldAddErrorWhenPasswordIsNotContainSpecialCharacter() {

        var password = "abCde2F";

        this.securityPasswordService.validateSpecialCharacter(password, this.erros);

        var expected = Constantes.VALIDATE_SPECIAL_CHARACTER_PASSWORD;
        assertFalse(this.erros.isEmpty());
        assertEquals(expected, this.erros.getFirst());

    }

    @Test
    void shouldNotAddErrorWhenPasswordIsContainSpecialCharacter() {

        var password = "abCde2F@";

        this.securityPasswordService.validateSpecialCharacter(password, this.erros);

        assertTrue(this.erros.isEmpty());

    }
}
