package duke;

import javafx.application.Application;

/**
 * Represents Duke, a text-based Java chatbot that helps to
 * keep track of various tasks.
 *
 * @author Joshua Yong
 */
public class Duke {

    private static final String STORAGE_FILE_PATH = "./data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class constructor.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(STORAGE_FILE_PATH);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage() + "\nStarting Duke without saved tasks...");
            tasks = new TaskList();
        }
    }

    /**
     * Gets the appropriate response to be shown to the user given a user input.
     *
     * @param input The user input.
     * @return The response to be shown to the user.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(tasks);
        if (input.equals("bye")) {
            System.exit(0);
        }
        try {
            String response = parser.parse(input);
            storage.save(tasks);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * The entry point of the application.
     *
     * @param args Command line arguments (currently not used).
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

}
