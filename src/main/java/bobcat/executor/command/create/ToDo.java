package bobcat.executor.command.create;

import bobcat.model.TaskList;
import bobcat.model.task.Task;

public class ToDo extends CreationCommand {
    public static String[] execute(TaskList taskList, String... args) {
        Task addedTask = taskList.push(new bobcat.model.task.ToDo(args[1]));
        return new String[]{"Got it. I've added this task:",
                            "  " + addedTask.toString(),
                            "Now you have " + taskList.numTasks() + " tasks in the list"};
    }
}
