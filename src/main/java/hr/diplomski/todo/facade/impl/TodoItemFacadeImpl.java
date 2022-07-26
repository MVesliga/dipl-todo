package hr.diplomski.todo.facade.impl;

import hr.diplomski.todo.converter.TodoItemConverter;
import hr.diplomski.todo.domain.TodoItem;
import hr.diplomski.todo.domain.form.TodoItemForm;
import hr.diplomski.todo.facade.TodoItemFacade;
import hr.diplomski.todo.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TodoItemFacadeImpl implements TodoItemFacade {

    private TodoItemRepository todoItemRepository;
    private TodoItemConverter todoItemConverter;

    @Autowired
    public TodoItemFacadeImpl(TodoItemRepository todoItemRepository, TodoItemConverter todoItemConverter) {
        this.todoItemRepository = todoItemRepository;
        this.todoItemConverter = todoItemConverter;
    }

    @Override
    public TodoItemRepository getTodoItemRepository() {
        return todoItemRepository;
    }

    @Override
    public TodoItem getById(final Integer id) {
        return todoItemRepository.findById(id).get();
    }

    @Override
    public void create(TodoItemForm form) {
        TodoItem todoItem = todoItemConverter.convert(form);
        todoItemRepository.save(todoItem);
    }

    @Override
    public void update(TodoItemForm form) {
        TodoItem todoItem = todoItemConverter.convert(form);
        todoItemRepository.save(todoItem);
    }

    @Override
    public void delete(final Integer id) {
        todoItemRepository.deleteById(id);
    }
}
