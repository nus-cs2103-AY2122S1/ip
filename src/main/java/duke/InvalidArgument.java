package duke;

public class InvalidArgument extends DukeException {
    InvalidArgument() {
        super("Aww, the input is incorrect, please try again");
    }
}
