package botto.ui;

import botto.Botto;
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

    private Botto botto;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/human.png"));
    private Image bottoImage = new Image(this.getClass().getResourceAsStream("/images/botto.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBotto(Botto botto) {
        this.botto = botto;
        botto.setUpDialogContainer(dialogContainer);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
        userInput.clear();
        botto.handleUserInput(input);
    }
}
