package com.example.OnlineBuyTickets.controllers.AdminPackage;

import com.example.OnlineBuyTickets.models.Role;
import com.example.OnlineBuyTickets.models.User;
import com.example.OnlineBuyTickets.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminMenuController {

    private final UserService userService;

    public AdminMenuController(UserService userService) {
        this.userService = userService;
    }

    //преход в админ меню, если пользователь не имеет роли ADMIN, то переход не осуществляеться
    @GetMapping("/menu")
    public String adminMenu(Principal principal){
            return "Admin/admin_menu";
    }
}
