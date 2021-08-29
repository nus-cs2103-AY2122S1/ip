package duke;

import java.io.IOException;

import duke.command.Command;
import duke.task.TaskList;

/**
 * The type Duke that is the main of the program.
 */
public class Duke {

    /** storage to handle save file (loading and saving) */
    private final Storage storage;
    /** storage to store tasks */
    private final TaskList tasks;
    /** handles basic interaction with the user and string formatting */
    private final Ui ui;

    /**
     * Instantiates a new Duke.
     *
     * @param filePath the file path
     */
    public Duke(String filePath) {
        this.tasks = new TaskList();
        this.ui = new Ui();
        // Initialise Storage with the tasks storage and the filepath to the save file
        this.storage = new Storage(this.tasks, filePath);
        // Loading save file from the filepath
        try {
            storage.load();
        } catch (IOException e) {
            System.out.println(
                    Ui.tabAndFormat(
                            "☹ OOPS!!! Please enter a proper file path! e.g.:\n'./duke.txt'"
                    )
            );
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Duke("./duke.txt").run();
    }

    /**
     * Runs Duke.
     */
    public void run() {
        // Starting with a welcome message
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();

            try {
                Command userCommand = new Parser(input, tasks).checkOperation();
                userCommand.execute();
                isExit = userCommand.isExit();
                storage.save();
            } catch (DukeException | IllegalArgumentException e) {
                System.out.println(Ui.tabAndFormat(e.getMessage()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
