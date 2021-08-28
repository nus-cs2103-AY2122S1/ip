package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.ui.Ui;

/**
 * Class that encapsulates the "Delete" Command.
 *
 * @author Wang Hong Yong
 */
public class DeleteCommand extends Command {
    private String input;

    /**
     * Constructor for DeleteCommand.
     *
     * @param tasklist Task Handler that handles the operation.
     * @param input command string to be executed.
     */
    public DeleteCommand(TaskList tasklist, String input){
        super(tasklist);
        this.input = input;
    }

    /**
     * Executes the "Delete" Command.
     */
    public void execute(){
        if (input.length() == 6) {
            throw new DukeException(Ui.getEmptyDescriptionMsg("delete"));
        }
        super.taskList.removeTask(Integer.parseInt(input.substring(7)));
    }
}
