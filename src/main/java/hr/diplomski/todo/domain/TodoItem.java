package hr.diplomski.todo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TodoItem {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private String title;
    private boolean completed;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TodoItem todoItem = (TodoItem) o;
        return completed == todoItem.completed
                && Objects.equals(id, todoItem.id)
                && Objects.equals(userId, todoItem.userId)
                && Objects.equals(title, todoItem.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, title, completed);
    }
}
