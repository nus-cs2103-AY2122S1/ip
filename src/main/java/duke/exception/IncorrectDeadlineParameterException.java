package duke.exception;

import duke.command.DeadlineCommand;

public class IncorrectDeadlineParameterException extends IncorrectParameterSuppliedException {

    /**
     * The constructor method for DukeException.
     *
     */
    public IncorrectDeadlineParameterException() {
        super(DeadlineCommand.COMMAND_WORD, new String[]{
                DeadlineCommand.COMMAND_WORD + " name_here"
                , "/by date_here"});
    }
}
