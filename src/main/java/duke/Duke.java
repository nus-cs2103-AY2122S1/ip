package duke;

import duke.gui.Main;
import javafx.application.Application;

/**
 * Represents Duke, an interactive personal assistant bot that can keep track of tasks via text commands.
 *
 * @author Hanif Kamal
 */
public class Duke {
    private final Storage storage;
    private final TaskList list;
    private final Ui ui;
    private static final String FILEPATH = "./data/duke.txt";

    /**
     * Class constructor to initialize a Duke instance.
     */
    public Duke() {
        this.storage = new Storage(FILEPATH);
        this.list = new TaskList();
        this.ui = new Ui();
        try {
            storage.readTasks(list);
        } catch (Exception e) {
            System.out.println("Could not read the data file: " + e.getMessage());
        }
    }

    /**
     * Starts up Duke to get ready for chatting and task-tracking.
     *
     * @param input The String input by the user.
     * @return Duke's response to the user as a String.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            System.exit(0);
        }
        Parser parser = new Parser(list);
        try {
            String response = parser.parse(input);
            storage.writeTasks(list);
            return response;
        } catch (DukeException e) {
            return (e.getMessage());
        }
    }

    /**
     * Main method to begin Duke.
     * @param args Not used.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
