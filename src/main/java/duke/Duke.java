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
    private final Storage storage;
    private final Ui ui = new Ui();
    private TaskList tasks;

    /**
     * Constructor for Duke.
     *
     * @param filePath the filePath for the saved data
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.reply("☹ OOPS!!! Cannot create file!");
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Executes the Duke application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError(new DukeException("Error reading commands.").getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
