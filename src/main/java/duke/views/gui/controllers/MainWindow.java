package duke.views.gui.controllers;

import duke.constants.Constants;
import duke.views.Response;
import duke.views.gui.Gui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Gui gui;


    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public void setGui(Gui gui) {
        this.gui = gui;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(gui.getGreeting(), dukeImage, Constants.Display.BootstrapColor.INFO)
        );

    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String query = userInput.getText();

        if (gui.getResponder().shouldEnd(query)) {
            Platform.exit();
        }

        Response response = gui.getResponseWithMetadata(query);
        Constants.Display.BootstrapColor color;
        switch (response.getResponseType()) {
        case INFO:
            color = Constants.Display.BootstrapColor.INFO;
            break;
        case ERROR:
            color = Constants.Display.BootstrapColor.DANGER;
            break;
        default:
            throw new IllegalStateException("Unexpected Response Type");
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(query, userImage),
                DialogBox.getDukeDialog(response.getMessage(), dukeImage, color)
        );
        userInput.clear();
    }

}
