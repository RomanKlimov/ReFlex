package ru.itis.reflex.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posture_data")
public class PostureData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "smooth_num")
    private int smoothNum;

    @Column(name = "flex_num")
    private int flexNum;

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

    public int getSmoothNum() {
        return smoothNum;
    }

    public void setSmoothNum(int smoothNum) {
        this.smoothNum = smoothNum;
    }

    public int getFlexNum() {
        return flexNum;
    }

    public void setFlexNum(int flexNum) {
        this.flexNum = flexNum;
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
