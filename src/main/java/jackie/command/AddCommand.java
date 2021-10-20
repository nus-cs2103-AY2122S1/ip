package jackie.command;

import java.util.Arrays;

/**
 * A Class that extends the Command class.
 * It is specifically designed for a Command for adding.
 *
 * @author Gu Geng
 */
public class AddCommand extends Command {
    private jackie.task.Task task;

    /**
     * Returns a AddCommand object with the information provided.
     *
     * @param command A String containing information that can possibility be used to create an AddCommand object.
     * @throws jackie.JackieException Will be thrown if information provided are insufficient/incorrect.
     */
    public AddCommand(String... command) throws jackie.JackieException {
        if (command.length == 1) {
            // guard clause
            throw new jackie.JackieException(" D: OOPS!!! The description of a task cannot be empty.");
        } else if (command[0].equals("todo")) {
            task = new jackie.task.Todo(command);
        } else if (Arrays.asList(command).contains("/") && command.length <= 3) {
            // guard clause
            throw new jackie.JackieException(
                    " D: HEY DEAR! Please enter a date after / following the task description");
        } else if (command[0].equals("deadline")) {
            task = new jackie.task.Deadline(command);
        } else if (command[0].equals("event")) {
            task = new jackie.task.Event(command);
        } else {
            assert false;
        }
    }

    /**
     * Implements the execute method from Command superclass.
     * Executes the given add command accordingly by updating taskList and storage, interacting with ui.
     * D:
     *
     * @param taskList A jackie.TaskList object that contains an ArrayList of jackie.task.task object to be updated.
     * @param ui A jackie.Ui object that helps to perform interaction when the command is executed.
     * @param storage A jackie.Storage object that helps to update the storage after the execution is done.
     * @return a String of system reply when given certain input under execution.
     * @throws jackie.JackieException Will be thrown if unable to locate/update the storage file.
     */
    public String execute(jackie.TaskList taskList, jackie.Ui ui, jackie.Storage storage)
            throws jackie.JackieException {
        taskList.addTask(task);
        System.out.println(ui.showAdd(task, taskList.size()));
        storage.updateStorage(taskList);
        return ui.showAdd(task, taskList.size());
    }

    /**
     * Implements the isExit method from Command superclass.
     * Returns a boolean indicating if the programme terminates after the add execution.
     *
     * @return A boolean indicating if the programme terminates after the add execution.
     */
    public boolean isExit() {
        return false;
    }
}
