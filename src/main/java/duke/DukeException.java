package duke;

public class DukeException extends Exception {
    public DukeException() {
        super("Sorry, Duke cannot understand your command");
    }
}
