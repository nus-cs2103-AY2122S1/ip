package alice;

import alice.exceptions.AliceException;
import dialog.exceptions.DialogException;
import dialog.TaskDialog;

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
    /** taskDialog dealing with the task that the bot wants to communicate back to the user */
    private final TaskDialog taskDialog;
    /** ui of alice.Alice interacting with the user from inputting command and showing the ui back to the user */
    private final Ui ui;


    public Alice() throws DialogException, IOException {
        this("test");
    }

    /**
     * Constructor of alice.Alice.
     *
     * @param fileName the filename without the suffix .txt, .TXT, etc.
     * @throws DialogException dialog cannot have the same id: same task with the same property (i.e. name, isDone)
     *                         cannot coexist while the app is running
     */
    public Alice(String fileName) throws IOException {
        ui = new Ui();
        storage = new Storage(fileName);
        // import the task from what the storage manage to load
        ui.importTaskList(storage.loadTaskList());
        // set the current taskDialog of alice.Alice to the one ui fetch from the storage
        taskDialog = ui.getTaskDialog();
    }

    /**
     * Getter for the storage of Alice
     *
     * @return storage
     */
    public Storage getStorage() {
        return this.storage;
    }

    /**
     * Getter for Ui of Alice
     *
     * @return Alice
     */
    public Ui getUi() {
        return this.ui;
    }

    public TaskDialog getTaskDialog() {
        return this.taskDialog;
    }

    /**
     * The method for running the personal assistant alice.Alice.
     *
     * @throws DialogException dialog cannot have the same id while the app is running
     */
    public void run() throws DialogException {
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(taskDialog, storage);
            isExit = c.isExit();

        }
    }


    /**
     * Execute the fullCommand
     *
     * @param fullCommand the command as string including taggers and date
     */
    public void execute(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            c.execute(taskDialog, storage);
        } catch (AliceException e) {
            taskDialog.getChatPage().printError(e);
        }
    }
}
