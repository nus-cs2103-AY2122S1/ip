package src.main.java.duke;

public class TimeNotFoundException extends DukeException {

    String message;

    TimeNotFoundException(String message) {
        super(message);
    }
}
