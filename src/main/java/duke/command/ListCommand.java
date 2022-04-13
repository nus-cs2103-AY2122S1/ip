package duke.command;

import duke.TaskList;
import duke.Ui;


/**
 * Contains the objects and methods necessary for a List Command.
 *
 * @author Toh Wang Bin
 */
public class ListCommand implements Command {

    private final TaskList taskList;

    /**
     * Constructor for a ListCommand instance.
     *
     * @param taskList The TaskList instance associated with the command.
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the command unique to this Command class.
     *
     * @return A string representing the response to running this command.
     */
    public String execute() {
        //if list is empty
        if (taskList.getTotalTasks() == 0) {
            return Ui.getNoTaskError();
        }
        //repeatedly append tasks then return them
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < taskList.getTotalTasks(); i++) {
            int listNumber = i + 1;
            String taskString = listNumber + ". " + taskList.getTask(i).toString() + "\n";
            str.append(taskString);
        }
        return str.toString();
    }

}
