package jarvis;

import jarvis.command.Command;
import jarvis.exception.JarvisException;
import jarvis.message.OutputMessage;
import jarvis.parser.Parser;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;
import javafx.application.Platform;

/**
 * Encapsulates the Jarvis chatbot.
 */
public class Jarvis {
    private TaskList taskList;
    private Storage storage;
    private Ui ui = new Ui();

    /**
     * Constructor for Jarvis.
     *
     * @param storageFilePath Storage file path name.
     */
    public Jarvis(String storageFilePath) {
        try {
            this.storage = new Storage(storageFilePath);
            this.taskList = new TaskList(storage.loadTasksFromFile());
        } catch (JarvisException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets Jarvis' response for a given user input. To be used only in GUI mode.
     *
     * @param userInput The user input
     * @return Jarvis response
     */
    public String getResponse(String userInput) {
        try {
            if (Parser.shouldExit(userInput)) {
                Platform.exit();
            }

            Command command = Command.createCommand(userInput);
            return command.execute(taskList, storage, ui).getFormattedMessage();
        } catch (JarvisException e) {
            return ui.getErrorMessage(e).getFormattedMessage();
        }
    }

    /**
     * Gets the greeting message to be shown to the user when in GUI mode.
     *
     * @return Formatted greeting message.
     */
    public String getGreetingMessage() {
        return ui.getGreetingMessage().getFormattedMessage();
    }

    /**
     * Launches the application in command line interface mode.
     */
    public void launchInCliMode() {
        Ui.showFormattedOutputMessage(ui.getGreetingMessage());
        this.processCliInput();
    }

    private void processCliInput() {
        String userInput = ui.readInput();

        // Run the CLI application until user types exit command
        while (true) {
            try {
                if (Parser.isUserInputEmpty(userInput)) {
                    userInput = ui.readInput();
                    continue;
                }
                if (Parser.shouldExit(userInput)) {
                    break;
                }

                Command command = Command.createCommand(userInput);
                OutputMessage message = command.execute(taskList, storage, ui);
                Ui.showFormattedOutputMessage(message);
            } catch (JarvisException e) {
                Ui.showFormattedOutputMessage(ui.getErrorMessage(e));
            }
            userInput = ui.readInput();
        }

        Ui.showFormattedOutputMessage(ui.getExitMessage());
        ui.closeScanner();
    }
}
