package ru.itis.reflex.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "head_id")
    private User head;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}
