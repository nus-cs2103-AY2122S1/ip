package duke.commands;

import duke.TaskList;

/**
 * This class handles command for clearing the list of task.
 */
public class ClearCommand implements Command {
    private TaskList taskList;

    /**
     * Constructor which takes in TaskList to delete the tasks inside.
     *
     * @param taskList TaskList of tasks to be cleared.
     */
    public ClearCommand(TaskList taskList) {
        this.taskList = taskList;
    }
    private void deleteAllTasks() {
        int count = taskList.taskCount();
        for (int i = 0; i < count; i++) {
            taskList.deleteFromList(1);
        }
    }

    @Override
    public String execute(TaskList taskList) {
        deleteAllTasks();
        String output = String.format("Noted. I've removed all your existing tasks\n"
                + "Now you have %d tasks in the list.", taskList.taskCount());
        return output;
    }
}
