package com.example.OnlineBuyTickets.repositories;

import com.example.OnlineBuyTickets.models.BusFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusFlightRepository extends JpaRepository<BusFlight,Long> {
    BusFlight findBusFlightById(Long id);
}
