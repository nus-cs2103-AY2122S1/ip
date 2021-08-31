package ponyo.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ponyo.Ponyo;

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

    private Ponyo ponyo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/jia.jpeg"));
    private Image ponyoImage = new Image(this.getClass().getResourceAsStream("/images/sally.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setPonyo(Ponyo p) {
        ponyo = p;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Ponyo's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ponyo.handleInput(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPonyoDialog(response, ponyoImage)
        );
        userInput.clear();
    }

//    @FXML
//    private static void showMessageFromPonyo(String msg) {
//        dialogContainer.getChildren().add(DialogBox.getPonyoDialog(msg, ponyoImage));
//    }
}
