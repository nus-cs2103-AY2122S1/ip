package duke;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Label;



import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents a Duke bot that can interact with users
 * and keep track of different tasks.
 */

public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor for Duke
     *
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.loadingError();
            tasks = new TaskList();
        }
        parser = new Parser(ui, tasks);

    }

    /**
     * A public constructor for Duke.
     */
    public Duke() {
        this("data/tasks.txt");
    }

    /**
     * Runs the program.
     */

    public void run() {
        //ui.greetUser();
        boolean isBye = false;
        while (!isBye) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isBye = c.isBye();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
    * @return a label with the specified text that has word wrap enabled.
    */
    public Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }


    public String getResponse(String input) {
        try {
            String fullCommand = input;
            Command c = parser.parse(fullCommand);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public Ui getUi() {
        return ui;
    }
    @Override
    public void start(Stage stage) {

    }

    /**
     * Creates an instance of Duke and runs the program.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
