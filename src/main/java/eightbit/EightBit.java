package eightbit;

import eightbit.command.Command;
import eightbit.util.Parser;
import eightbit.util.Storage;
import eightbit.util.TaskList;
import eightbit.util.Ui;

/**
 * Represents the chat bot.
 */
public class EightBit {

    private String filepath;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor.
     *
     * @param filepath Path of file to store the tasks in local machine.
     */
    public EightBit(String filepath) {
        this.filepath = filepath;
        this.storage = new Storage(filepath);
        this.taskList = new TaskList(storage.loadFileContents());
        this.ui = new Ui();
    }

    /**
     * Starts the chat bot.
     * Displays a welcome message, takes in user inputs, and displays exit message at the end.
     */
    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (EightBitException e) {
                ui.printError(e);
            }
        }

        ui.showBye();
    }
}
