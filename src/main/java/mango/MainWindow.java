package mango;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    private Mango mango;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image mangoImage = new Image(this.getClass().getResourceAsStream("/images/Mango.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the instance of Mango that will interact in the MainWindow.
     * @param d The instance of Mango.
     */
    public void setMango(Mango d) {
        mango = d;
        MangoDialogBox showLogo = MangoDialogBox.getMangoDialog(mango.showLogo(), mangoImage);
        dialogContainer.getChildren().addAll(showLogo);
        MangoDialogBox greet = MangoDialogBox.getMangoDialog(mango.greet(), mangoImage);
        dialogContainer.getChildren().addAll(greet);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Mango's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = mango.getResponse(input);

        dialogContainer.getChildren().addAll(
                UserDialogBox.getUserDialog(input, userImage),
                MangoDialogBox.getMangoDialog(response, mangoImage)
        );
        userInput.clear();
    }
}
