package javafx;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Controller for javafx.MainWindow. Provides the layout for the other controls.
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

    private final Image USER_IMAGE = new Image(this.getClass().getResourceAsStream("/images/neko_user.png"));
    private final Image DUKE_IMAGE = new Image(this.getClass().getResourceAsStream("/images/neko_duke.png"));
    private final Image BACKGROUND_IMAGE = new Image(this.getClass().getResourceAsStream("/images/neko_bg.jpg"));

    /**
     * Initialises the height and other stylistic features of the GUI, as well as shows the welcome message.
     * Other stylistic features include the background image, font type, font weight, and font size.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setBackground(new Background(new BackgroundImage(this.BACKGROUND_IMAGE, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("hewwo welcome to neko duke uwu type help to see commands!", DUKE_IMAGE)
        );
        sendButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 18));
        userInput.setFont(Font.font("Comic Sans MS", FontWeight.SEMI_BOLD, 16));
    }

    /**
     * Sets the instance of Duke to be used.
     *
     * @param d The instance of Duke to be used.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Handles the user input that is inputted into the text field.
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, USER_IMAGE),
                DialogBox.getDukeDialog(response, DUKE_IMAGE)
        );
        userInput.clear();
    }
}
