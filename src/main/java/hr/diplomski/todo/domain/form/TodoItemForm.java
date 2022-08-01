package hr.diplomski.todo.domain.form;

import hr.diplomski.todo.domain.HrUser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class TodoItemForm {
    private Integer id;
    private HrUser user;
    @NotNull
    private String description;
    private boolean completed;
}
