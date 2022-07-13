package hr.diplomski.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/my-profile")
    public String getMyUserProfilePage() {
        return "myUserProfile";
    }

    @GetMapping("/list")
    public String getUserList() {
        return "userList";
    }
}
