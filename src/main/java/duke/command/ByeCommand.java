package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Class to execute the exit command.
 */
public class ByeCommand implements Command {

    /**
     * Returns a String after executing appropriate commands.
     *
     * @param taskList TaskList to manage current user's tasks.
     * @param ui Ui to print messages to the user.
     * @param storage Storage to save and load tasks from disk.
     * @return String Duke's response to user.
     * @throws DukeException If there are input or parsing errors.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.save(taskList.getTasks());
        ui.exit();
        return "";
    }
    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "bye  [exit duke]";
    }
}
