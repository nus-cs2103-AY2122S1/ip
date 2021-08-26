package duke;

import duke.command.Command;
import duke.exception.BruhException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * The main class for the Bruh chatbot program.
 */
public class Bruh {
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    public Bruh(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = storage.loadTaskList();
    }

    /**
     * Initializes the chatbot & performs cleanup after the chatbot is exited normally.
     */
    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readUserInput();
                Command command = Parser.parseInputToCommand(userInput);
                command.execute(taskList, ui, storage);
                ui.showCommandDescription(command);
                isExit = command.isExit();
            } catch (BruhException e) {
                ui.showErrorMessage(e.getMessage());
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                ui.showErrorMessage("Please specify a valid task number (use 'list' to view your tasks).");
            }
        }
        exit();
    }

    /**
     * Cleanup routines after the chatbot is exited normally.
     */
    private void exit() {
        storage.saveToDisk(taskList);
    }

    public static void main(String[] args) {
        final String STORAGE_PATH = "/output/tasklist.txt";
        new Bruh(STORAGE_PATH).run();
    }
}
