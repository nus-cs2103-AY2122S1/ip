package duke;

public class DukeException extends Exception {
    public DukeException() {
        super("Sorry, duke.Duke cannot understand your command");
    }
}
