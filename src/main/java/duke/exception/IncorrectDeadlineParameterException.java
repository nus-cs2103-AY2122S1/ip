package duke.exception;

import duke.command.DeadlineCommand;

/**
 * An exception to indicate the incorrect Deadline Task parameters provided.
 */
public class IncorrectDeadlineParameterException extends IncorrectParameterSuppliedException {

    /**
     * Constructor method to initialise IncorrectDeadlineParameterException
     */
    public IncorrectDeadlineParameterException() {
        super(DeadlineCommand.COMMAND_WORD, new String[]{DeadlineCommand.COMMAND_WORD + " name_here",
            "/by date_here"});
    }
}
