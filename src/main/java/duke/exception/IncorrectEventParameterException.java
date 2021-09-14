package duke.exception;

import duke.command.EventCommand;

public class IncorrectEventParameterException extends IncorrectParameterSuppliedException {

    /**
     * The constructor method for DukeException.
     *
     */
    public IncorrectEventParameterException() {
        super(EventCommand.COMMAND_WORD, new String[]{EventCommand.COMMAND_WORD + " name_here",
            "/at date_here"});
    }
}
