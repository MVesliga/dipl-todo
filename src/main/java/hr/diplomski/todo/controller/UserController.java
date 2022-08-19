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
import java.security.Principal;
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

    @ModelAttribute
    public void addUserIdAttribute(final Model model,
                                   final Principal principal) {
        HrUser user = userFacade.getUserRepository().findByUsername(principal.getName());
        model.addAttribute("userId", user.getId());
    }

    @GetMapping("/my-profile")
    public String getMyUserProfilePage(final Principal principal,
                                       final Model model) {
        HrUser user = userFacade.getUserRepository().findByUsername(principal.getName());
        model.addAttribute("user", user);

        return "myUserProfile";
    }

    @GetMapping("/profile/{id}")
    public String getMyUserProfilePage(@PathVariable("id") String id,
                                       final Model model) {
        HrUser user = userFacade.getUserRepository().findById(Integer.valueOf(id)).get();
        model.addAttribute("user", user);

        return "userProfile";
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
        //TODO delete user_role\
        HrUser user = userFacade.getUserRepository().findById(id).get();
        userFacade.getUserRoleRepository().deleteByUsername(user.getUsername());
        userFacade.getUserRepository().deleteById(id);
        redirectAttributes.addFlashAttribute("deleteUserSuccess", true);

        return "redirect:/user/list";
    }
}
