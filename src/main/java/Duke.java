import duke.command.Command;
import duke.error.DukeException;
import duke.general.Parser;
import duke.general.Storage;
import duke.general.Tasklist;
import duke.general.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Chatbot that helps you form a task list
 */

public class Duke {
    private Storage storage;
    private Tasklist tasks;
    private Ui ui;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/data/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/data/DaDuke.png"));

    /**
     * Initializes the Duke chatbot program
     * @param filePath File path to save the data files of the Tasklist to
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new Tasklist(storage.loadSave());
        parser = new Parser();
    }

    /**
     * Initializes the Duke chatbot program for the Launcher
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        tasks = new Tasklist(storage.loadSave());
        parser = new Parser();
    }

    /**
     * Run the main program
     */
    public void run() {
        ui.showWelcome();
        while (ui.getLoop()) {
            try {
                String fullCommand = ui.readInput();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, storage, ui);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public String getResponse(String input) {
        String output = "";
        try {
            Command c = parser.parse(input);
            output = c.execute(tasks, storage, ui);
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
