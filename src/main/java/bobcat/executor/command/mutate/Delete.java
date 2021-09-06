package bobcat.executor.command.mutate;

import bobcat.model.TaskList;
import bobcat.model.task.Task;

public class Delete extends MutationCommand {
    /**
     * Deletes a given <code>Task</code> from the provided <code>TaskList</code>, with <code>args</code> being the
     * id of the <code>Task</code> in the <code>TaskList</code>
     * of the deadline
     * @param taskList <code>TaskList</code> to be modified
     * @param args second position of array is id of the <code>Task</code> to be deleted
     * @return Array of Strings to display
     */
    public static String[] execute(TaskList taskList, String... args) {
        assert args.length > 2;
        Task deletedTask = taskList.deleteTaskByIdx(Integer.parseInt(args[1]));
        return new String[]{"Noted. I've removed this task:",
                            "  " + deletedTask.toString(),
                            "Now you have " + taskList.numTasks() + " tasks in the list"};
    }
}
