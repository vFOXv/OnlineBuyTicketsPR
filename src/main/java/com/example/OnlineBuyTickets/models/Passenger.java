package com.example.OnlineBuyTickets.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;



@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name="passengers")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lastname;

    private String name;

    @OneToOne (cascade=CascadeType.MERGE)
    @JoinColumn (name="ticket_id")
    private Ticket ticket;

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", name='" + name + '\'' +
                '}' +"\n";
    }
}
