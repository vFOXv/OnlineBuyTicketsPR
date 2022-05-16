package com.example.OnlineBuyTickets.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.text.ParseException;
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
    @Column(name = "finish_city")
    private String finishCity;

    //Время и дата отправления со станции
    @Column(name = "flight_departure")
    private Date flightDeparture;

    //количество мест в автобусе
    private Integer seats;

    //количество свободных мест в атобусе
    @Column(name = "free_seats")
    private Integer freeSeats;

    //метод позволяющий из строки(получаемой из HTML (создание/изменение объекта))
    //распарсить в Date (yyyy-MM-dd'T'HH:mm)       'T' - удаляеться
    public void setFlightDepartureStringT(String text) throws ParseException {
        //yyyy-MM-dd HH:mm ----> количество пробелов между символами ВАЖНО!!!
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String textDate = text.substring(0, 10)+" "+ text.substring(11);
        this.flightDeparture = format.parse(textDate);
    }

    //Set билетов(не объявляем) мапиться в Ticket
    //Set<Ticket> tickets;

    //метод позволяющий преобразовывать BusFlight в select(HTML) в читабильный вид
    public String myShow() {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm");
        String myView = formatForDateNow.format(getFlightDeparture()) + " - to " + getFinishCity();
        return myView;
    }

    //метод приводящий дату к читабельному виду(форматирование даты)
    public String changeThisDateInString() {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm");
        return formatForDateNow.format(getFlightDeparture());
    }
}
