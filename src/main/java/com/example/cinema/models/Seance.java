package com.example.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor@NoArgsConstructor
public class Seance  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date heureDebut;

}
