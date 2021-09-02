package duke.commands;

import duke.TaskList;

/**
 * This class handles command for deleting tasks.
 */
public class DeleteCommand implements Command {
    private int index;

    /**
     * Constructor which takes in index of the task in TaskList to delete.
     *
     * @param index int to represent index of task.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList) {
        String output = String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                taskList.deleteFromList(index), taskList.taskCount());
        return output;
    }
}
