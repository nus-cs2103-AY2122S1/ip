package ui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for ui.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String LOGO = " ____            _        \n"
            + "|     _   \\ _   _|  | _____ \n"
            + "|    |   |  |   |    | | |/ / _ \\\n"
            + "|    |_|   |   |_|  |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String FAREWELL = "Bye! Hope to see you soon!";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialize MainWindow for Duke and prints greeting.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        greet();
    }

    /**
     * Prints greeting
     */
    @FXML
    public void greet() {
        String greeting = "Hello! Welcome to Duke";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greeting, dukeImage));
    }

    @FXML
    public void farewell() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(FAREWELL, dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        try {
            String input = userInput.getText();
            String response = duke.getResponse(input);
            if (input.trim().equals("bye")) {
                farewell();
                Thread.sleep(5);
                System.exit(0);
            }
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
        } catch (InterruptedException e) {
            System.out.println("Error during delayed exit");
            System.exit(0);
        }

    }
}
