package duke.command;

import duke.exception.InvalidInputException;
import duke.exception.SaveFileException;
import duke.exception.TaskNotFoundException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * A command class encapsulating the logic that occurs when the user issues a 'delete' command.
 */
public class DeleteCommand extends Command {
    /** The index of the todo to delete */
    private final String todoNumber;

    /**
     * Constructor for the DeleteCommand
     *
     * @param todoNumber The index of the todo to delete
     */
    public DeleteCommand(String todoNumber) {
        super(false);
        this.todoNumber = todoNumber;
    }

    /**
     * Deletes a task from the tasks
     *
     * @param tasks List of existing tasks
     * @param ui User interface current interacting with the user
     * @param storage Storage class handling the persistence of the tasks
     * @throws TaskNotFoundException if task does not exist
     * @throws InvalidInputException if input cannot be parsed into a number
     * @throws SaveFileException if there are issues with the save file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskNotFoundException,
            InvalidInputException, SaveFileException {
        try {
            int taskNumber = Integer.parseInt(todoNumber);
            if (taskNumber <= tasks.size() && taskNumber > 0) {
                Task taskToDelete = tasks.get(taskNumber - 1);
                tasks.remove(taskNumber - 1);
                ui.showTaskRemoved(taskToDelete, tasks);
                storage.save(tasks);
            } else {
                throw new TaskNotFoundException("The task chosen does not exist. Use 'list' to see all your tasks.");
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Command 'delete' require an integer as the second parameter");
        }
    }
}
