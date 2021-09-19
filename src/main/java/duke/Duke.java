package duke;

import javafx.application.Platform;
import javafx.scene.control.Label;

import duke.command.Command;
import duke.error.DukeException;
import duke.task.TaskList;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A chatbot that helps keep track of various tasks.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * Constructs a Duke object.
     */
    public Duke() {
        ui = new UI();
        storage = new Storage("./data/", "duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Generates a response to user input.
     *
     * @param input The input from the user.
     * @return The response.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Command c = Parser.parse(input);
            response += c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            response = e.getMessage();
        }

        return response;
    }
}
