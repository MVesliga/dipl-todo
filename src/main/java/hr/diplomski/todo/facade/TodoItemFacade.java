package hr.diplomski.todo.facade;

import hr.diplomski.todo.domain.TodoItem;
import hr.diplomski.todo.domain.form.TodoItemForm;
import hr.diplomski.todo.repository.TodoItemRepository;

import java.util.List;

public interface TodoItemFacade {
    TodoItemRepository getTodoItemRepository();
    TodoItem getById(final Integer id);
    void create(final TodoItemForm form);
    void update(final TodoItemForm form);
    void delete(final Integer id);
}
