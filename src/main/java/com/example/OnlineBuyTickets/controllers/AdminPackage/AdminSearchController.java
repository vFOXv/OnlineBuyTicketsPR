package com.example.OnlineBuyTickets.controllers.AdminPackage;

import com.example.OnlineBuyTickets.services.BusFlightService;
import com.example.OnlineBuyTickets.services.TicketService;
import com.example.OnlineBuyTickets.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/admin/search")
public class AdminSearchController {

    private final TicketService ticketService;
    private final BusFlightService busFlightService;
    private final UserService userService;

    public AdminSearchController(TicketService ticketService, BusFlightService busFlightService, UserService userService) {
        this.ticketService = ticketService;
        this.busFlightService = busFlightService;
        this.userService = userService;
    }

    //получение данных с HTML(show_all_tickets.html)
    //и поиск tickets по  bus flights
    @PostMapping("/by_busflight")
    public String searchTicketsByBusFlight(@RequestParam("this_busFlight") String idStr, Model model){
        model.addAttribute("ThisBusFlight", "Bus Flight: "+busFlightService.findBusFlightByIdString(idStr).changeThisDateInString()+" -"+busFlightService.findBusFlightByIdString(idStr).getFinishCity());
        model.addAttribute("AllTicketsByBusFlight", ticketService.searchTicketsByBusFlightString(idStr));
        return "Admin/search_tickets_by_busflight";
    }

    //получение данных с HTML(show_all_tickets.html)
    //и поиск tickets по  users
    @PostMapping("/by_user")
    public String searchTicketsByUser(@RequestParam("this_user") String Username, Model model, Principal principal){
        model.addAttribute("NameUser",principal.getName());
        //список билетов по user
        model.addAttribute("AllTicketsByUser", ticketService.searchTicketsByUser(Username));
        //System.out.println(ticketService.searchTicketsByUser(Username));
        return "Admin/search_tickets_by_user";
    }
}
