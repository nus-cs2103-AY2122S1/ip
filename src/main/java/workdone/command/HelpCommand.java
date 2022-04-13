package workdone.command;

import workdone.data.Storage;
import workdone.data.TaskList;

/**
 * Represents a command that asks for help. A subclass of the Command class.
 */
public class HelpCommand extends Command {
    /**
     * Constructor of the class `HelpCommand`.
     */
    public HelpCommand() {
        super("help");
    }

    /**
     * Executes the command. Updates help message.
     *
     * @param tasks A list of tasks.
     * @param storage An instance of Storage that can read from and write to the hard disk.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        // Update message
        this.message = "All Commands:\n"
                + "Add a task:\n"
                + "todo {task description} - add a task to be completed\n"
                + "deadline {task description} /by yyyy-MM-dd HH:mm - add a task with a deadline\n"
                + "event {event description} /at yyyy-MM-dd HH:mm to yyyy-MM-dd HH:mm - add an event\n"
                + "Finish a task:\n"
                + "done {task no.} - mark a task as done\n"
                + "undone {task no.} - mark a task as undone\n"
                + "Delete tasks:\n"
                + "delete {task no.} - delete a task by specifying its task number\n"
                + "clear - delete all tasks from the task list\n"
                + "View all tasks:\n"
                + "list - list all tasks added\n"
                + "Find a task:\n"
                + "find {keyword} - find tasks containing `keyword`\n"
                + "Undo:\n"
                + "undo - undo the last command\n"
                + "Exit:\n"
                + "bye - exit the program\n";
    }
}
