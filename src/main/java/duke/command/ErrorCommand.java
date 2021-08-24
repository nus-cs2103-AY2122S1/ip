package duke.command;

import duke.general.Storage;
import duke.general.Tasklist;
import duke.general.Ui;

/**
 * Command that is being sent when the input is incorrect
 */
public class ErrorCommand extends Command {
    @Override
    public void execute(Tasklist tasks, Storage storage, Ui ui) {
        System.out.println("Command inputted is not a valid command!");
    }
}
