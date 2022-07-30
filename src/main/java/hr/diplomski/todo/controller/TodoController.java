package hr.diplomski.todo.controller;

import hr.diplomski.todo.domain.CustomUserDetails;
import hr.diplomski.todo.domain.HrUser;
import hr.diplomski.todo.domain.TodoItem;
import hr.diplomski.todo.domain.form.TodoItemForm;
import hr.diplomski.todo.facade.TodoItemFacade;
import hr.diplomski.todo.facade.UserFacade;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
        List<TodoItem> todoList = todoItemFacade.getTodoItemRepository().findByUser_Id(user.getId());

        model.addAttribute("todoList", todoList);
        return "todoList";
    }

    @GetMapping("/create")
    public String getCreateForm(final Model model) {
        if (!model.containsAttribute("todoItemForm")) {
            model.addAttribute("todoItemForm", new TodoItemForm());
        }

        return "createTodo";
    }

    @PostMapping("/create")
    public String postCreateForm(@ModelAttribute @Valid TodoItemForm todoItemForm,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.todoItemForm", bindingResult);
            redirectAttributes.addFlashAttribute("todoItemForm", todoItemForm);

            return "redirect:/todo/create";
        }

        HrUser user = userFacade.getUserRepository().findByUsername(principal.getName());
        todoItemForm.setUser(user);

        todoItemFacade.create(todoItemForm);

        redirectAttributes.addFlashAttribute("createTodoItemSuccess", true);
        return "redirect:/todo/list";
    }

    @GetMapping("/delete")
    public String deleteLjubimac(@RequestParam("id") Integer id,
                                 RedirectAttributes redirectAttributes){
        todoItemFacade.getTodoItemRepository().deleteById(id);

        redirectAttributes.addFlashAttribute("deleteTodoItemSuccess", true);
        return "redirect:/todo/list";
    }
}
