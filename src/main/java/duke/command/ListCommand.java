package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.ui.Ui;

/**
 * A ListCommand class encapsulates the instructions for Duke to list the tasks saved.
 */
public class ListCommand extends Command {

    public ListCommand() {

    }

    /**
     * executes the command on the specified tasklist
     *
     * @param taskList tasklist to be operated on
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList){
        Ui.showTaskList(taskList);
    }

    /**
     * returns the type of command
     *
     * @return list
     */
    @Override
    public String getType() {
        return "list";
    }

}
