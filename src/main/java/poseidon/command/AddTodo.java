package poseidon.command;

import java.util.regex.Pattern;

import poseidon.exception.PoseidonException;
import poseidon.exception.PoseidonIncorrectCommandFormatException;
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

    public static final String CMD_USER_FORMAT = "todo 'description'";

    // Private constants dictating format of the command represented by this class.
    private static final String CMD_FORMAT = "(?i)todo.*";
    private static final String CMD_VALID_FORMAT = "(?i)todo\\s+\\S+.*";

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
    public String execute(Storage storage, TaskList taskList, Ui ui) throws PoseidonException {
        if (!Pattern.compile(CMD_VALID_FORMAT).matcher(cmdContent).matches()) {
            throw new PoseidonIncorrectCommandFormatException("TODO", CMD_USER_FORMAT);
        }

        String todoDescription = cmdContent.substring(4).trim();
        Todo newTodo = new Todo(todoDescription);

        storage.storeAdd(newTodo.toStorage());
        String message = taskList.addTask(newTodo);
        return ui.showMessage(message);
    }
}
