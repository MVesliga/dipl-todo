package hr.diplomski.todo.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "todo")
public class TodoItem {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private HrUser user;
    private String description;
    @Column(name = "creation_date")
    private Timestamp creationDate;
    private boolean completed;
}
