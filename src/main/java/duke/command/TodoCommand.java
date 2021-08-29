package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;
import duke.task.Todo;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String USAGE_TEXT = "Usage: todo [description]";

    private String description;

    public TodoCommand(String desc) {
        description = desc;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Add new to do
        Task task = new Todo(description);
        taskList.addTask(task);
        ui.showTasksReply(true, task.toString(), taskList.size());
    }
}
