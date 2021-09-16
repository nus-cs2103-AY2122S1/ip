package duke.command;

import java.io.IOException;

import javafx.util.Pair;

import duke.result.Result;
import duke.result.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;


/**
 * A class that represents the command when the user types in 'todo', 'deadline' or 'event'.
 */
public class AddTaskCommand extends Command {

    /**
     * Creates an AddTaskCommand, which relies on user inputs to function.
     *
     * @param input A string containing the user's input.
     */
    public AddTaskCommand(String input) {
        super(input);
    }

    /**
     * Adds a todo/deadline/event task and saves the new task list to the save file.
     *
     * @param taskList   The current list of tasks from the user.
     * @param ui      An object that handles all UI related functionality. (e.g. printing)
     * @param storage An object that handles all save/load related functionality.
     * @return A Result object containing task and alias data, as well as an output message.
     * @throws IOException If an error occurs during the save operation.
     */
    @Override
    public Result execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        // This splits up the input into task attributes.
        // (i.e. Task type, task name, if the task is done or not, date/time)
        String[] splitInputs = Parser.splitWith(input, 0, " ",
                "The description of a todo/deadline/event task cannot be empty.");
        // This pair contains the updated task list along with an output message
        Pair<TaskList, String> taskListPair = taskList.addTask(ui, splitInputs);
        TaskList newTaskList = taskListPair.getKey();
        storage.saveTasksToFile(newTaskList);
        String message = taskListPair.getValue();
        return new Result(newTaskList, message);
    }
}
