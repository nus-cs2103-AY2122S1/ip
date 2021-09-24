package poseidon.command;

import java.io.IOException;

import poseidon.storage.Storage;
import poseidon.tasklist.TaskList;
import poseidon.ui.Ui;

/**
 * Represents an abstract {@code Command} class that contains all the functionality of a command that can be
 * executed.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public abstract class Command {

    /** {@code String} containing the contents of the command represented by an instance of this class */
    protected final String cmdContent;

    protected Command(String cmdContent) {
        this.cmdContent = cmdContent;
    }

    /**
     * Returns false because {@code Command} is an abstract class that has no command format attached/related to it.
     * Serves as a sample method for sub-classes to implement.
     *
     * @param cmdContent {@code String} that contains the content of the command to be checked.
     * @return {@code Boolean} - false.
     */
    public static boolean isThisCmd(String cmdContent) {
        return false;
    }

    /**
     * Returns a {@code String} representation of the Bot's response as a result of executing a User command represented
     * by this class, by making use of the given {@code Storage}, {@code TaskList} and {@code Ui} objects.
     *
     * @param storage {@code Storage} object to be used for writing to a file on the local hard disk.
     * @param taskList {@code TaskList} object to be used for task list related operations (if any).
     * @param ui {@code Ui} object to be used for crafting messages from the Bot to the User.
     * @return {@code String} reponse message.
     * @throws IOException Errors related to IO operations (reading/writing to a file) involving {@code Storage} object.
     */
    public abstract String execute(Storage storage, TaskList taskList, Ui ui) throws IOException;
}
