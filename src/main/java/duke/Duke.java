package duke;

import duke.command.Command;
import duke.graphics.DialogBox;
import duke.graphics.ResponseMessage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;


public class Duke {//} extends Application {
    // GUI componnents
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    // project components
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final String FILE_URL = "data/storedList.txt";

    /**
     * Empty constructor for Launcher to call
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_URL);
        tasks = new TaskList(storage.loadStorage());
    }


    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the program with the correct components and storage file set-ups.
     */
    public void run() {
        ui.displayGreetings();
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
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public ResponseMessage getResponse(String input) {
        List<String> linesOfOutput = new ArrayList<>();
        boolean isExit = false;

        try {
            linesOfOutput.add(ui.showLine());
            Command c = Parser.parse(input);
            linesOfOutput.addAll(c.execute(tasks, ui, storage));
            isExit = c.isExit();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } finally {
            linesOfOutput.add(ui.showLine());
        }


        //if (isExit)

        return new ResponseMessage(String.join("\n", linesOfOutput), isExit);
        //return "Duke heard: " + input;
    }

    public String greetToGui() {
        return ui.displayGreetings();
    }
}