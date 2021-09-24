package poseidon.command;

import java.io.IOException;

import poseidon.storage.Storage;
import poseidon.tasklist.TaskList;
import poseidon.ui.Ui;

/**
 * Represents an {@code Fail} object that contains all the functionality of a command that implies that there is no
 * valid command matching the User input.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class Fail extends Command {

    /**
     * Constructs a new {@code Fail} object with the given {@code String}.
     *
     * @param cmdContent {@code String} that contains the content of the command.
     */
    public Fail(String cmdContent) {
        super(cmdContent);
    }

    /**
     * Returns true by default because every command is a Fail command, unless another Command sub-class has a matching
     * command format. To be used with extreme caution (ideally only after checking against all other known Command
     * sub-classes).
     *
     * @param cmdContent {@code String} that contains the content of the command to be checked.
     * @return {@code Boolean} - True.
     */
    public static boolean isThisCmd(String cmdContent) {
        return true;
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws IOException {
        return ui.showCommandFail();
    }
}
