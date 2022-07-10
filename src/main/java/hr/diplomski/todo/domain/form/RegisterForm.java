package hr.diplomski.todo.domain.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegisterForm {
    private Integer id;
    @NotBlank(message = "{registerForm.firstName.notBlank}")
    private String firstName;
    @NotBlank(message = "{registerForm.lastName.notBlank}")
    private String lastName;
    @NotBlank(message = "{registerForm.username.notBlank}")
    private String username;
    @NotBlank(message = "{registerForm.email.notBlank}")
    @Email(message = "{registerForm.email.format}")
    private String email;
    @NotBlank(message = "{registerForm.password.notBlank}")
    private String password;
    @NotBlank(message = "{registerForm.repeatedPassword.notBlank}")
    private String repeatedPassword;
}
