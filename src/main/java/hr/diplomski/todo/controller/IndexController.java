package hr.diplomski.todo.controller;

import hr.diplomski.todo.domain.HrUser;
import hr.diplomski.todo.domain.UserRole;
import hr.diplomski.todo.domain.form.LoginForm;
import hr.diplomski.todo.domain.form.RegisterForm;
import hr.diplomski.todo.facade.UserFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class IndexController {

    private UserFacade userFacade;

    public IndexController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/login")
    public String getLoginForm(@RequestParam(value = "error", required = false) boolean error, Model model) {
        if (!model.containsAttribute("loginForm")) {
            model.addAttribute("loginForm", new LoginForm());
        }
        if (error == true) {
            model.addAttribute("isError", true);
        }
        return "login";
    }

    @PostMapping("/login")
    public String postLoginForm(@ModelAttribute @Valid LoginForm loginForm,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
     if (bindingResult.hasErrors()) {
         redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginForm", bindingResult);
         redirectAttributes.addFlashAttribute("loginForm", loginForm);

         return "redirect:/login";
     }

     return "todoIndex";
 }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        if (!model.containsAttribute("registerForm")) {
            model.addAttribute("registerForm", new RegisterForm());
        }

        return "registration";
    }

    @PostMapping("/register")
    public String postRegisterUser(@ModelAttribute @Valid RegisterForm registerForm,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {

        HrUser existingHrUser = userFacade.getUserRepository().findByUsername(registerForm.getUsername());
        if (existingHrUser != null) {
            bindingResult.rejectValue("username", null, "Postoji korisnik sa unesenim korisničkim imenom!");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerForm", bindingResult);
            redirectAttributes.addFlashAttribute("registerForm", registerForm);

            return "redirect:/register";
        }

        UserRole userRole = new UserRole(registerForm.getUsername(), "ROLE_USER");
        userFacade.create(registerForm);
        userFacade.getUserRoleRepository().save(userRole);

        redirectAttributes.addFlashAttribute("registerSuccess", true);
        return "redirect:/login";
    }
}
