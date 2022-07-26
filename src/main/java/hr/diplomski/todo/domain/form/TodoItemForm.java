package hr.diplomski.todo.domain.form;

import hr.diplomski.todo.domain.HrUser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class TodoItemForm {
    private Integer id;
    private HrUser user;
    private String description;
    private Timestamp creationDate;
    private boolean completed;
}
