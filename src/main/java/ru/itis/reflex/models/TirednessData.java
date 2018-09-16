package ru.itis.reflex.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "tiredness_data")
public class TirednessData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "morning_val")
    private int morningValue;

    @Column(name = "evening_val")
    private int eveningValue;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Date date;

}
