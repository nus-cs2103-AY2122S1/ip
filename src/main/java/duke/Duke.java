package duke;

import java.util.ArrayList;

import duke.command.Command;
import javafx.scene.image.Image;

/**
 * Duke class that starts and runs the Duke bot.
 */
public class Duke {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.jpg"));

    /**
     * Constructs the Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        try {
            taskList = new TaskList(storage.getFile());
        } catch (DukeException e) {
            ui.printMessage(e.getMessage());
            taskList = new TaskList(new ArrayList<>());
        }
    }

    public String start() {
        return "Hello! I'm Duke\n" + "What can I do for you?";
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
