package hr.diplomski.todo.domain.form;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class UserForm {
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
    @NotNull
    private String password;
    private boolean enabled;
}
