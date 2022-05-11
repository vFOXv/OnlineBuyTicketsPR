package com.example.OnlineBuyTickets.repositories;

import com.example.OnlineBuyTickets.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket getTicketById(Long id);
    //поиск билетов по рейсу(автобусу)
    List<Ticket> findTicketsByBusFlightId(Long id);
    //поиск билетов по user
    List<Ticket> findTicketsByUserUsername(String Username);
}
