package hr.diplomski.todo.controller;

import hr.diplomski.todo.domain.CustomUserDetails;
import hr.diplomski.todo.domain.HrUser;
import hr.diplomski.todo.domain.TodoItem;
import hr.diplomski.todo.facade.TodoItemFacade;
import hr.diplomski.todo.facade.UserFacade;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/todo")
public class TodoController {

    private UserFacade userFacade;
    private TodoItemFacade todoItemFacade;

    public TodoController(final UserFacade userFacade,
                          final TodoItemFacade todoItemFacade) {
        this.userFacade = userFacade;
        this.todoItemFacade = todoItemFacade;
    }

    @GetMapping("/home")
    public String getTodoIndexPage() {
        return "todoIndex";
    }

    @GetMapping("/list")
    public String getTodoList(final Model model, final Principal principal) {
        HrUser user = userFacade.getUserRepository().findByUsername(principal.getName());
        TodoItem firstTodo = todoItemFacade.getTodoItemRepository().findById(1).get();
        List<TodoItem> todoList = todoItemFacade.getTodoItemRepository().findByUser_Id(user.getId());
        return "todoList";
    }
}
