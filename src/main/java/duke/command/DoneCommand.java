package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Class used to mark a task as completed.
 */
public class DoneCommand implements Command {
    private String number;

    /**
     * Constructor to initialize DoneCommand.
     *
     * @param no A string to be parsed to task number.
     */
    public DoneCommand(String no) {
        this.number = no;
    }

    /**
     * Constructor to initialize DoneCommand.
     */
    public DoneCommand() {}
    /**
     * Method to execute command.
     *
     * @param taskList TaskList that manages all current tasks.
     * @param ui Ui used to print messages.
     * @param storage Loads and saves the tasks to a txt file.
     * @throws DukeException Thrown if there are input/parsing errors.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int i = Integer.parseInt(number.trim()) - 1;
            String t = taskList.markDone(i);
            ui.echo("Nice! I marked this task as completed: " + t);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Oops! Enter a valid task no. to complete the task.");
        }
    }

    /**
     * Returns a boolean to determine if Duke should stop running.
     *
     * @return A boolean false As this is not an exit command.
     */
    @Override
    public boolean isRunning() {
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
        return "done <int>  [mark a task in the list as completed]";
    }
}
