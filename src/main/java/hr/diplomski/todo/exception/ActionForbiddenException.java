package hr.diplomski.todo.exception;

public class ActionForbiddenException extends RuntimeException {
    public ActionForbiddenException() {
    }

    public ActionForbiddenException(String message) {
        super(message);
    }

    public ActionForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }
}
