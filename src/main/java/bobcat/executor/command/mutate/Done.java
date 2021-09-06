package bobcat.executor.command.mutate;

import bobcat.model.TaskList;
import bobcat.model.task.Task;

public class Done extends MutationCommand {
    public static String[] execute(TaskList taskList, String... args) {
        Task markedTask = taskList.markDone(Integer.parseInt(args[1]));
        return new String[]{"Nice! I've marked this task as done:", "  " + markedTask.toString()};
    }
}
