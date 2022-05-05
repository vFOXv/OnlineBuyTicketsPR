package com.example.OnlineBuyTickets.services;

import com.example.OnlineBuyTickets.models.BusFlight;
import com.example.OnlineBuyTickets.repositories.BusFlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusFlightService {

    private final BusFlightRepository busFlightRepository;

    public BusFlightService(BusFlightRepository busFlightRepository) {
        this.busFlightRepository = busFlightRepository;
    }

    //получение списка всех рейсов из DB
    public List<BusFlight> getAllBusFlights(){
        return busFlightRepository.findAll();
    }

    //получение рейса по id(String id )
    public BusFlight findBusFlightByIdString(String idStr){
        //перевод String in Long
        Long id = Long.parseLong(idStr);
        return busFlightRepository.getById(id);
    }

    //сохранение нового рейса в DB
    public void saveNewBusFlight(BusFlight busFlight){
        busFlightRepository.save(busFlight);
    }
}
