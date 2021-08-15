/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke: Incrementally building a Chatbot.
 *
 * SideException encapsulates the errors specific to Side when it encounters runtime errors.
 *
 * It contains the exceptions that are thrown due to wrong inputs given.
 *
 * @author Lua Yi Da
 */

public class SideException extends RuntimeException {
    String message;

    public SideException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}

class UnknownCommandException extends SideException {
    public UnknownCommandException() {
        super("No such command, what a drag...");
    }
}

class WrongFormatException extends SideException {
    public WrongFormatException(String format) {
        super("Follow this format, don't make this worse:\n  " + format);
    }
}

class TaskIndexException extends SideException {
    public TaskIndexException() {
        super("No such task, more energy wasted...");
    }
}

class DeleteIndexException extends SideException {
    public DeleteIndexException() {
        super("Can't delete what isn't there...");
    }
}

class NoIndexException extends SideException {
    public NoIndexException() {
        super("Can't do anything without task number...");
    }
}

class TooManyIndexesException extends SideException {
    public TooManyIndexesException(String input) {
        super("I only have 1 hand, I can only " + input + " 1 at a time...");
    }
}
