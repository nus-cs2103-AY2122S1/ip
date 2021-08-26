package duke;

import duke.exception.DukeException;
import duke.ui.TaskList;
import duke.command.Command;
import duke.ui.Parser;
import duke.ui.Storage;
import duke.ui.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Main Class to run the Duke ChatBot
 */
public class Duke {
    private final Storage storage;
    private TaskList taskLists;
    private final Ui ui;

    /**
     * Constructor for the duke class
     * @param filePath The path where the txt file is located/to be created
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            this.taskLists = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            Ui.showLoadingError(); //Inform user that the existing file is of the wrong format
            this.taskLists = new TaskList(); //Creates a new empty list
        }
    }

    /**
     * Starts the bot
     */
    public void run() {
        ui.showWelcome();
        boolean isRunning = true; //Bot is running on start up
        do {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line after each line
                Command c = Parser.parse(fullCommand); //Converts the input to the proper commands
                c.execute(taskLists, ui, storage); //Run the given command
                isRunning = c.isRunning(); //Updates the status of the bot
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e2) {
                System.out.println("☹ OOPS!!! It seems like your file is corrupted!");
            } catch (DateTimeParseException e3) {
                System.out.println("☹ OOPS!!! It seems like your date/time input is wrong!");
            } finally {
                ui.showLine(); //To show the divider line after the bot's output
            }
        } while (isRunning);
    }

    /**
     * main function
     */
    public static void main(String[] args) {
        new Duke("data").run(); //To run the bot
    }
}