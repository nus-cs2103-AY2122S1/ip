package duke;

public class DukeException extends Exception{

    DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "DukeException: " + this.getMessage();
    }
}

class DescriptionNotFoundException extends DukeException{

    DescriptionNotFoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "DescriptionNotFoundException: " + this.getMessage();
    }
}

class ToDoDescriptionNotFoundException extends DescriptionNotFoundException{

    ToDoDescriptionNotFoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "ToDoDescriptionNotFoundException: " + this.getMessage();
    }
}

class DeadlineDescriptionNotFoundException extends DescriptionNotFoundException{

    DeadlineDescriptionNotFoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "DeadlineDescriptionNotFoundException: " + this.getMessage();
    }
}

class EventDescriptionNotFoundException extends DescriptionNotFoundException{

    EventDescriptionNotFoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "EventDescriptionNotFoundException: " + this.getMessage();
    }
}

class InvalidCommandException extends DukeException {

    InvalidCommandException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "InvalidCommandException: " + this.getMessage();
    }
}

class DeadlineNotFoundException extends DukeException {

    DeadlineNotFoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "DeadlineNotFoundException: " + this.getMessage();
    }
}

class EventTimeNotFoundException extends DukeException {

    EventTimeNotFoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "EventTimeNotFoundException: " + this.getMessage();
    }
}

class TaskIndexOutOfBoundException extends DukeException {

    TaskIndexOutOfBoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "TaskIndexOutOfBoundException: " + this.getMessage();
    }
}