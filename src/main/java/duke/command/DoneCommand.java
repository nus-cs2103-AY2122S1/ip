package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * This class deals with the finish task command.
 */
public class DoneCommand extends Command {
    private TaskList myTasks;
    private String next;

    /**
     * Constructs a new DoneCommand
     *
     * @param myTasks all tasks now
     * @param next instruction
     */
    public DoneCommand(TaskList myTasks, String next) {
        this.myTasks = myTasks;
        this.next = next;
    }

    /**
     * Executes instructions according to the Command type (here is finishing a task)
     */
    @Override
    public void execute() {
        if (next.length() > 5) {
            myTasks.getDone(next);
        } else {
            Ui.printWarning("☹ OOPS!!! Please enter a valid number, such as done 3");
        }
    }
}
