package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.command.Command;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;


/**
 * The driver class for the Duke chat bot.
 */
public class Duke {
    /**
     * Object to handle UI-related functions (e.g. printing messages and lines)
     */
    private final Ui ui;
    /**
     * Object to handle loading from/save to a save file
     */
    private final Storage storage;
    /**
     * Object to represent the user's task list (e.g. add/delete/mark as done)
     */
    private TaskList taskList;
    private String initMessage;
    private boolean isTerminated = false;

    /**
     * Creates a Duke chat bot instance, using a file path for loading/saving.
     *
     * @param filePath Relative path to the location of the save file.
     */
    public Duke(Path filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        initMessage = "";
        try {
            taskList = new TaskList(storage.loadTasksFromFile());
        } catch (FileNotFoundException e) {
            initMessage = ui.showFileNotFoundError();
            taskList = new TaskList();
        } catch (Exception e) {
            initMessage = ui.showLoadingError(e.getMessage());
            taskList = new TaskList();
        }
        initMessage += ui.showIntroduction();
    }

    /**
     * Creates a Duke chat bot instance, using a default file path for loading/saving.
     */
    public Duke() {
        this(Paths.get(System.getProperty("user.dir"), "data", "tasks.txt"));
    }

    /**
     * Returns the messages generated on the initialization of Duke.
     *
     * @return A string message to be used by the JavaFX GUI.
     */
    public String getInitMessage() {
        return initMessage;
    }

    /**
     * Gives a user input to the Duke chat bot, and returns the appropriate response.
     *
     * @param input The user input from the GUI.
     * @return A string message to be used by the JavaFX GUI.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommandFromInput(input.trim());
            taskList = command.execute(taskList, ui, storage);
            isTerminated = command.isTerminated();
            return taskList.getRecentMessage();
        } catch (IOException e) {
            return ui.showError("The data failed to save to the save file with error:"
                    + e.getMessage());
        } catch (IllegalArgumentException e) {
            // When invalid command is given, it is unable to be parsed into the enum
            return ui.showError("You have entered an invalid command.");
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Returns a boolean representing whether or not the program should continue running.
     *
     * @return True if a bye/terminating command has been inputted, false otherwise.
     */
    public boolean getIsTerminated() {
        return isTerminated;
    }
}
