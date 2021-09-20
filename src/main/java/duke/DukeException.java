package duke;

/**
 * Represents exceptions thrown by Duke Personal Assistant Chat-bot.
 */
public class DukeException extends Exception {

    DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "DukeException: " + this.getMessage();
    }
}

/**
 * Represents exception where task description is not found.
 */
class DescriptionNotFoundException extends DukeException {

    DescriptionNotFoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "DescriptionNotFoundException: " + this.getMessage();
    }
}

/**
 * Represents exception where ToDo task description is not found.
 */
class ToDoDescriptionNotFoundException extends DescriptionNotFoundException {

    ToDoDescriptionNotFoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "ToDoDescriptionNotFoundException: " + this.getMessage();
    }
}

/**
 * Represents exception where Deadline task description is not found.
 */
class DeadlineDescriptionNotFoundException extends DescriptionNotFoundException {

    DeadlineDescriptionNotFoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "DeadlineDescriptionNotFoundException: " + this.getMessage();
    }
}

/**
 * Represents exception where Event task description is not found.
 */
class EventDescriptionNotFoundException extends DescriptionNotFoundException {

    EventDescriptionNotFoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "EventDescriptionNotFoundException: " + this.getMessage();
    }
}

/**
 * Represents exception where an invalid command is entered.
 */
class InvalidCommandException extends DukeException {

    InvalidCommandException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "InvalidCommandException: " + this.getMessage();
    }
}

/**
 * Represents exception where Deadline was not entered.
 */
class DeadlineNotFoundException extends DukeException {

    DeadlineNotFoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "DeadlineNotFoundException: " + this.getMessage();
    }
}

/**
 * Represents exception where Event date and time was not entered.
 */
class EventTimeNotFoundException extends DukeException {

    EventTimeNotFoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "EventTimeNotFoundException: " + this.getMessage();
    }
}

/**
 * Represents exception where task index is out of range.
 */
class TaskIndexOutOfBoundException extends DukeException {

    TaskIndexOutOfBoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "TaskIndexOutOfBoundException: " + this.getMessage();
    }
}

/**
 * Represents exception where task index is out of range.
 */
class InvalidTaskTypeException extends DukeException {

    InvalidTaskTypeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "InvalidTaskTypeException: " + this.getMessage();
    }
}

/**
 * Represents exception where priority level is invalid.
 */
class InvalidPriorityException extends DukeException {

    InvalidPriorityException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "InvalidPriorityException: " + this.getMessage();
    }
}
