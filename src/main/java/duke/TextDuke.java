package duke;

import java.io.IOException;

import duke.logic.commands.Command;
import duke.logic.commands.CommandResult;
import duke.logic.commands.ExitCommand;
import duke.logic.parser.Parser;
import duke.logic.tasks.TaskList;
import duke.storage.Storage;
import duke.ui.TextUi;

/**
 * Entry point for the text-based version of Duke. Initializes the program and runs in the CLI.
 */
public class TextDuke {
    private TextUi textUi;
    private Parser parser;
    private TaskList taskList;

    public static void main(String[] args) {
        new TextDuke().run();
    }

    /** Runs the program in three steps: start, loop for input, end. */
    private void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Sets up the required objects, loads the data from the storage file, and displays the welcome message.
     */
    private void start() {
        textUi = new TextUi();
        parser = new Parser();
        try {
            taskList = new TaskList(Storage.load());
        } catch (IOException e) {
            textUi.showLoadingError(e.getMessage());
            taskList = new TaskList();
        }
        textUi.showWelcomeMessage();
    }

    /**
     * Reads the user command, executes it and displays the result until exit signal.
     */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = textUi.getUserCommand();
            textUi.showHorizontalLine();
            command = parser.parse(userCommandText);
            command.setTaskList(taskList);
            CommandResult result = command.execute();
            textUi.showResultToUser(result);
            textUi.showHorizontalLine();
            textUi.showBlankLine();
        } while (!(command instanceof ExitCommand));
    }

    /**
     * Saves current data into the file, displays the goodbye message and closes the program.
     */
    private void exit() {
        try {
            Storage.save(taskList.getSaveFormat());
        } catch (IOException e) {
            textUi.showSavingError(e.getMessage());
        }
        textUi.showGoodbyeMessage();
        textUi.close();
        System.exit(0);
    }
}
