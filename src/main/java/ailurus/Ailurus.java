package ailurus;

import ailurus.task.TaskList;
import javafx.application.Application;

/**
 * The main task chatbot class for Ailurus Chatbot
 *
 * @author Leeroy Liu
 */
public class Ailurus {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;
    private static boolean isExit = false;

    /**
     * Constructor for Ailurus Chatbot
     *
     * @param directory directory of the file to store data
     * @param filename filename of the file to store data (with .txt)
     */
    public Ailurus(String directory, String filename) {
        ui = new Ui();
        storage = new Storage(directory, filename);
        try {
            tasks = new TaskList(storage.load());
        } catch (AilurusException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Exit JavaFX program
     *
     */
    public static void exit() {
        isExit = true;
    }

    /**
     * Unload storage to save file
     *
     */
    public static void unloadStorage() {
        storage.unload(tasks);
    }

    /**
     * Returns whether program is ready to exit
     * @return isExit
     */
    public static boolean isExit() {
        return isExit;
    }

    /**
     * Get message and handle response
     *
     * @param message message to be parsed
     * @return response to message
     */
    public String getResponse(String message) {
        try {
            String command = Parser.parse(message);
            return Parser.parseCommand(command, message, ui, storage, tasks);
        } catch (AilurusException e) {
            return ui.showError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

}
