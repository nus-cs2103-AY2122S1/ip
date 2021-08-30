import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private Morgan morgan;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image morganImage = new Image(this.getClass().getResourceAsStream("/images/Morgan.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setMorgan(Morgan morgan) {
        this.morgan = morgan;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Label inputLabel = new Label(input);
        String response = morgan.getResponse(input);
        Label responseLabel = new Label(response);

        ImageView morganImageView = new ImageView(morganImage);
        ImageView userImageView = new ImageView(userImage);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(inputLabel, userImageView),
                DialogBox.getDukeDialog(responseLabel, morganImageView)
        );
        userInput.clear();
    }
}