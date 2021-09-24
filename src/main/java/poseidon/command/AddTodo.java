package poseidon.command;

import java.io.IOException;
import java.util.regex.Pattern;

import poseidon.exception.PoseidonException;
import poseidon.storage.Storage;
import poseidon.task.Todo;
import poseidon.tasklist.TaskList;
import poseidon.ui.Ui;

/**
 * Represents an {@code AddTodo} object that contains all the functionality of a command for adding a {@code Todo}
 * task to the {@code TaskList}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class AddTodo extends Command {

    // Private constants dictating format of the command represented by this class.
    private static final String CMD_FORMAT = "(?i)todo.*";
    private static final String CMD_VALID_FORMAT = "(?i)todo\\s+";

    /**
     * Constructs a new {@code AddTodo} object with the given {@code String}.
     *
     * @param cmdContent {@code String} that contains the content of the command.
     */
    public AddTodo(String cmdContent) {
        super(cmdContent);
    }

    /**
     * Returns whether the given {@code String} is in the format of the command represented by the class
     * {@code AddTodo} or not.
     *
     * @param cmdContent {@code String} that contains the content of the command to be checked.
     * @return {@code Boolean} - true if the {@code String} matches the command format of this class, else false.
     */
    public static boolean isThisCmd(String cmdContent) {
        return Pattern.compile(CMD_FORMAT).matcher(cmdContent).matches();
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws IOException {
        String[] strArr = Pattern.compile(CMD_VALID_FORMAT).split(cmdContent, 2);

        if (strArr.length == 1 && strArr[0].length() > 4) {
            throw new PoseidonException("There appears to be a typo in your TODO command.\n"
                    + "The command should be of the form:\n"
                    + "  todo 'description'\n"
                    + "Please try again.");
        }

        if (strArr.length == 1 || strArr[1].length() == 0) {
            throw new PoseidonException("The description of a TODO task cannot be empty.\nPlease try again.");
        }

        Todo newTodo = new Todo(strArr[1].trim());

        storage.storeAdd(newTodo.toStorage());
        String message = taskList.addTask(newTodo);
        return ui.showMessage(message);
    }
}
