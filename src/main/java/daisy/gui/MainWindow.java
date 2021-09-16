package daisy.gui;

import daisy.Daisy;
import daisy.DaisyException;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

//@@author tsy24-reused
//Reused from https://se-education.org/guides/tutorials/javaFx.html
// with modifications
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

    private Daisy daisy;

    private Image daisyImage = new Image(this.getClass().getResourceAsStream("/images/Daisy.jpg"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDaisy(Daisy d) {
        daisy = d;
        dialogContainer.setBackground(new Background(new BackgroundFill(Color.rgb(255, 240, 255),
                CornerRadii.EMPTY, Insets.EMPTY)));
        dialogContainer.getChildren().add(DialogBox.getDaisyDialog(daisy.start(), daisyImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Daisy's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            String response = daisy.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDaisyDialog(response, daisyImage));
        } catch (DaisyException e) {
            String response = e.getMessage();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getExceptionDialog(response, daisyImage));
        }
        userInput.clear();
    }
}
