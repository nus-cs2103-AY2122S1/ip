package ailurus;

import ailurus.task.Deadline;
import ailurus.task.Event;
import ailurus.task.TaskList;
import ailurus.task.Todo;

/**
 * The main task chatbot class for Ailurus Chatbot
 *
 * @author Leeroy Liu
 */
public class Ailurus {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public static boolean isExit = false;

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
     * Run the chatbot program with the correct environment setups.
     */
    public void run() {
        ui.showWelcome();
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                String command = Parser.parse(fullCommand);
                Parser.parseCommand(command, fullCommand, ui, storage, tasks);
            } catch (AilurusException e) {
                this.ui.showError(e.getMessage());
            }
        }
    }

    public static void exit() {
        isExit = true;
    }

    public String getResponse(String message) {
        try {
            String command = Parser.parse(message);
            return Parser.parseCommand(command, message, ui, storage, tasks);
        } catch (AilurusException e) {
            return this.ui.showError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Ailurus("./data/", "tasks.txt").run();
    }

}
