package duke.commands;

public class CommandTodo extends Command {
    public static final String HELP_COMMAND = "todo";
    public static final String HELP_DESCRIPTION = "Add a new todo task";
    public static final String HELP_USAGE = "Usage: todo task_name\n"
            + "Add a new todo task\n"
            + "\ttask_name\tname of the task to add";
}
