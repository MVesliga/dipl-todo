package hr.diplomski.todo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "hr_user")
public class HrUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<TodoItem> todoList;
}
