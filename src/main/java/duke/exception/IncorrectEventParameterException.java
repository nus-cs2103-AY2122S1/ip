package duke.exception;

import duke.command.EventCommand;

/**
 * An exception to indicate the incorrect Event Task parameters provided.
 */
public class IncorrectEventParameterException extends IncorrectParameterSuppliedException {

    /**
     * Constructor method to initialise IncorrectEventParameterException
     */
    public IncorrectEventParameterException() {
        super(EventCommand.COMMAND_WORD, new String[]{EventCommand.COMMAND_WORD + " name_here",
            "/at date_here"});
    }
}
