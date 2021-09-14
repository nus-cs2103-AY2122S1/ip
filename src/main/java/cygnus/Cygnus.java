package cygnus;

import cygnus.gui.Main;
import javafx.application.Application;

/**
 * Represents Cygnus, a text-based Java chatbot that helps to
 * keep track of various tasks.
 *
 * @author Joshua Yong
 */
public class Cygnus {

    private static final String STORAGE_FILE_PATH = "./data/cygnus.txt";
    private final Storage storage;
    private TaskList tasks;

    /**
     * Class constructor.
     */
    public Cygnus() {
        this.storage = new Storage(STORAGE_FILE_PATH);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (CygnusException e) {
            System.out.println(e.getMessage() + "\nStarting Cygnus without saved tasks...");
            tasks = new TaskList();
        }
    }

    /**
     * Returns the appropriate String response to be shown to the user given
     * a user input.
     *
     * @param input The user input.
     * @return The String response to be shown to the user.
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
        } catch (CygnusException e) {
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
