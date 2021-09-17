package bobcat.executor.command.mutate;

import bobcat.model.TaskList;
import bobcat.model.task.Task;

public class Done extends MutationCommand {
    /**
     * Tags a <code>Task</code> as completed
     * @param taskList <code>TaskList</code> to be modified
     * @param args id of <code>Task</code> to be marked as completed in the <code>TaskList</code>
     * @return Array of Strings to display
     */
    public static String[] execute(TaskList taskList, String... args) {
        Task markedTask = taskList.markDone(Integer.parseInt(args[1]));
        return new String[]{"Nice! I've marked this task as done:", "  " + markedTask.toString()};
    }
}
