package duke.commands;

import duke.TaskList;

/**
 * THis class handles command meant for marking tasks as done.
 */
public class DoneCommand implements Command {
    private int index;

    /**
     * Constructor which takes in index of task in TaskList to mark as done.
     *
     * @param index int to represent index of task.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList) {
        String output = "Nice! I've marked this task as done:\n" + taskList.taskDone(index);
        return output;
    }
}
