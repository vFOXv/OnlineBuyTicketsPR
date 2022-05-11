package com.example.OnlineBuyTickets.controllers.UserPackage;

import com.example.OnlineBuyTickets.models.BusFlight;
import com.example.OnlineBuyTickets.models.Passenger;
import com.example.OnlineBuyTickets.models.Ticket;
import com.example.OnlineBuyTickets.services.BusFlightService;
import com.example.OnlineBuyTickets.services.PassengerService;
import com.example.OnlineBuyTickets.services.TicketService;
import com.example.OnlineBuyTickets.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user/action")
public class UserActionController {
    private final UserService userService;
    private final BusFlightService busFlightService;
    private final TicketService ticketService;
    private final PassengerService passengerService;


    public UserActionController(UserService userService, BusFlightService busFlightService, TicketService ticketService, PassengerService passengerService) {
        this.userService = userService;
        this.busFlightService = busFlightService;
        this.ticketService = ticketService;
        this.passengerService = passengerService;
    }

    //переход на страницу HTML добавление билета юзером(покупка билета)
    //в URL передаеться id  для заполнения поля BusFlight
    @GetMapping("/newTicket/{id}")
    public String buyTicketUserToHTML(@PathVariable("id") Long id, Model model, Principal principal){
        model.addAttribute("NameUser", principal.getName());

        Passenger passenger = new Passenger();
        //инициализация поля ticket в объекте passenger
        Ticket ticket = new Ticket();
        passenger.setTicket(ticket);
        //Инициализация поля BusFlight в объесте Ticket
        passenger.getTicket().setBusFlight(busFlightService.getBusFlightById(id));
        model.addAttribute("NewPassenger", passenger);
        //список свободных мест в автобусе
        model.addAttribute("freePlaces", ticketService.searchTicketsByBusFlight(id));
        return "User/buy_new_ticket";
    }

    //получение данных с HTML и запись новой пары пассажира+билет в DB
    @PostMapping("/newTicket")
    public String savePassengerAndTicketInDB(@RequestParam("place") Integer place,
                                             @RequestParam("idBusFlight") Long idBusFlight,
                                             @ModelAttribute("NewPassenger") Passenger newPassenger,
                                             Principal principal, Model model){
        //инициализация полей пассажира (ticket and busFlight)
        Ticket ticket = new Ticket();
        BusFlight busFlight = new BusFlight();
        ticket.setBusFlight(busFlight);
        newPassenger.setTicket(ticket);
        //запись ticket in passenger
        //запись рейса в билет
        newPassenger.getTicket().setBusFlight(busFlightService.getBusFlightById(idBusFlight));
        //запись места в билет
        newPassenger.getTicket().setPlace(place);
        //запись юзера в билет
        newPassenger.getTicket().setUser(userService.findByUserName(principal.getName()));

        //сохранение билета в DB
        ticketService.saveTicketInDB(ticket);
        //сохранение пассажира в DB
        passengerService.saveNewPassenger(newPassenger);

    return "redirect:/show/allBusFlights";
    }

    //переход на HTML где показываються конкретный билет
    @GetMapping("/ticket/{id}")
    public String showThisTicket(@PathVariable("id") Long id, Model model, Principal principal){
        Ticket ticket = ticketService.getTicketById(id);
        model.addAttribute("ticket", ticket);
        model.addAttribute("NameUser", principal.getName());
        model.addAttribute("Delete", "DELETE");
        return "User/show_this_ticket";
    }

    //удаление билета по id (если пассажир сдал билет)
    @DeleteMapping("/deleteThisTicket/{id}")
    public String deleteThisTicketById(@PathVariable("id") Long id){
        ticketService.deleteTicket(id);
        return "redirect:/show/allBusFlights";
    }

}
