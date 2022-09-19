package hr.diplomski.todo.controller;

import hr.diplomski.todo.converter.TodoItemConverter;
import hr.diplomski.todo.domain.HrUser;
import hr.diplomski.todo.domain.TodoItem;
import hr.diplomski.todo.domain.form.TodoItemForm;
import hr.diplomski.todo.exception.ActionForbiddenException;
import hr.diplomski.todo.facade.TodoItemFacade;
import hr.diplomski.todo.facade.UserFacade;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.AccessControlException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/todo")
public class TodoController {

    private UserFacade userFacade;
    private TodoItemFacade todoItemFacade;
    private TodoItemConverter todoItemConverter;

    public TodoController(final UserFacade userFacade,
                          final TodoItemFacade todoItemFacade,
                          final TodoItemConverter todoItemConverter) {
        this.userFacade = userFacade;
        this.todoItemFacade = todoItemFacade;
        this.todoItemConverter = todoItemConverter;
    }

    @ModelAttribute
    public void addUserIdAttribute(final Model model,
                                   final Principal principal) {
        HrUser user = userFacade.getUserRepository().findByUsername(principal.getName());
        model.addAttribute("userId", user.getId());
    }

    @GetMapping("/home")
    public String getTodoIndexPage() {
        return "todoIndex";
    }

    @GetMapping("/{id}/list")
    public String getTodoList(@PathVariable("id") String id,
                              final Model model,
                              final Principal principal) {

        //TODO Dio koda koji onemogucava horizontalnu eskalaciju privilegija
        HrUser user = userFacade.getUserRepository().findByUsername(principal.getName());
        if (!String.valueOf(user.getId()).equals(id)) {
            throw new ActionForbiddenException("User has no permission for this!");
        }
        List<TodoItem> todoList = todoItemFacade.getTodoItemRepository().findByUser_Id(Integer.valueOf(id));

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
                                final BindingResult bindingResult,
                                final RedirectAttributes redirectAttributes,
                                final Model model) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.todoItemForm", bindingResult);
            redirectAttributes.addFlashAttribute("todoItemForm", todoItemForm);

            return "redirect:/todo/create";
        }

        HrUser user = userFacade.getUserRepository().findById(Integer.valueOf(model.getAttribute("userId").toString())).get();
        todoItemForm.setUser(user);

        todoItemFacade.create(todoItemForm);

        redirectAttributes.addFlashAttribute("createTodoItemSuccess", true);
        return "redirect:/todo/" + user.getId() + "/list";
    }

    @GetMapping("/edit")
    public String getEditForm(@RequestParam(value = "id")Integer id,
                              final Model model){

        if (!model.containsAttribute("updateTodoItemForm")) {
            TodoItem todoItem = todoItemFacade.getTodoItemRepository().findById(id).get();
            TodoItemForm updateTodoItemForm = todoItemConverter.convertToForm(todoItem);
            model.addAttribute("updateTodoItemForm", updateTodoItemForm);
        }

        model.addAttribute("updateTodoItemForm", model.getAttribute("updateTodoItemForm"));

        return "updateTodoItem";
    }

    @PostMapping("/edit")
    public String postEditForm(@ModelAttribute @Valid TodoItemForm updateTodoItemForm,
                               final BindingResult bindingResult,
                               final RedirectAttributes redirectAttributes,
                               final Model model) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateTodoItemForm", bindingResult);
            redirectAttributes.addFlashAttribute("updateTodoItemForm", updateTodoItemForm);

            return "redirect:/todo/edit";
        }

        TodoItem editedTodoItem = todoItemConverter.convert(updateTodoItemForm);
        todoItemFacade.getTodoItemRepository().save(editedTodoItem);

        redirectAttributes.addFlashAttribute("updateTodoItemSuccess", true);
        String userId = model.getAttribute("userId").toString();
        return "redirect:/todo/" + userId + "/list";
    }

    @GetMapping("/delete")
    public String deleteTodoItem(@RequestParam("id") Integer id,
                                 final RedirectAttributes redirectAttributes,
                                 final Model model){
        todoItemFacade.getTodoItemRepository().deleteById(id);

        redirectAttributes.addFlashAttribute("deleteTodoItemSuccess", true);
        String userId = model.getAttribute("userId").toString();
        return "redirect:/todo/" + userId + "/list";
    }
}
