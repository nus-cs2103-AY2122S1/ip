package meow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private Meow meow;
    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/doge.png"));
    private Image meowImage = new Image(this.getClass().getResourceAsStream("/images/cat.jpeg"));

    /**
     * Start of the application.
     *
     * @param stage The given stage.
     */
    @FXML
    public void initialize(Stage stage) {
        stage.setTitle("Meow - An Automated Reminder Chat Bot");
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setStyle("-fx-background-image: url('images/background.png');");
        dialogContainer.getChildren().add(
                DialogBox.getMeowDialog(ui.greet(), meowImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     *
     * @param m The Meow object input.
     */
    public void setMeow(Meow m) {
        meow = m;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
   private void handleUserInput() {
        String input = userInput.getText();
        String response = meow.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMeowDialog(response, meowImage)
        );
        userInput.clear();
    }
}
