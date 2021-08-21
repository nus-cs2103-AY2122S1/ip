package duke;

import command.Command;
import storage.StorageFile;
import tasklist.TaskList;
import parser.Parser;
import ui.Ui;
import ui.message.Message;

import storage.Storage;
import exception.DukeException;

/**
 * Encapsulates a chatbot that greets the user,
 * adds valid inputs to a task list,
 * updates tasks in the list,
 * and exits when the user types `bye`.
 */
public class Duke {
    private StorageFile storageFile;
    private TaskList list;
    private Ui ui;
    private Parser parser;

    public Duke() {
        // Load data
        StorageFile storageFile = Storage.loadListFile();
        this.storageFile = storageFile;

        // Scan data to a list
        TaskList list = Storage.scanListFileDataToList(storageFile);
        this.list = list;

        this.ui = new Ui();
        this.parser = new Parser();
    }

    private void run() {
        // Check config
        if (this.storageFile == null || this.list == null) {
            return;
        }

        // Greet
        this.ui.showWelcome();

        // Process user inputs
        String inputMessage = this.ui.readInputMessage();
        while (!this.parser.detectExitCommand(inputMessage)) {
            Message outputMessage;

            try {
                Command command = this.parser.makeCommand(inputMessage);
                command.execute(this.list);
                outputMessage = command.getOutputMessage();
            } catch (DukeException e) {
                outputMessage = e.getOutputMessage();
            }

            // Print output message
            this.ui.showMessage(outputMessage);
            inputMessage = this.ui.readInputMessage();
        }

        // Exit
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
