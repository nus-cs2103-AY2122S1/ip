package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A Class that extends the Command Class.
 * It is specifically designed for a Command for undoing operation.
 *
 * @author Gu Geng
 */
public class UndoCommand extends Command {

    /**
     * Returns a UndoCommand object.
     *
     * @param command A String array containing information
     *                that can possibly be used to create an UndoCommand object.
     * @throws DukeException Will be thrown if information provided are insufficient/incorrect.
     */
    public UndoCommand(String... command) throws DukeException {
        if (!(command.length == 1 && command[0].trim().equals("undo"))) {
            throw new DukeException("D: Something wrong with this undo operation");
        }
    }

    /**
     * Implements the execute method from Command superclass.
     * Executes the given undo command accordingly by updating taskList and storage, interacting with ui.
     * Returns a String of system reply when given certain input under execution.
     *
     * @param taskList A duke.TaskList object that contains an ArrayList of duke.task.task object to be updated.
     * @param ui A duke.Ui object that helps to perform interaction when the command is executed.
     * @param storage A duke.Storage object that helps to update the storage after the execution is done.
     * @return a String of system reply when given certain input under execution.
     * @throws duke.DukeException Will be thrown if unable to locate/update the storage file.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws duke.DukeException {
        boolean ifUndone = taskList.ifUndone();
        storage.updateStorage(taskList);
        System.out.println(ui.showUndo(ifUndone, taskList));
        return ui.showUndo(ifUndone, taskList);
    }

    /**
     * Implements the isExit method from Command superclass.
     * Returns a boolean indicating if the programme terminates after the undo execution.
     *
     * @return A boolean indicating if the programme terminates after the undo execution.
     */
    public boolean isExit() {
        return false;
    }
}
