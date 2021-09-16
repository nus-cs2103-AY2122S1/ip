package energy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import energy.command.Command;
import energy.result.AliasHandler;
import energy.result.Result;
import energy.result.TaskList;
import energy.util.Parser;
import energy.util.Storage;
import energy.util.Ui;

/**
 * The driver class for the Energy chat bot.
 */
public class Energy {
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
    private String initMessage = "";
    private boolean isTerminated = false;
    private boolean hasException = false;

    /**
     * Creates an Energy chat bot instance, using a file path for loading/saving.
     *
     * @param saveFilePath Relative path to the location of the save file.
     */
    public Energy(Path saveFilePath, Path configFilePath) {
        this.ui = new Ui();
        this.storage = new Storage(saveFilePath, configFilePath);
        // Load saved tasks from data file
        try {
            if (!Files.exists(saveFilePath)) {
                initMessage += ui.showSaveFileNotFoundError();
                taskList = new TaskList();
                storage.saveTasksToFile(taskList);
            } else {
                taskList = storage.loadTasksFromFile();
            }
        } catch (Exception e) {
            hasException = true;
            initMessage += ui.showSaveFileLoadingError(e.getMessage());
            taskList = new TaskList();
        }
        // Load saved aliases from config file
        try {
            // Initialize Parser to recognize aliases for commands
            AliasHandler aliasHandler = new AliasHandler();
            if (!Files.exists(configFilePath)) {
                initMessage += ui.showConfigFileNotFoundError();
                storage.saveAliasesToFile(aliasHandler);
            } else {
                aliasHandler = storage.loadAliasesFromFile();
            }
            Parser.setAliasHandler(aliasHandler);
        } catch (Exception e) {
            hasException = true;
            initMessage += ui.showConfigLoadingError(e.getMessage());
            taskList = new TaskList();
        }
        initMessage += ui.showIntroduction();
    }

    /**
     * Creates a Energy chat bot instance, using a default file path for loading/saving.
     */
    public Energy() {
        this(Paths.get(System.getProperty("user.dir"), "data", "tasks.txt"),
                Paths.get(System.getProperty("user.dir"), "data", "config.txt"));
    }

    /**
     * Returns the messages generated on the initialization of Energy.
     *
     * @return A string message to be used by the JavaFX GUI.
     */
    public String getInitMessage() {
        return initMessage;
    }

    /**
     * Gives a user input to the Energy chat bot, and returns the appropriate response.
     *
     * @param input The user input from the GUI.
     * @return A string message to be used by the JavaFX GUI.
     */
    public String getResponse(String input) {
        try {
            hasException = false;
            Command command = Parser.parseCommandFromInput(input.trim());
            Result result = command.execute(taskList, ui, storage);
            // Energy should always give a non-empty response to a command
            assert (!result.getMessage().equals(""));
            // Update user-related state (tasks and aliases)
            taskList = result.getTaskList();
            Parser.setAliasHandler(result.getAliasHandler());
            isTerminated = command.isTerminated();
            return result.getMessage();
        } catch (IOException e) {
            hasException = true;
            return ui.showError("The data failed to save to the save file with error:"
                    + e.getMessage());
        } catch (Exception e) {
            hasException = true;
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Returns a boolean representing whether or not the program should continue running.
     *
     * @return True if a bye/terminating command has been inputted, false otherwise.
     */
    public boolean isTerminated() {
        return isTerminated;
    }

    /**
     * Returns a boolean representing whether or not an exception was thrown
     * while executing a command.
     *
     * @return True if an exception was caught, false otherwise.
     */
    public boolean hasException() {
        return hasException;
    }
}
