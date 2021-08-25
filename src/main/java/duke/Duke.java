package duke;

import duke.exception.DukeException;
import duke.ui.TaskList;
import duke.command.Command;
import duke.ui.Parser;
import duke.ui.Storage;
import duke.ui.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) { //Creating the Duke Class
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            Ui.showLoadingError(); //Inform user that the existing file is of the wrong format
            this.taskList = new TaskList(); //Creates a new empty list
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isRunning = true; //Bot is running on start up
        do {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line after each line
                Command c = Parser.parse(fullCommand); //Converts the input to the proper commands
                c.execute(taskList, ui, storage); //Run the given command
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

    public static void main(String[] args) {
        new Duke("data").run(); //To run the bot
    }
}