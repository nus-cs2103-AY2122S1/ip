package misaki.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import misaki.DialogBox;
import misaki.Misaki;

/**
 * Controller for ui.MainWindow. Provides the layout for the other controls.
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

    private Misaki misaki;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image misakiImage = new Image(this.getClass().getResourceAsStream("/images/Misaki.png"));

    /**
     * Initializes the misaki program.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getMisakiDialog(Ui.WELCOME, misakiImage)
        );
    }

    public void setMisaki(Misaki d) {
        misaki = d;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = misaki.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMisakiDialog(response, misakiImage)
        );

        if (input.equals("bye")) {

            Platform.exit();
        }
        userInput.clear();
    }
}
