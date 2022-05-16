package com.example.OnlineBuyTickets.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name="passengers")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, message = "Name min 2 char!!!")
    @NotEmpty(message = "String must not be empty!!!")
    private String lastname;

    @Size(min = 2, message = "Name min 2 char!!!")
    @NotEmpty(message = "String must not be empty!!!")
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
