package duke.command;

import duke.TaskList;
import duke.CommandResult;
import duke.task.Task;
import duke.task.Todo;

public class TodoCommand extends Command implements TaskListAddable {

    public static final String COMMAND_WORD = "todo";
    private final String command;

    public TodoCommand(TaskList taskList, String command) {
        super(taskList);
        this.command = command;
    }

    @Override
    public CommandResult execute() {
        TaskList taskList = super.getTaskList();
        Task todo = new Todo(this.command, false);
        String feedback = addTaskToTaskList(taskList, todo);
        return new CommandResult(feedback, false);
    }

    @Override
    public String addTaskToTaskList(TaskList taskList, Task task) {
        taskList.addTask(task);
        return "Got it. I've added this task:\n  "
                + task.getDetails()
                + "\n"
                + printListNumber(taskList);
    }
}
