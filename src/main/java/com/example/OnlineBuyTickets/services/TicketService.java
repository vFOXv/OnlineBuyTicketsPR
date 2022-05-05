package com.example.OnlineBuyTickets.services;

import com.example.OnlineBuyTickets.models.Ticket;
import com.example.OnlineBuyTickets.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    //получение списка всех билетов из DB
    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    //получение билета по id
    public Ticket getTicketById(Long id){
        return ticketRepository.getTicketById(id);
    }

    //удаление билета по id
    public void deleteTicket(Long id){
        Ticket ticket = getTicketById(id);
        ticketRepository.delete(ticket);
    }

    //поиск tickets по busFlight(при входящем id в виде String)
    public List<Ticket> searchTicketsByBusFlightString(String idStr){
        //перевод String in Long (id  принадлежит busFlight)
        Long id = Long.parseLong(idStr);
        List<Ticket> tickets = ticketRepository.findTicketsByBusFlightId(id);
        return tickets;
    }

    //поиск tickets по user
    public List<Ticket> searchTicketsByUser(String Username){
        List<Ticket> tickets = ticketRepository.findTicketsByUserUsername(Username);
        return tickets;
    }
}
