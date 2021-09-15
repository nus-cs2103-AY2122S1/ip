package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.command.Storage;
import duke.command.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidTaskException;
import duke.exception.MissingTaskException;
import duke.exception.MissingTimeException;
import duke.task.TaskList;

/**
 * This class runs a personal assistant chat bot that helps a person keep track of various tasks.
 * Commands for the bot are: list, mark, find, delete, bye, event, deadline, help, and todo.
 * Unrecognised commands will be met with a prompt to enter a recognised one instead.
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private boolean isExit;

    /**
     * Initiates the chat bot by loading the given file.
     */
    public Duke() {
        ui = new Ui("Pingu");
        storage = new Storage("data/duke_list_data.txt");
        isExit = false;

        try {
            tasks = new TaskList(storage.loadListData());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Accepts the user's inputs and acts accordingly.
     *
     * @param input The input entered by the user.
     * @return
     */
    private String handleUserInput(String input) {
        try {
            String command = ui.receiveUserCommand(input);
            return executeAction(input, command);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return ui.printException("IOException");
        } catch (DateTimeParseException e) {
            return ui.printException("DateTimeParse");
        }
    }

    /**
     * Executes an action depending on the command.
     *
     * @param input The input entered by the user.
     * @param command The command interpreted by the parser.
     * @return The String representing the
     * @throws IOException
     * @throws InvalidCommandException
     * @throws InvalidTaskException
     * @throws MissingTaskException
     * @throws MissingTimeException
     */
    private String executeAction(String input, String command)
            throws IOException, InvalidCommandException, InvalidTaskException,
            MissingTaskException, MissingTimeException {
        if (command.equals("bye")) {
            isExit = true;
            return ui.printBye();
        } else if (command.equals("help")) {
            return ui.printHelp();
        } else {
            storage.saveTasksToFile(tasks.getTasks());
            return tasks.performCommand(command, input);
        }
    }

    /**
     * Returns a generated response based on the user's input.
     *
     * @param input The input entered by the user.
     * @return The String representing the response.
     */
    public String getResponse(String input) {
        return handleUserInput(input.trim());
    }

    /**
     * Returns the String representing Duke's welcome message.
     *
     * @return Duke's welcome message.
     */
    public String getWelcomeMessage() {
        return ui.getWelcome();
    }

    /**
     * Returns initial list of tasks.
     *
     * @return The String representing the tasks.
     */
    public String initialGetTasks() {
        return tasks.printList();
    }

    /**
     * Checks if the user has requested to exit Duke.
     *
     * @return true if user has requested to exit; false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
