package duke.command;

import task.TaskList;
import task.TaskTodo;

public class CommandAddTodo extends Command {

    private final TaskList taskList;
    private final String desc;

    public CommandAddTodo(TaskList tasklist, String desc) {
        this.commandName = "todo <string>";
        this.description = "Creates a to-do task";

        this.taskList = tasklist;
        this.desc = desc;
    }

    @Override
    public void execute() {
        taskList.add(new TaskTodo(desc, false));
    }
}
