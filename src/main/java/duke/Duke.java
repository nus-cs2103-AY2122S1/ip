package duke;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Represents a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {

    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Class constructor.
     */
    public Duke() {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser();
    }

    /**
     * Runs the Duke Personal Assistant Chatbot.
     */
    public void run() {
        boolean terminate = false;
        this.ui.welcome();

        while (!terminate) {
            try {
                String input = ui.readInput();
                parser.executeCommand(ui, tasks, input);
                terminate = parser.getToTerminate();
            } catch (Exception e) {
                ui.handleException(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
