package hr.diplomski.todo.facade.impl;

import hr.diplomski.todo.converter.UserConverter;
import hr.diplomski.todo.domain.HrUser;
import hr.diplomski.todo.domain.form.RegisterForm;
import hr.diplomski.todo.facade.UserFacade;
import hr.diplomski.todo.repository.UserRepository;
import hr.diplomski.todo.repository.UserRoleRepository;
import org.springframework.stereotype.Component;

@Component
public class UserFacadeImpl implements UserFacade {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private UserConverter userConverter;

    public UserFacadeImpl(final UserRepository userRepository,
                          final UserRoleRepository userRoleRepository,
                          final UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userConverter = userConverter;
    }

    @Override
    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public UserRoleRepository getUserRoleRepository() {
        return userRoleRepository;
    }

    @Override
    public void create(RegisterForm registerForm) {
        HrUser hrUser = userConverter.convert(registerForm);
        userRepository.save(hrUser);
    }
}
