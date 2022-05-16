package com.example.OnlineBuyTickets.services;


import com.example.OnlineBuyTickets.models.BusFlight;
import com.example.OnlineBuyTickets.models.Ticket;
import com.example.OnlineBuyTickets.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final BusFlightService busFlightService;

    public TicketService(TicketRepository ticketRepository, BusFlightService busFlightService) {
        this.ticketRepository = ticketRepository;
        this.busFlightService = busFlightService;
    }

    //получение списка всех билетов из DB
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    //получение билета по id
    public Ticket getTicketById(Long id) {
        return ticketRepository.getTicketById(id);
    }

    //удаление билета по id
    public void deleteTicket(Long id) {
        Ticket ticket = getTicketById(id);
        ticketRepository.delete(ticket);
    }

    //поиск tickets по busFlight(при входящем id в виде String)
    public List<Ticket> searchTicketsByBusFlightString(String idStr) {
        //перевод String in Long (id  принадлежит busFlight)
        Long id = Long.parseLong(idStr);
        List<Ticket> tickets = ticketRepository.findTicketsByBusFlightId(id);
        return tickets;
    }

    //поиск tickets по user
    public List<Ticket> searchTicketsByUser(String Username) {
        List<Ticket> tickets = ticketRepository.findTicketsByUserUsername(Username);
        return tickets;
    }

    //создание списка свободных мест на рейсе(автобусе --> id)
    public Set<Integer> searchTicketsByBusFlight(Long id) {
        //список купленых билетов
        List<Ticket> listTickets = ticketRepository.findTicketsByBusFlightId(id);
        //список мест из этих билетов
        HashSet<Integer> placesTicket = new HashSet<>();
        for (Ticket ticket : listTickets) {
            placesTicket.add(ticket.getPlace());
        }
        //список всех мест в атобусе
        HashSet<Integer> placesBus = new HashSet<>();
        for (int i = 1; i <= busFlightService.getBusFlightById(id).getSeats(); i++) {
            placesBus.add(i);
        }
        //получение списка свободных мест в автобусе
        for (Ticket ticket : listTickets) {
            placesBus.remove(ticket.getPlace());
        }
        return placesBus;
    }

    //сохранение билета в DB
    public void saveTicketInDB(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    //получение и изменение количества свободных мест в конкретном автобусе
    public int getFreeSeatsInBus(Long id) {
        int freeSeats = searchTicketsByBusFlight(id).size();
        return freeSeats;
    }

    //получение и изменение количества свободных мест во всех автобусах
    public void changeFreeSeatsInBuses() {
        List<BusFlight> buses = busFlightService.getAllBusFlights();
        for(int i = 0;i< buses.size();i++){
            BusFlight bus = buses.get(i);
            int freeSeats = getFreeSeatsInBus(bus.getId());
            bus.setFreeSeats(freeSeats);
            busFlightService.saveNewBusFlight(bus);
        }
    }
}
