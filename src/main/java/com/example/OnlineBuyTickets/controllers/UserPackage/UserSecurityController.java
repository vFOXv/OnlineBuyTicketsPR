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
        //переменная указывающая на наличее ошибок при заполненние полей регистрации нового юзера
        boolean flagMethod = true;
        //проверка на наличее в DB user с подобным Username
        if (!userService.checkNewUsername(newUser)){
            boolean flag = true;
            model.addAttribute("usernameError", flag);
            flagMethod = false;
        }
        //проверка на правильность пароля(пароль и его повторения в окошке ниже)
        if (!newUser.getPassword().equals(newUser.getPasswordConfirm())){
            boolean flag = true;
            model.addAttribute("passwordError", flag);
            flagMethod = false;
        }
        if (bindingResult.hasErrors()){
            flagMethod = false;
        }
        // если есть хоть одна ошибка - возврат на страницу регистрации
        if(flagMethod){
            userService.saveNewUser(newUser);
            return "Security/login";
        }else{
            return "Security/create_new_user";
        }
    }
}
