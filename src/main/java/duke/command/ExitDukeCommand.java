package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitDukeCommand extends Command {

    /**
     * Constructor for class ExitDukeCommand
     *
     * @param userInput  user's input
     */
    public ExitDukeCommand(String userInput) {
        super(userInput);
    }

    /**
     * Sets the boolean exit to True, and prints out the closing message
     *
     * @param tasks contains the task list
     * @param ui deals with interactions with the user
     * @param storage deals with loading tasks from the file and saving tasks in the file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showClosingMessage();
        this.setExitTrue();
    }
}
