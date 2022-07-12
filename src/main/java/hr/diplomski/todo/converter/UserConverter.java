package hr.diplomski.todo.converter;

import hr.diplomski.todo.domain.HrUser;
import hr.diplomski.todo.domain.form.RegisterForm;
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

        return hrUser;
    }
}
