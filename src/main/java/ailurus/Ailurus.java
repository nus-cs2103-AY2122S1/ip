package ailurus;

import ailurus.task.TaskList;
import javafx.application.Application;

/**
 * The main task chatbot class for Ailurus Chatbot
 *
 * @author Leeroy Liu
 */
public class Ailurus {
    private static boolean isExit = false;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Ailurus Chatbot
     *
     * @param directory directory of the file to store data
     * @param filename filename of the file to store data (with .txt)
     */
    public Ailurus(String directory, String filename) {
        this.ui = new Ui();
        this.storage = new Storage(directory, filename);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (AilurusException e) {
            this.ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Exit JavaFX program
     */
    public static void exit() {
        isExit = true;
    }

    /**
     * Checks if program can be exited
     *
     * @return isExit boolean
     */
    public static boolean isExit() {
        return isExit;
    }

    /**
     * Get message and handle response
     * @param message message to be parsed
     * @return response to message
     */
    public String getResponse(String message) {
        try {
            String command = Parser.parse(message);
            return Parser.parseCommand(command, message, ui, storage, tasks);
        } catch (AilurusException e) {
            return this.ui.showError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

}
