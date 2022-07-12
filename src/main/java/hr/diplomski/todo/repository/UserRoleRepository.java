package hr.diplomski.todo.repository;

import hr.diplomski.todo.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    @Transactional
    void deleteByUsername(String username);
}
