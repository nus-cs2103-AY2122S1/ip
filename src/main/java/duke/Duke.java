package duke;

import java.io.IOException;

import duke.exception.FileParseException;
import duke.storage.TaskStorage;
import duke.ui.Cli;
import duke.ui.Gui;
import duke.ui.Ui;

/**
 * Represents the entry point of the Duke application.
 *
 * @author botr99
 */
public class Duke {
    private static final String STORAGE_LOCATION = "data/duke.txt";
    private Ui ui;

    /**
     * Constructs a Duke that reads in tasks from the user's hard disk.
     *
     * @param fileLocation The string of the file path to access the tasks.
     * @param isGui False if Duke is run with the command line; true otherwise.
     * @throws IOException When an error occurs when reading or writing to the file.
     * @throws FileParseException When the file is not of the right format.
     */
    public Duke(String fileLocation, boolean isGui) throws IOException, FileParseException {
        TaskStorage taskStorage = new TaskStorage(fileLocation);
        TaskList taskList = new TaskList(taskStorage.loadTasks());
        ui = isGui ? new Gui(taskStorage, taskList) : new Cli(taskStorage, taskList);
    }

    /**
     * Starts the Duke application.
     */
    public void run() {
        ui.start();
    }

    public String getResponse(String input) {
        ui.handleUserInput(input);
        return ui.getCurrentMessage();
    }

    /**
     * Attempts to start the Duke application by loading in the file
     * located in the user's hard disk.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        try {
            new Duke(STORAGE_LOCATION, false).run();
        } catch (IOException e) {
            System.out.println("Something went wrong with accessing " + STORAGE_LOCATION);
        } catch (FileParseException e) {
            System.out.println(STORAGE_LOCATION + " is not of the proper format. "
                    + "Either modify it to the right format or delete the file.");
        }
    }
}
