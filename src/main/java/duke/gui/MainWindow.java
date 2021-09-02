package duke.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import duke.main.Duke;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    /** Image representing the user */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    /** Image representing Gary */
    private Image garyImage = new Image(this.getClass().getResourceAsStream("/images/DaGary.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void showLogo() {
        String logo = "  _______              ___            _____      __             __    _\n"
                + "|      ____|          /  ^  \\         |      _    \\   \\  \\        /  /  |  |\n"
                + "|    |  ___          /  /_\\  \\      |    |_|    |   \\  \\  /  /     |  |\n"
                + "|    ||_    |      /      __     \\    |    __    <        \\      /        |_|\n"
                + "|    |__| |    /      /    \\     \\  |    |  \\    \\        |   |            _  \n"
                + "|______|  /__/        \\__\\|_ |       \\_\\     |_|         |_|\n";
        dialogContainer.getChildren().add(DialogBox.getGaryDialog(logo, garyImage));
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
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getGaryDialog(response, garyImage)
        );
        userInput.clear();
    }
}