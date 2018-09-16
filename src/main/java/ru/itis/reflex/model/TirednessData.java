package ru.itis.reflex.model;

import javax.persistence.*;
import java.util.Date;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMorningValue() {
        return morningValue;
    }

    public void setMorningValue(int morningValue) {
        this.morningValue = morningValue;
    }

    public int getEveningValue() {
        return eveningValue;
    }

    public void setEveningValue(int eveningValue) {
        this.eveningValue = eveningValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
