package com.example.OnlineBuyTickets.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name = "bus_flights")
public class BusFlight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Город назначения
    @Column(name="finish_city")
    private String finishCity;

    //Время и дата отправления со станции
    @DateTimeFormat(style="yyyy.MM.dd HH:mm")
    @Column(name="flight_departure")
    private Date flightDeparture;

    //количество мест в автобусе
    private Integer seats;

    //Set билетов(не объявляем) мапиться в Ticket
    //Set<Ticket> tickets;

    //метод позволяющий преобразовывать BusFlight в select(HTML) в читабильный вид
    public String myShow(){
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm");
        String myView = formatForDateNow.format(getFlightDeparture())+" - to "+getFinishCity();
        return myView;
    }

    //метод приводящий дату к читабельному виду(форматирование даты)
    public String changeThisDateInString() {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm");
        return formatForDateNow.format(getFlightDeparture());
    }
}
