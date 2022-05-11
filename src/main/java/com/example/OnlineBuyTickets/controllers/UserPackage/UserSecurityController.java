package com.example.OnlineBuyTickets.controllers.UserPackage;

import com.example.OnlineBuyTickets.models.User;
import com.example.OnlineBuyTickets.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/security")
public class UserSecurityController {
    private final UserService userService;

    public UserSecurityController(UserService userService) {
        this.userService = userService;
    }

    //переход на страницу HTML создания нового юзера
    @GetMapping("/newUser")
    public String createNewUserHTML(Model model) {
        model.addAttribute("NewUser", new User());
        return "Security/create_new_user";
    }

    //получение данных о новом юзоре с HTML и запись в DB
    @PostMapping("/create/newUser")
    public String createNewUserDB(@ModelAttribute("NewUser") @Valid User newUser,
                                  BindingResult bindingResult, Model model) {
        System.out.println("--------------------------->"+newUser);
        if (bindingResult.hasErrors()){
            return "Security/create_new_user";
        }
        if (!newUser.getPassword().equals(newUser.getPasswordConfirm())){
            model.addAttribute("passwordError", "Passwords not equals!!!");
            return "Security/create_new_user";
        }
        if (!userService.saveNewUser(newUser)){
            model.addAttribute("usernameError", "User with the same name already exists!!! ");
            return "Security/create_new_user";
        }
        return "redirect:/admin/show/allUsers";
    }
}
