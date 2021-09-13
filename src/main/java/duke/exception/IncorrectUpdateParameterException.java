package duke.exception;

import duke.command.UpdateCommand;

public class IncorrectUpdateParameterException extends IncorrectParameterSuppliedException {

    /**
     * The constructor method for DukeException.
     */
    public IncorrectUpdateParameterException() {
        super(UpdateCommand.COMMAND_WORD, new String[]{
                UpdateCommand.COMMAND_WORD
                , "/i id_of_the_task"
                , "/n new_name_of_task"
                , "/d new_date_of_task"
        });
    }
}
