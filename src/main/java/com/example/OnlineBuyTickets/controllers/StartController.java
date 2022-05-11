package com.example.OnlineBuyTickets.controllers;

import com.example.OnlineBuyTickets.models.Role;
import com.example.OnlineBuyTickets.models.User;
import com.example.OnlineBuyTickets.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collection;



@Controller
public class StartController {
    private final UserService userService;

    public StartController(UserService userService) {
        this.userService = userService;
    }

    //при входе в меню происходит проверка на роли. Если у пользовотеля роль админ,
    // он видит на HTML странице ссылку на админ часть сайта, если нет, то th:if скрывает ее
    @GetMapping("/menu")
    public String goToMenu(Model model, Principal principal){
        //получение у user поля active
        boolean active = userService.findByUserName(principal.getName()).getActive();
        model.addAttribute("flag", active);
        //если узер забанен(active = false) переход по URL logout
        if(!active){
            model.addAttribute("Ban", "You are banned!!!");
            model.addAttribute("Active", active);
            return "redirect:/logout";
        }
        User thisUser = userService.findByUserName(principal.getName());
        Collection<Role> roles =  thisUser.getRoles();
        Boolean flag = roles.contains(new Role(2L, "ROLE_ADMIN"));
        model.addAttribute("flag",flag);
        return "Start/menu";
    }
}
