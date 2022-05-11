package com.example.OnlineBuyTickets.repositories;

import com.example.OnlineBuyTickets.models.BusFlight;
import com.example.OnlineBuyTickets.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BusFlightRepository extends JpaRepository<BusFlight,Long> {
    BusFlight findBusFlightById(Long id);
    //поиск всех билетов(Set<id>) купленых на это рейс(автобус)

}
