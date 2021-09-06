package bloom.command;

import bloom.app.TaskList;
import bloom.constant.Message;
import bloom.task.ToDo;

/**
 * Represents a to-do command which
 * creates a to-do task with given description.
 */
public class ToDoCommand extends Command {

    /** The description of the to-do. */
    private final String description;

    /**
     * Constructor for a ToDoCommand.
     *
     * @param description the description of the to-do
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Creates a to-do task.
     */
    @Override
    public String run() {
        ToDo todo = new ToDo(this.description);
        TaskList.add(todo);
        return Message.COMMAND_ADD.getMessage() + "\t   " + todo + "\n";
    }
}
