package com.example.OnlineBuyTickets.controllers.AdminPackage;

import com.example.OnlineBuyTickets.models.Ticket;
import com.example.OnlineBuyTickets.services.BusFlightService;
import com.example.OnlineBuyTickets.services.PassengerService;
import com.example.OnlineBuyTickets.services.TicketService;
import com.example.OnlineBuyTickets.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin/show")
public class AdminShowController {
    private final TicketService ticketService;
    private final BusFlightService busFlightService;
    private final UserService userService;
    private final PassengerService passengerService;


    public AdminShowController(TicketService ticketService, BusFlightService busFlightService, UserService userService, PassengerService passengerService) {
        this.ticketService = ticketService;
        this.busFlightService = busFlightService;
        this.userService = userService;
        this.passengerService = passengerService;
    }

    //переход на HTML где показываються все купленные билеты
    @GetMapping("/allTickets")
    public String getAllTicket(Model model, Principal principal){
        model.addAttribute("NameUser",principal.getName());
        model.addAttribute("AllPassengers", passengerService.getAllPassengers());
        model.addAttribute("ThisTicket", "This ticket");
        //List BusFlights для select
        model.addAttribute("ListBusFlights", busFlightService.getAllBusFlights());
        //List User для select
        model.addAttribute("ListUsers", userService.getAllUsers());
        return "Admin/show_all_tickets";
    }

    //переход на HTML где показываються конкретный билет
    @GetMapping("/ticket/{id}")
    public String showThisTicket(@PathVariable("id") Long id, Model model, Principal principal){
        Ticket ticket = ticketService.getTicketById(id);
        model.addAttribute("ticket", ticket);
        model.addAttribute("NameUser", principal.getName());
        model.addAttribute("Delete", "DELETE");
        return "Admin/show_this_ticket";
    }

    ////переход на HTML где показываються все пользователи(user+admin)
    @GetMapping("/allUsers")
    public String showAllUsers(Model model, Principal principal){
        model.addAttribute("NameUser", principal.getName());
        model.addAttribute("ThisUser", "This User");
        model.addAttribute("AllUsers", userService.getAllUsers());
        return "Admin/show_all_users";
    }

    //переход на HTML где показываються конкретный user
    @GetMapping("/user/{username}")
    public String showThisUser(@PathVariable("username") String username, Model model,Principal principal){
        model.addAttribute("NameUser", principal.getName());
        model.addAttribute("ThisUser", userService.findByUserName(username));
        return "Admin/show_this_user";
    }

}
