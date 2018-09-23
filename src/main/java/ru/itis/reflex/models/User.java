package ru.itis.reflex.models;

import lombok.*;
import ru.itis.reflex.security.Role.Role;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "\"user\"")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"company"})
//@EqualsAndHashCode(exclude = {"company"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "head")
    private List<Key> keys;

    @Override
    public int hashCode(){
        return Math.toIntExact(this.getId());
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof User) {
            User u = (User)o;
            if (   u.getEmail().equals(this.getEmail()) && this.getId() == ((User) o).getId()  ){
                return true;
            }
        }
        return false;
    }

}
