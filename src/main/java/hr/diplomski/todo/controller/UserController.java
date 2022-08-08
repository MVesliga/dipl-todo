package hr.diplomski.todo.controller;

import hr.diplomski.todo.converter.UserConverter;
import hr.diplomski.todo.domain.HrUser;
import hr.diplomski.todo.facade.UserFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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


}
