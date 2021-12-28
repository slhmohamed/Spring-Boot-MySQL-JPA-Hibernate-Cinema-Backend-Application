package com.example.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor@NoArgsConstructor
public class Place  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;
    private double longitude,latidude,altitude;
    @ManyToOne
    private Salle salle;
    @OneToMany(mappedBy = "palace")
    private Collection<Ticket> tickets;
}
