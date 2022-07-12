package hr.diplomski.todo.facade;

import hr.diplomski.todo.domain.form.RegisterForm;
import hr.diplomski.todo.repository.UserRepository;
import hr.diplomski.todo.repository.UserRoleRepository;

public interface UserFacade {
    UserRepository getUserRepository();
    UserRoleRepository getUserRoleRepository();
    void create(RegisterForm registerForm);
}
