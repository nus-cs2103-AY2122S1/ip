package alice;

import dialog.DialogException;
import dialog.TaskDialog;

import parser.Parser;
import storage.Storage;
import command.Command;
import ui.StartPage;
import ui.Ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Main class of the chatbot.
 * By running the main method the bot will first ask for the save file and asking if
 * the user want to create a new save file location before proceeding.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.01
 * @since 0.00
 */
public class Alice extends Application{
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
        ui.importTaskList(storage.load());
        // set the current taskDialog of alice.Alice to the one ui fetch from the storage
        // in future update the taskDialog of alice.Alice could change accordingly in case of reaching to other save file
        taskDialog = ui.getTaskDialog();
    }

    public Storage getStorage() {
        return this.storage;
    }

    public Ui getUi() {
        return this.ui;
    }

    /**
     * The method for running the personal assistant alice.Alice.
     *
     * @throws DialogException dialog cannot have the same id while the app is running
     */
    public void run() throws DialogException {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskDialog, storage);
                isExit = c.isExit();
            } catch (AliceException e) {
                taskDialog.getChatPage().printError(e);
            }
        }
    }

    public void execute(String fullCommand) {
        Command c = Parser.parse(fullCommand);
        c.execute(taskDialog, storage);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Alice");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        if (!Storage.haveSaveLocation()) {
            Storage.createSaveLocation();
        }

        // Add the scene to the stage
        stage.setScene(new StartPage().layout());
        // Display
        stage.show();
    }
}
