package hr.diplomski.todo.repository;

import hr.diplomski.todo.domain.HrUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<HrUser, Integer> {

    HrUser findByUsername(String username);
}
