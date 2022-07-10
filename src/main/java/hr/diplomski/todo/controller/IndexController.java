package hr.diplomski.todo.controller;

import hr.diplomski.todo.domain.form.LoginForm;
import hr.diplomski.todo.domain.form.RegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/login")
    public String getLoginForm(Model model){
        if (!model.containsAttribute("loginForm")) {
            model.addAttribute("loginForm", new LoginForm());
        }
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        if (!model.containsAttribute("registerForm")) {
            model.addAttribute("registerForm", new RegisterForm());
        }

        return "registration";
    }
}
