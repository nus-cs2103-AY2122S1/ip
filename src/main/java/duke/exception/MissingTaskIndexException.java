package duke.exception;

public class MissingTaskIndexException extends DukeException {

    public MissingTaskIndexException() {
        super("Task index is not specified!\n"
                + "Please follow this format: [done/delete] [index]");
    }
}
