package bobcat.executor.command.create;

import bobcat.model.TaskList;
import bobcat.model.task.Task;

public class Deadline extends CreationCommand {
    /**
     * Adds a deadline to the provided <code>TaskList</code>, with <code>args</code> being the specifications
     * of the deadline
     * @param taskList <code>TaskList</code> to be added to
     * @param args Description and deadline of the <code>Deadline</code> to be added
     * @return Array of Strings to display
     */
    public static String[] execute(TaskList taskList, String... args) {
        assert args.length >= 3;
        Task addedTask = taskList.push(new bobcat.model.task.Deadline(args[1], args[2]));
        return new String[]{"Got it. I've added this task:",
                            "  " + addedTask.toString(),
                            "Now you have " + taskList.numTasks() + " tasks in the list"};
    }
}
