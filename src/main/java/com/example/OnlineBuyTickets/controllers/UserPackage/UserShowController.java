package com.example.OnlineBuyTickets.controllers.UserPackage;

import com.example.OnlineBuyTickets.services.BusFlightService;
import com.example.OnlineBuyTickets.services.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@RequestMapping("/show")
public class UserShowController {
    private final BusFlightService busFlightService;
    private final TicketService ticketService;

    public UserShowController(BusFlightService busFlightService, TicketService ticketService) {
        this.busFlightService = busFlightService;
        this.ticketService = ticketService;
    }

    //переход на HTML для показа всех рейсов
    @GetMapping("/allBusFlights")
    //Principal краткая информация о user
    public String showAllBusFlights(Model model, Principal principal){
        //обновление поля freeSeats во всех объектах BusFlight
        ticketService.changeFreeSeatsInBuses();
        model.addAttribute("NameUser",principal.getName());
        model.addAttribute("AllBusFlights", busFlightService.getAllBusFlights());
        model.addAttribute("ThisBusFlights", "Buy ticket on the bus flights");
        model.addAttribute("NotSeats", "All Seats Are Occupied!!!");
        return "Show/show_all_busflights";
    }

    //показ всех билетов user
    @GetMapping("/allTicketsUser")
    public String showAllTicketsUser(Principal principal, Model model){
        model.addAttribute("ThisUser", principal.getName());
        model.addAttribute("ThisTicket", "Show ticket");
        model.addAttribute("AllTicketsByUser",ticketService.searchTicketsByUser(principal.getName()));
        return "User/show_tickets_by_user";

    }

}
