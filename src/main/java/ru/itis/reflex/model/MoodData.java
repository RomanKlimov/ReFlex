package ru.itis.reflex.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mood_data")
public class MoodData {

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
