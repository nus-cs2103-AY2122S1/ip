import alice.AliceException;
import dialog.DialogException;
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
 * @version 0.01
 * @since 0.00
 *
 */
public class Alice {
    /** storage for Alice */
    private final Storage storage;
    /** taskDialog dealing with the task that the bot wants to communicate back to the user */
    private final TaskDialog taskDialog;
    /** ui of Alice interacting with the user from inputting command and showing the ui back to the user */
    private final Ui ui;

    /**
     * Constructor of Alice.
     *
     * @param fileName the filename without the suffix .txt, .TXT, etc.
     * @throws DialogException dialog cannot have the same id: same task with the same property (i.e. name, isDone)
     * cannot coexist while the app is running
     */
    public Alice(String fileName) throws DialogException {
        ui = new Ui();
        storage = new Storage(fileName);
        // import the task from what the storage manage to load
        ui.importTaskList(storage.load());
         // set the current taskDialog of Alice to the one ui fetch from the storage
         // in future update the taskDialog of Alice could change accordingly in case of reaching to other save file
        taskDialog =ui.getTaskDialog();
    }

    /**
     * The method for running the personal assistant Alice.
     *
     * @throws DialogException dialog cannot have the same id while the app is running
     */
    public void run() throws DialogException {
        ui.showCurrentList();
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskDialog, storage);
                isExit = c.isExit();
            } catch (AliceException | IOException e) {
                Ui.printError(e);
            }

        }
    }

    /**
     * Main method of Alice.
     * First show welcome message then ask the user to choose a save file or create a new one.
     * When the user has chosen the save file location, run Alice application.
     *
     * @param args user arguments
     * @throws DialogException dialog cannot have the same id while the app is running
     * @throws IOException if there is any error dealing with the system IO
     */
    public static void main(String[] args) throws DialogException, IOException {
        Ui.showWelcome();
        boolean isGo;
        String saveFileName;
        do {
            isGo = true;
            Ui.printSelectSaveFile();
            saveFileName = Ui.fastRead();
            if (!Storage.contains(saveFileName)) {
                Ui.showConfirmCreateNewFile(saveFileName);
                isGo = Parser.yesNoToBoolean(Ui.fastRead());
            }
        } while (!isGo);
        new Alice(saveFileName).run();
    }
}
