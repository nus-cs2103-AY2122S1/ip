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

    /**
     * Creates a Duke chat bot instance, using a file path for loading/saving.
     *
     * @param filePath Relative path to the location of the save file.
     */
    public Duke(Path filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasksFromFile());
        } catch (FileNotFoundException e) {
            ui.showFileNotFoundError();
            taskList = new TaskList();
        } catch (Exception e) {
            ui.showLoadingError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Creates a Duke chat bot instance, using a default file path for loading/saving.
     */
    public Duke() {
        this(Paths.get(System.getProperty("user.dir"), "data", "tasks.txt"));
    }

    /**
     * Runs the actual chat bot program.
     */
    public void run() {
        ui.showIntroduction();
        boolean isTerminated = false;
        while (!isTerminated) {
            try {
                String input = ui.readInput();
                ui.showLine();
                Command command = Parser.parseCommandFromInput(input);
                taskList = command.execute(taskList, ui, storage);
                isTerminated = command.isTerminated();
            } catch (IOException e) {
                ui.showError("The data failed to save to the save file with error:"
                        + e.getMessage());
            } catch (IllegalArgumentException e) {
                // When invalid command is given, it is unable to be parsed into the enum
                ui.showError("You have entered an invalid command.");
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    /* TODO: Delete this once JavaFX is fully implemented
    /**
     * Starts the whole program.
     * @param args Some input arguments. (Unused)

    public static void main(String[] args) {
        Path filePath = Paths.get(System.getProperty("user.dir"), "data", "tasks.txt");
        new Duke(filePath).run();
    }*/

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
