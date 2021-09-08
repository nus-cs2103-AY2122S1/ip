package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Class to delete a task from the current list of tasks.
 */
public class DeleteCommand implements Command {
    private String text;

    /**
     * Constructor to initialize DeleteCommand.
     *
     * @param s A string to be parsed to task number.
     */
    public DeleteCommand(String s) {
        this.text = s;
    }

    /**
     * Constructor to initialize DeleteCommand.
     */
    public DeleteCommand() { }
    /**
     * Execute command.
     *
     * @param taskList TaskList that manages all current tasks.
     * @param ui Ui used to print messages.
     * @param storage Loads and saves the tasks to a txt file.
     * @throws DukeException Thrown if there are input/parsing errors.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int i = Integer.parseInt(text.trim()) - 1;
            String t = taskList.removeTask(i);
            ui.echo("Noted. I removed this task: " + t);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Oops! Enter a valid task no. to delete the task.");
        }
    }

    /**
     * Returns a boolean to determine if Duke should stop running.
     *
     * @return A boolean false as this is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
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
