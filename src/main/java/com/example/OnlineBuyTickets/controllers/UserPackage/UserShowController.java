package com.example.OnlineBuyTickets.controllers.UserPackage;

import com.example.OnlineBuyTickets.models.BusFlight;
import com.example.OnlineBuyTickets.services.BusFlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/show")
public class UserShowController {
    private final BusFlightService busFlightService;

    public UserShowController(BusFlightService busFlightService) {
        this.busFlightService = busFlightService;
    }

    //переход на HTML для показа всех рейсов
    @GetMapping("/allBusFlights")
    //Principal краткая информация о user
    public String showAllBusFlights(Model model, Principal principal){
        model.addAttribute("NameUser",principal.getName());
        model.addAttribute("AllBusFlights", busFlightService.getAllBusFlights());
        model.addAttribute("ThisBusFlights", "This bus flights");
        return "Show/show_all_busflights";
    }
}
