package hr.diplomski.todo.converter;

import hr.diplomski.todo.domain.HrUser;
import hr.diplomski.todo.domain.form.RegisterForm;
import hr.diplomski.todo.domain.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<RegisterForm, HrUser> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public HrUser convert(final RegisterForm source) {
        HrUser hrUser = new HrUser();
        hrUser.setFirstName(source.getFirstName());
        hrUser.setLastName(source.getLastName());
        hrUser.setEmail(source.getEmail());
        hrUser.setUsername(source.getUsername());
        hrUser.setPassword(passwordEncoder.encode(source.getPassword()));
        hrUser.setEnabled(true);

        return hrUser;
    }

    public HrUser convert(final UserForm source) {
        HrUser hrUser = new HrUser();
        hrUser.setId(source.getId());
        hrUser.setFirstName(source.getFirstName());
        hrUser.setLastName(source.getLastName());
        hrUser.setEmail(source.getEmail());
        hrUser.setUsername(source.getUsername());
        hrUser.setPassword(source.getPassword());
        hrUser.setEnabled(source.isEnabled());

        return hrUser;
    }

    public UserForm convertToForm(final HrUser user) {
        UserForm userForm = new UserForm();
        userForm.setId(user.getId());
        userForm.setFirstName(user.getFirstName());
        userForm.setLastName(user.getLastName());
        userForm.setUsername(user.getUsername());
        userForm.setEmail(user.getEmail());
        userForm.setPassword(user.getPassword());
        userForm.setEnabled(user.isEnabled());

        return userForm;
    }
}
