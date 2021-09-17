package duke.commands;

import duke.DukeStorage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Todo;

/**
 * Class that contains the to-do command
 *
 */
public class TodoCommand extends Command {

    /** The description of the to-do command */
    private String description;

    /**
     * Constructor for the to do command class
     *
     * @param description The description of the to-do command
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the to-do command
     *
     * @param taskList The list of tasks that is associated with the instance of Duke
     * @param ui The UI that is associated with the instance of Duke
     * @param storage The storage that is associated with the instance of Duke
     * @return Duke's String response
     */
    @Override
    public String execute(TaskList taskList, Ui ui, DukeStorage storage) {
        Todo todo = new Todo(this.description);
        taskList.add(todo);
        return ui.addedMessage(taskList, todo);
    }
}
