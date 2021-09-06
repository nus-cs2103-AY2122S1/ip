package bobcat.executor.command.mutate;

import bobcat.model.TaskList;
import bobcat.model.task.Task;

public class Delete extends MutationCommand {
    public static String[] execute(TaskList taskList, String... args) {
        Task deletedTask = taskList.deleteTaskByIdx(Integer.parseInt(args[1]));
        return new String[]{"Noted. I've removed this task:",
                            "  " + deletedTask.toString(),
                            "Now you have " + taskList.numTasks() + " tasks in the list"};
    }
}
