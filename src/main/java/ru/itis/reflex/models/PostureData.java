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
@Table(name = "posture_data")
public class PostureData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_of_photos")
    private int photosNum;

    @Column(name = "flex_num")
    private int flexNum;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Date date;

}
