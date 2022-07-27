package hr.diplomski.todo.converter;

import hr.diplomski.todo.domain.TodoItem;
import hr.diplomski.todo.domain.form.TodoItemForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class TodoItemConverter implements Converter<TodoItemForm, TodoItem> {
    @Override
    public TodoItem convert(final TodoItemForm source) {
        TodoItem todoItem = new TodoItem();
        todoItem.setDescription(source.getDescription());
        todoItem.setCreationDate(LocalDateTime.now());
        todoItem.setCompleted(source.isCompleted());
        todoItem.setUser(source.getUser());
        return todoItem;
    }

    public TodoItemForm convertToForm(final TodoItem todoItem) {
        TodoItemForm todoItemForm = new TodoItemForm();
        todoItemForm.setId(todoItem.getId());
        todoItemForm.setDescription(todoItem.getDescription());
        todoItemForm.setCompleted(todoItem.isCompleted());

        return todoItemForm;
    }


}
