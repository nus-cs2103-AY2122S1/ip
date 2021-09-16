package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Class to delete a task from the current list of tasks.
 */
public class DeleteCommand implements Command {
    private String taskNumber;

    /**
     * Constructor to initialize DeleteCommand.
     *
     * @param number A string to be parsed to task number.
     */
    public DeleteCommand(String number) {
        this.taskNumber = number;
    }

    /**
     * Constructor to initialize DeleteCommand.
     */
    public DeleteCommand() { }
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
        try {
            int i = Integer.parseInt(taskNumber.trim()) - 1;
            String t = taskList.removeTask(i);
            return ui.echo("Noted. I removed this task: " + t);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Oops! Enter a valid task no. to delete the task.");
        }
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
        return "delete <int>  [delete a task in the list]";
    }
}
