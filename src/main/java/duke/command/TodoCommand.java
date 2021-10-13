package duke.command;

import duke.IncompleteCommandException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.Todo;

/**
 * TodoCommand instructions to add task into the program.
 */
public class TodoCommand extends Command {

    /**
     * constructor for TodoCommand.
     *
     * @param input String command.
     */
    public TodoCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IncompleteCommandException {
        if (input.length() <= 5) {
            throw new IncompleteCommandException("OOPS!!! The description of a todo cannot be empty.");
        }
        String taskMessage = input.substring(5);
        Task task = new Todo(taskMessage.strip());

        taskList.addTask(task);
        return ui.taskAddedMessage(task, taskList.getTotalNumberOfTask());
    }


}
