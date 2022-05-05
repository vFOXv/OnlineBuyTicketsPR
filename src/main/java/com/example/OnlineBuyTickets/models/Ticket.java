package com.example.OnlineBuyTickets.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name="tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //номер места в автобусе
    private Integer place;

    //рейс
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="bus_flight_id")
    private BusFlight busFlight;

    //билет принадлежит пассажиру  --> mappedBy="ticket"
    //данные о билетах храняться в таблице пассажиров
    //mappedBy="ticket" --> имя поля в классе Passenger по которому происходит мапинг
    @OneToOne (mappedBy="ticket", cascade=CascadeType.MERGE)
    //@JoinColumn (name="passenger_id")
    private Passenger passenger;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

}
