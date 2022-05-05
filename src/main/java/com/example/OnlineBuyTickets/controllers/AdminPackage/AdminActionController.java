package com.example.OnlineBuyTickets.controllers.AdminPackage;

import com.example.OnlineBuyTickets.models.BusFlight;
import com.example.OnlineBuyTickets.models.User;
import com.example.OnlineBuyTickets.services.BusFlightService;
import com.example.OnlineBuyTickets.services.TicketService;
import com.example.OnlineBuyTickets.services.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin/action")
public class AdminActionController {
    private final TicketService ticketService;
    private final UserService userService;
    private final BusFlightService busFlightService;

    public AdminActionController(TicketService ticketService, UserService userService, BusFlightService busFlightService) {
        this.ticketService = ticketService;
        this.userService = userService;
        this.busFlightService = busFlightService;
    }

    //удаление билета по id (если пассажир сдал билет)
    @DeleteMapping("/deleteThisTicket/{id}")
    public String deleteThisTicketById(@PathVariable("id") Long id, Model model){
        ticketService.deleteTicket(id);
        return "redirect:/admin/show/allTickets";
    }

    //бан и включение user по полю active
    @PostMapping("/ban/{username}")
    //@PathVariable - получение username из URL(для индификации user)
    // @ModelAttribute - получение объекта User из HTML, с одним заполненым полем - active(checkbox передает только 1 параметр)
    public String banThisUser(@PathVariable("username") String username,@ModelAttribute("ThisUser") User banUser){
        User myUser = userService.findByUserName(username);
        //задание значения поля active переданого с HTML show_this_ticket.html
        myUser.setActive(banUser.getActive());
        //сохранение изменений в актовности user в DB
        userService.saveUser(myUser);
        return "redirect:/admin/show/allUsers";
    }

    //переход на HTML для создание нового рейса
    @GetMapping("/newBusFlight")
    public String createNewBusFlightToHtml(Model model, Principal principal){
        model.addAttribute("NameUser", principal.getName());
        BusFlight busFlight = new BusFlight();
        model.addAttribute("NewBusFlight", busFlight);
        return "Admin/create_new_busflight";
    }

    //передача с HTML данных о новом рейсе и запись его в DB
    @PostMapping("/newBusFlight")
    public String createNewBusFlightToDb(@DateTimeFormat(pattern = "yyyy-MM-dd  HH: mm") @ModelAttribute("NewBusFlight") BusFlight busFlight){
        busFlightService.saveNewBusFlight(busFlight);
        return "redirect:show/allBusFlights";
    }

}
