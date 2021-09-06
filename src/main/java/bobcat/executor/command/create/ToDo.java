package bobcat.executor.command.create;

import bobcat.model.TaskList;
import bobcat.model.task.Task;

public class ToDo extends CreationCommand {
    /**
     * Adds a todo to the provided <code>TaskList</code>, with <code>args</code> being the specifications
     * of the todo
     * @param taskList <code>TaskList</code> to be added to
     * @param args Description of the <code>ToDo</code> to be added
     * @return Array of Strings to display
     */
    public static String[] execute(TaskList taskList, String... args) {
        Task addedTask = taskList.push(new bobcat.model.task.ToDo(args[1]));
        return new String[]{"Got it. I've added this task:",
                            "  " + addedTask.toString(),
                            "Now you have " + taskList.numTasks() + " tasks in the list"};
    }
}
