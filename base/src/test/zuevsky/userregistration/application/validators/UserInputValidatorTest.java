package zuevsky.userregistration.application.validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class UserInputValidatorTest {
    UserInputValidator userInputValidator = new UserInputValidator();

    @ParameterizedTest
    @CsvSource({"'asdasd.asd@mail.ru', true, 'test-email@maild.ruasd', true," +
            " 'kkk@lib.co.com', true, '23487283@list.com', true"})
    void validateRegex_shouldValidate_whenEmailIsCorrect(String example, boolean expected) {
        assertEquals(expected, userInputValidator.validateRegex
                (example, UserInputValidator.getEmailRegex()));
    }

    @ParameterizedTest
    @CsvSource({"'asdasd@asd@mail.ru', false, 'test-em?ail@maild.ruasd', false," +
            " 'kkk@lib.co.com.ru', false, '23487283фыв@list.com', false"})
    void validateRegex_shouldFail_whenEmailIsIncorrect(String example, boolean expected) {
        assertEquals(expected, userInputValidator.validateRegex
                (example, UserInputValidator.getEmailRegex()));
    }

    @ParameterizedTest
    @CsvSource({"'Alexey', true, 'Алексей', true"})
    void validateRegex_shouldValidate_whenNameIsCorrect(String example, boolean expected) {
        assertEquals(expected, userInputValidator.validateRegex
                (example, UserInputValidator.getNameRegex()));
    }

    @ParameterizedTest
    @CsvSource({"'a', false, 'ajfhsjdhfishdofshidufsdf', false, 'ajfhs7', false"})
    void validateRegex_shouldFail_whenNameIsIncorrect(String example, boolean expected) {
        assertEquals(expected, userInputValidator.validateRegex
                (example, UserInputValidator.getNameRegex()));
    }

    @ParameterizedTest
    @CsvSource({"'Зуевский', true, 'Zuevsky', true"})
    void validateRegex_shouldValidate_whenSurnameIsCorrect(String example, boolean expected) {
        assertEquals(expected, userInputValidator.validateRegex
                (example, UserInputValidator.getSurnameRegex()));
    }

    @ParameterizedTest
    @CsvSource({"'d', false, 'daksjdklasdkhjajsdjasjdlansd', false, 'daks*', false"})
    void validateRegex_shouldFail_whenSurnameIsIncorrect(String example, boolean expected) {
        assertEquals(expected, userInputValidator.validateRegex
                (example, UserInputValidator.getSurnameRegex()));
    }

    @ParameterizedTest
    @CsvSource({"375299486547, true"})
    void validateRegex_shouldValidate_whenPhoneIsCorrect(String example, boolean expected) {
        assertEquals(expected, userInputValidator.validateRegex
                (example, UserInputValidator.getPhoneRegex()));
    }

    @ParameterizedTest
    @CsvSource({"'+375299486547', false, '+375299486547475', false, " +
            "'3752994d', false, '376231954738', false"})
    void validateRegex_shouldFail_whenPhoneIsIncorrect(String example, boolean expected) {
        assertEquals(expected, userInputValidator.validateRegex
                (example, UserInputValidator.getPhoneRegex()));
    }

    @ParameterizedTest
    @CsvSource({"'alexey.zuevsky@mail.ru', true"})
    void validateEmail_shouldValidate_whenEmailIsCorrect(String example, boolean expected) {
        assertEquals(expected, userInputValidator.validateEmail
                (example, UserInputValidatorTestRepositories.getUserList()));
    }

    @ParameterizedTest
    @CsvSource({"'dmitriy.shved@gmail.com', false, 'jwhfoin', false"})
    void validateEmail_shouldFail_whenEmailIsIncorrect(String example, boolean expected) {
        assertEquals(expected, userInputValidator.validateEmail
                (example, UserInputValidatorTestRepositories.getUserList()));
    }

    @ParameterizedTest
    @CsvSource({"'Petr', true"})
    void validateName_shouldValidate_whenNameIsCorrect(String example, boolean expected) {
        assertEquals(expected, userInputValidator.validateName(example));
    }

    @ParameterizedTest
    @CsvSource({"'Andreanisiusiysdfhsdfusdf', false, 'alex89', false, 'alex_', false"})
    void validateName_shouldFail_whenNameIsIncorrect(String example, boolean expected) {
        assertEquals(expected, userInputValidator.validateName(example));
    }

    @ParameterizedTest
    @CsvSource({"'zuevsky', true"})
    void validateSurname_shouldValidate_whenSurnameIsCorrect(String example, boolean expected) {
        assertEquals(expected, userInputValidator.validateSurname(example));
    }

    @ParameterizedTest
    @CsvSource({"'antonov3', false, 'antonov.!', false"})
    void validateSurname_shouldFail_whenSurnameIsIncorrect(String example, boolean expected) {
        assertEquals(expected, userInputValidator.validateSurname(example));
    }

    @ParameterizedTest
    @CsvSource({"'375291754356', true"})
    void validatePhone_shouldValidate_whenPhoneIsCorrect(String example, boolean expected) {
        assertEquals(expected, userInputValidator.validatePhone
                (example, UserInputValidatorTestRepositories.getPhoneList(),
                        UserInputValidatorTestRepositories.getUserList()));
    }

    @ParameterizedTest
    @CsvSource({"'375331564454', false, '300331564454', false, 'sdfjsdf', false, '37529161745!', false"})
    void validatePhone_shouldFail_whenPhoneIsIncorrect(String example, boolean expected) {
        assertEquals(expected, userInputValidator.validatePhone
                (example, UserInputValidatorTestRepositories.getPhoneList(),
                        UserInputValidatorTestRepositories.getUserList()));
    }


}