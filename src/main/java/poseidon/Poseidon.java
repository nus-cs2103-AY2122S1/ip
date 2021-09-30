package poseidon;

import poseidon.command.Command;
import poseidon.exception.PoseidonException;
import poseidon.exception.PoseidonStorageException;
import poseidon.exception.PoseidonStorageReadWriteException;
import poseidon.parser.Parser;
import poseidon.storage.Storage;
import poseidon.tasklist.TaskList;
import poseidon.ui.Ui;


/**
 * Represents a {@code Poseidon} object that executes the user commands.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class Poseidon {

    /** {@code Storage} object that reads from and writes onto the hard disk  */
    private Storage storage;

    private String storageLoadExceptionMsg = "";

    /** {@code TaskList} object that maintains and updates the list of tasks */
    private TaskList taskList;

    /** {@code Ui} object to be used to generate responses to User commands */
    private Ui ui;

    /**
     * Constructs a {@code Poseidon} object and initialises the class members.
     */
    public Poseidon() {
        ui = new Ui();
        try {
            storage = new Storage();
            taskList = new TaskList(storage.load());
        } catch (PoseidonStorageException | PoseidonStorageReadWriteException ex) {
            storageLoadExceptionMsg = ex.getMessage();
            System.out.println(storageLoadExceptionMsg);
            storage = null;
            taskList = new TaskList();
        }
    }

    /**
     * Returns a {@code String} representation of a welcome message as prepared by {@code Ui} class.
     *
     * @return {@code String} welcome message.
     */
    public String runWelcome() {
        return ui.getWelcomeMessage();
    }

    /**
     * Returns a {@code String} representation of the Bot's response to the given new User command.
     *
     * @param newCommand {@code String} version of the User input.
     * @return {@code String} response.
     */
    public String run(String newCommand) {
        if (storage == null) {
            return ui.showException(storageLoadExceptionMsg);
        }

        String exceptionMsg = "";
        try {
            Command command = Parser.parse(newCommand);
            return command.execute(storage, taskList, ui);
        } catch (PoseidonException ex) {
            exceptionMsg = ui.showException(ex.getMessage());
        }
        assert exceptionMsg.length() != 0 : "Exception message supposed to contain readable text";
        return exceptionMsg;
    }

    /**
     * Returns true if the given user input is a "bye" command.
     *
     * @param newCommand {@code String} version of the user input.
     * @return {@code Boolean} validation result.
     */
    public boolean isBye(String newCommand) {
        return Parser.isParsedBye(newCommand);
    }
}
