package hr.diplomski.todo.controller;

import hr.diplomski.todo.converter.UserConverter;
import hr.diplomski.todo.domain.HrUser;
import hr.diplomski.todo.domain.TodoItem;
import hr.diplomski.todo.domain.form.TodoItemForm;
import hr.diplomski.todo.domain.form.UserForm;
import hr.diplomski.todo.facade.UserFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    UserFacade userFacade;
    private UserConverter userConverter;

    public UserController(final UserFacade userFacade,
                          final UserConverter userConverter) {
        this.userFacade = userFacade;
        this.userConverter = userConverter;
    }

    @GetMapping("/my-profile")
    public String getMyUserProfilePage() {
        return "myUserProfile";
    }

    @GetMapping("/list")
    public String getUserList(final Model model) {
        List<HrUser> userList = userFacade.getUserRepository().findAll();

        model.addAttribute("userList", userList);
        return "userList";
    }

    @GetMapping("/edit")
    public String getEditForm(@RequestParam(value = "id")Integer id, Model model){

        if (!model.containsAttribute("updateUserForm")) {
            HrUser user = userFacade.getUserRepository().findById(id).get();
            UserForm updateUserForm = userConverter.convertToForm(user);
            model.addAttribute("updateUserForm", updateUserForm);
        }

        model.addAttribute("updateUserForm", model.getAttribute("updateUserForm"));

        return "updateUser";
    }

    @PostMapping("/edit")
    public String postEditForm(@ModelAttribute @Valid UserForm updateUserForm,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateUserForm", bindingResult);
            redirectAttributes.addFlashAttribute("updateUserForm", updateUserForm);

            return "redirect:/user/edit";
        }

        HrUser editedUser = userConverter.convert(updateUserForm);
        userFacade.getUserRepository().save(editedUser);

        redirectAttributes.addFlashAttribute("updateUserSuccess", true);
        return "redirect:/user/list";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Integer id,
                                 RedirectAttributes redirectAttributes){
        userFacade.getUserRepository().deleteById(id);

        redirectAttributes.addFlashAttribute("deleteUserSuccess", true);
        return "redirect:/user/list";
    }
}
