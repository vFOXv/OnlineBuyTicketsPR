package com.example.OnlineBuyTickets.services;

import com.example.OnlineBuyTickets.models.Passenger;
import com.example.OnlineBuyTickets.models.Ticket;
import com.example.OnlineBuyTickets.repositories.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PassengerService {
    public final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    //получение списка всех пассажиров из DB
    public List<Passenger> getAllPassengers(){
        return passengerRepository.findAll();
    }

    //получение пассажира по id
    public Passenger getPassengerById(Long id){
        return passengerRepository.findPassengerById(id);
    }

    //сохранение нового пассажира и билета(при покупке билета)
    public void saveNewPassenger(Passenger passenger){
        passengerRepository.save(passenger);
    }
}
