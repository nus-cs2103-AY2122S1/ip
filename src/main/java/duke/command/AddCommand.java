package duke.command;

/**
 * A Class that extends the Command class.
 * It is specifically designed for a Command for adding.
 *
 * @author Gu Geng
 */
public class AddCommand extends Command {
    private duke.task.Task task;

    /**
     * Returns a AddCommand object with the information provided.
     *
     * @param command A String containing information that can possibility be used to create an AddCommand object.
     * @throws duke.DukeException Will be thrown if information provided are insufficient/incorrect.
     */
    public AddCommand(String command) throws duke.DukeException {
        String[] holder = command.trim().split(" ");
        if (holder.length == 1) {
            throw new duke.DukeException(" ☹ OOPS!!! The description of a task cannot be empty.");
        } else {
            if (holder[0].equals("todo")) {
                task = new duke.task.Todo(command);
            } else {
                if (command.trim().split("/").length == 1) {
                    throw new duke.DukeException(
                            " ☹ HEY DEAR! Please enter a date after / following the task description");
                }
                if (holder[0].equals("deadline")) {
                    task = new duke.task.Deadline(command);
                } else {
                    task = new duke.task.Event(command);
                }
            }
        }
    }

    /**
     * Implements the execute method from Command superclass.
     * Executes the given add command accordingly by updating taskList and storage, interacting with ui.
     *
     * @param taskList A duke.TaskList object that contains an ArrayList of duke.task.task object to be updated.
     * @param ui A duke.Ui object that helps to perform interaction when the command is executed.
     * @param storage A duke.Storage object that helps to update the storage after the execution is done.
     * @throws duke.DukeException Will be thrown if unable to locate/update the storage file.
     */
    public void execute(duke.TaskList taskList, duke.Ui ui, duke.Storage storage) throws duke.DukeException {
        taskList.addTask(task);
        ui.showAdd(task, taskList.size());
        storage.updateStorage(taskList);
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
