package hr.diplomski.todo.converter;

import hr.diplomski.todo.domain.TodoItem;
import hr.diplomski.todo.domain.form.TodoItemForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TodoItemConverter implements Converter<TodoItemForm, TodoItem> {
    @Override
    public TodoItem convert(final TodoItemForm source) {
        TodoItem todoItem = new TodoItem();
        todoItem.setDescription(source.getDescription());
        todoItem.setCreationDate(source.getCreationDate());
        todoItem.setCompleted(source.isCompleted());
        return todoItem;
    }

    public TodoItemForm convertToForm(final TodoItem todoItem) {
        TodoItemForm todoItemForm = new TodoItemForm();
        todoItemForm.setId(todoItem.getId());
        todoItemForm.setDescription(todoItem.getDescription());
        todoItemForm.setCreationDate(todoItem.getCreationDate());
        todoItemForm.setCompleted(todoItem.isCompleted());

        return todoItemForm;
    }


}
