/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke: Incrementally building a Chatbot.
 *
 * The DukeException is an exception that is thrown when Duke is run.
 *
 * It contains the exceptions that are thrown due to wrong inputs given and is specific to this version of Duke.
 * Names of exception should be self-explanatory in describing when it should be used.
 *
 * @author Benedict Chua
 */
public class DukeException extends RuntimeException {
    String message;

    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}

class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException() {
        super("BAKA! The description of a task cannot be empty!");
    }
}

class MissingArgumentException extends DukeException {
    public MissingArgumentException(String tag, String type) {
        super(String.format("BAKA! You're missing the %s argument to add a %s!", tag, type));
    }
}

class InvalidCommandException extends DukeException {
    public InvalidCommandException(String command) {
        super(String.format("BAKA! I don't understand this command! %s", command));
    }
}

class InvalidIndexException extends DukeException {
    public InvalidIndexException(int numOfTasks) {
        super(String.format("BAKA! Input a valid index!! You have %d tasks currently!", numOfTasks));
    }
}