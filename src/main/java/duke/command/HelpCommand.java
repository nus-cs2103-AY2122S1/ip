package duke.command;

import java.util.ArrayList;
import java.util.Collections;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Class used to execute help command.
 */
public class HelpCommand implements Command {

    private ArrayList<Command> commandList = new ArrayList<>();

    /**
     * Execute Help command.
     *
     * @param taskList Manages all current tasks.
     * @param ui       Used to print messages.
     * @param storage  Loads and saves the tasks to a txt file.
     * @throws DukeException Thrown if there are input/parsing errors.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Collections.addAll(commandList, new ListCommand(), new AddTaskCommand(), new FindCommand(),
                new DoneCommand(), new DeleteCommand(), new HelpCommand(), new ByeCommand(), new HelpCommand());
        ui.printAll(commandList, "Command Help: \n");
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
        return "help  [find details of all features]";
    }
}
