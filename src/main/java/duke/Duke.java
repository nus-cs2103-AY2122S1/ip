package duke;

import duke.command.Command;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Duke class that starts and runs the Duke bot.
 */
public class Duke {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

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

//    /**
//     * Runs the Duke bot.
//     */
//    public void run() {
//        ui.showWelcome();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                Command c = Parser.parse(fullCommand);
//                c.execute(taskList, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.printMessage(e.getMessage());
//            }
//        }
//    }

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
//            boolean isExit = c.isExit();

        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
