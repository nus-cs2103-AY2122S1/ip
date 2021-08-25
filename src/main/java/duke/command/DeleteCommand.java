package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * This class deals with the deleting task command.
 */
public class DeleteCommand extends Command{
    private TaskList myTasks;
    private String next;

    /**
     * Constructs a new DeleteCommand
     *
     * @param myTasks all tasks now
     * @param next instruction
     */
    public DeleteCommand(TaskList myTasks, String next) {
        this.myTasks = myTasks;
        this.next = next;
    }

    /**
     * Executes instructions according to the Command type (here is deleting a task)
     */
    @Override
    public void execute() {
        if (next.length() > 7) {
            myTasks.getDelete(next);
        } else {
            Ui.myPrint("☹ OOPS!!! Please enter a valid number, such as delete 3");
        }
    }
}
