package alice;

import alice.exceptions.AliceException;
import dialog.exceptions.DialogException;

import parser.Parser;
import storage.Storage;
import command.Command;
import ui.Ui;

import java.io.IOException;

/**
 * Main class of the chatbot.
 * By running the main method the bot will first ask for the save file and asking if
 * the user want to create a new save file location before proceeding.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.03
 * @since 0.02
 */
public class Alice {
    /** storage for alice.Alice */
    private final Storage storage;

    /** ui of alice.Alice interacting with the user from inputting command and showing the ui back to the user */
    private final Ui ui;


    public Alice() throws DialogException, IOException {
        this("test");
    }

    /**
     * Constructor of alice.Alice.
     *
     * @param fileName the filename without the suffix .txt, .TXT, etc.
     * @throws IOException if there is issue loading taskList from storage
     */
    public Alice(String fileName) throws IOException {
        ui = new Ui();
        storage = new Storage(fileName);
        // import the task from what the storage manage to load
        ui.setTaskList(storage.loadTaskList());
        // set the current taskDialog of alice.Alice to the one ui fetch from the storage
    }

    /**
     * Getter for Ui of Alice
     *
     * @return Alice
     */
    public Ui getUi() {
        return this.ui;
    }

    /**
     * Execute the fullCommand.
     * Execute the fullCommand by parsing the command
     * and executing it without caring if it is exit or not.
     *
     * @param fullCommand the command as string including taggers and date
     */
    public void execute(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            c.execute(ui, storage);

        } catch (AliceException e) {
            ui.getChatPage().printError(e);
        }
    }

}
