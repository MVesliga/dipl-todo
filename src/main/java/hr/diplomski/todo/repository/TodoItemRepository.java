package hr.diplomski.todo.repository;

import hr.diplomski.todo.domain.HrUser;
import hr.diplomski.todo.domain.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TodoItemRepository extends CrudRepository<TodoItem, Integer> {
    Optional<TodoItem> findById(Integer id);
    List<TodoItem> findByUser_Id(Integer userId);
}
