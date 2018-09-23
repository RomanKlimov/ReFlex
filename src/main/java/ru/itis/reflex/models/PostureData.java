package ru.itis.reflex.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

}
