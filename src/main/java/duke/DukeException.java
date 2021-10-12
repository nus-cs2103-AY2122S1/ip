package duke;

public class DukeException extends Exception {
    private final String description;

    DukeException(String description) {
        this.description = description;
    }
}
