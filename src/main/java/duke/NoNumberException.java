package duke;

public class NoNumberException extends DukeException {

    private static String NO_NUMBER_MSG = String.format("Please enter a number after ");

    public NoNumberException(String task) {
        super(NO_NUMBER_MSG + task + "!");
    }
}
