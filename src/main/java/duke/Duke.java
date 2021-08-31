package duke;

import java.io.IOException;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Message;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

public class Duke {
    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    /**
     * Initialize each component of the program.
     * Loads any data available from filePath.
     *
     * @param filePath path to the storage file
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        ui.printMessage(Message.WELCOME);

        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.printMessage(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Overloading constructor for Duke used in JavaFX.
     * Initialize each component of the program.
     * Loads any data available from data.txt.
     */
    public Duke() {
        storage = new Storage("data.txt");
        ui = null;

        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Run the program.
     */
    public void run() {
        String input;

        // Program exits only if user inputs "bye"
        while (!(input = ui.readCommand()).equals("bye")) {
            try {
                // Interpret and execute the command input by user
                String message = Parser.interpretCommand(input).execute(taskList);
                ui.printMessage(message);

                // Update storage file
                storage.save(taskList.getTasks());
            } catch (DukeException | IOException e) {
                ui.printMessage(e.getMessage());
            }
        }

        // Exit Ui
        ui.exit();
    }

    /**
     * Handles command from user and returns corresponding message from executing the command.
     * Used by JavaFX.
     *
     * @param command command input by user
     * @return response message of the command or error message
     */
    public String getResponse(String command) {
        // Exit is handled separately
        if (command.equals("bye")) {
            return Message.EXIT.toString();
        }

        String message;
        try {
            // Interpret and execute the command by user
            message = Parser.interpretCommand(command).execute(taskList);

            // Update storage file
            storage.save(taskList.getTasks());
        } catch (DukeException | IOException e) {
            message = e.getMessage();
        }

        return message;
    }

    /**
     * Initialize and start the program in CLI interface.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        new Duke("data.txt").run();
    }
}
