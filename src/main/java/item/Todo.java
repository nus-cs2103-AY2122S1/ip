package item;

import exception.BotException;
import exception.EmptyCommandException;

public class Todo extends Task {

    private Todo(String description) throws BotException {
        super(description);
    }

    public static Todo of(String description) throws BotException {
        if (description.isEmpty()) {
            throw new EmptyCommandException("todo");
        } else {
            return new Todo(description);
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
}
