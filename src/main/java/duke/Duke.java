package duke;

import duke.errors.DukeException;
import duke.errors.FileException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Duke is a personal Assistant Chatbot that helps the user to keep track of various things.
 */
public class Duke {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/thinkingCat1.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/CuteDuke.png"));

    private Archive archive;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor.
     *
     * @param filePath path of the file that stores previously entered tasks (Hard Disk).
     * @throws duke.errors.FileException
     */
    public Duke(String filePath) {
        ui = new Ui();
        this.archive = new Archive();
        try {
            storage = new Storage(filePath, this.ui);
            tasks = new TaskList(storage.load());
        } catch (FileException fileException) {
            tasks = new TaskList();
            ui.showError("Unable to load from hard disk, new task list created.");
        }
        this.parser = new Parser(this.tasks);
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText.toString(), user),
                DialogBox.getDukeDialog(dukeText.toString(), duke)
        );
        userInput.clear();
    }

    /**
     * Generates Duke's response to the user input.
     *
     * @param input user input to GUI.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        try {
            duke.command.Command result = parser.parse(input);
            return result.execute(this.tasks, this.ui, this.storage, this.archive);
        } catch (DukeException dukeException) {
            return dukeException.toString();
        }
    }

    /**
     * Displays Duke's response to the user input, entered from the Command Line.
     */
    private void chat() throws DukeException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                duke.command.Command c = parser.parse(fullCommand);
                String response = c.execute(tasks, ui, storage, this.archive);
                ui.printToConsole(response);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public String greetUser() {
        return ui.showWelcome();
    }

    /**
     * Checks whether the user input is 'bye' to exit application. (for GUI only)
     *
     * @param input user input.
     * @return boolean of whether the input is 'bye'.
     */
    public boolean isExit(String input) {
        try {
            return this.parser.parse(input).isExit();
        } catch (DukeException dukeException) {
            return false;
        }
    }

}
