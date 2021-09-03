package duke.command;

import java.io.IOException;

import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

/**
 * A class that represents the command when the user types in 'todo', 'deadline' or 'event'.
 */
public class AddCommand extends Command {

    /**
     * Creates an AddCommand, which relies on user inputs to function.
     *
     * @param input A string containing the user's input.
     */
    public AddCommand(String input) {
        super(input);
    }

    /**
     * Adds a todo/deadline/event task and saves the new task list to the save file.
     *
     * @param taskList   The current list of tasks from the user.
     * @param ui      An object that handles all UI related functionality. (e.g. printing)
     * @param storage An object that handles all save/load related functionality.
     * @return A task list with the new task added and an output message.
     * @throws IOException If an error occurs during the save operation.
     */
    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        String[] inputs = Parser.splitWith(input, 0, " ",
                "The description of a todo/deadline/event task cannot be empty.");
        TaskList newTaskList = taskList.addTask(ui, inputs);
        storage.saveTasksToFile(newTaskList);
        return newTaskList;
    }
}
