package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;



/**
 * A chat-bot called Naruto that acts as a task list.
 */
public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final String NARUTO_REPLY_PREFIX = "Sage Mode On! \n";

    /**
     * Constructor for the Duke chat-bot.
     *
     * @param persistedData the relative path to the persisted data starting from the project directory.
     */
    public Duke(String persistedData) {
        ui = new Ui();
        storage = new Storage(persistedData);
        tasks = new TaskList(storage.loadPersistedData());
    }

    public String getResponse(String fullCommand) {
        // Create a stream to hold the output
        ByteArrayOutputStream narutoStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(narutoStream);
        // IMPORTANT: Save the old System.out!
        PrintStream oldPrintStream = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);

        // Now execute the entire command from user. It goes to my special stream
        try {
            Command command = Parser.parse(fullCommand);
            command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showError(e);
        } finally {
            ui.showLines();
        }

        // Put things back. Important!
        System.out.flush();
        System.setOut(oldPrintStream);
        // Show what happened in the terminal on IntelliJ
        // System.out.println("Here: " + narutoStream.toString());

        return NARUTO_REPLY_PREFIX + narutoStream.toString();
    }

    /**
     * Returns the initial welcome message from Naruto when the task manager program is first
     * initialized.
     *
     * @return the initial welcome message from Naruto as a string.
     */
    public String initialMessageFromNaruto() {
        // Create a stream to hold the output
        ByteArrayOutputStream narutoStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(narutoStream);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use my special stream
        System.setOut(ps);

        ui.showWelcome();

        // Put things back. Important!
        System.out.flush();
        System.setOut(old);
        // Show what happened in the terminal on IntelliJ if needed
        // System.out.println("Here: " + narutoStream.toString());

        return NARUTO_REPLY_PREFIX + narutoStream.toString();
    }

}
