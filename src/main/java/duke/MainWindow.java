package duke;

import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


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
    @FXML
    private MenuBar menuBar;

    private Duke duke;

    private Image userImage;
    private Image dukeImage;

    /**
     * Initialize the MainWindow.
     * */
    @FXML
    public void initialize() {
        assert this.getClass().getResourceAsStream("/images/Tom.png") != null;
        assert this.getClass().getResourceAsStream("/images/Jerry.png") != null;
        userImage = new Image(this.getClass().getResourceAsStream("/images/Tom.png"));
        dukeImage = new Image(this.getClass().getResourceAsStream("/images/Jerry.png"));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcome = "Hello! I'm Jerry\n" + "Can I help you?\n";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcome, dukeImage));
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
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    @FXML
    private void terminateProgram() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void showUserGuideLink() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setContentText("Type help to check the command list!\n"
                + "Alternatively, visit https://github.com/ZhaoPeiduo/ip\n"
                + "for more information!");
        dialog.setTitle("Help");
        ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.show();
    }
}

