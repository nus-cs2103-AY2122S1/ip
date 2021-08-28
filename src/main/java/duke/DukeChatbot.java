package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import duke.command.Command;
import duke.command.CommandParser;
import duke.command.DukeInvalidCommandException;
import duke.storage.StorageHandler;
import duke.task.TaskHandler;
import duke.ui.Ui;

/**
 * Represents the Duke chatbot.
 *
 * @author Jay Aljelo Saez Ting
 */
public class DukeChatbot {

    private Ui ui;
    private CommandParser commandParser;
    private StorageHandler storageHandler;
    private TaskHandler taskHandler;
    private boolean hasErrorOnSave;

    /**
     * Creates a Duke chatbot.
     */
    public DukeChatbot() {
    }

    /**
     * Runs the Duke chatbot.
     */
    public void run() {
        try {
            initialise();
            ui.printGreeting();
            listenForInput();
        } catch (IOException e) {
            ui.printUnexpectedErrorMessage();
        }
    }

    private void initialise() throws IOException {
        ui = new Ui();
        commandParser = new CommandParser();
        storageHandler = StorageHandler.getInstance();
        taskHandler = new TaskHandler(storageHandler.loadTasks());
        taskHandler.addTasksListUpdateObserver(tasks -> {
            try {
                storageHandler.saveTasks(tasks);
            } catch (IOException e) {
                hasErrorOnSave = true;
            }
        });
        hasErrorOnSave = false;
    }

    private void listenForInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command;
        Command commandInstance;
        do {
            try {
                command = br.readLine();
                commandInstance = commandParser.getCommandInstance(command);
                commandInstance.execute(taskHandler, ui);
                if (hasErrorOnSave) {
                    ui.printUnexpectedErrorMessage();
                    break;
                }
            } catch (IOException e) {
                ui.printUnexpectedErrorMessage();
                break;
            } catch (DukeInvalidCommandException e) {
                ui.printInvalidCommandErrorMessage(e.getMessage());
                commandInstance = null;
            }
        } while (commandInstance == null || !commandInstance.mustExit());
    }
}
