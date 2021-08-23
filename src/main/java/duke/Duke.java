package main.java.duke;

import main.java.duke.command.Command;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Duke is a Personal Assistant Chatbot that helps a person
 * keep track of various things.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S2
 */
public class Duke {
    private final Storage STORAGE;
    private final Ui UI;
    private TaskList tasks;

    /**
     * Constructor for Duke.
     *
     * @param filePath the filePath for the saved data
     */
    public Duke(String filePath) {
        this.UI = new Ui();
        this.STORAGE = new Storage(filePath);
        try {
            tasks = new TaskList(this.STORAGE.load(), UI);
        } catch (IOException e) {
            UI.reply("☹ OOPS!!! Cannot create file!");
            tasks = new TaskList(new ArrayList<>(), UI);
        }
    }

    /**
     * Executes the Duke application.
     */
    public void run() {
        UI.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = UI.readCommand();
                UI.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, UI, STORAGE);
                isExit = c.isExit();
            } catch (DukeException e) {
                UI.showError(e.getMessage());
            } catch (IOException e) {
                UI.showError(new DukeException("Error reading commands.").getMessage());
            } finally {
                UI.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
