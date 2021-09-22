package ailurus;

import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
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

    private Ailurus ailurus;
    private Ui ui;

    private Image userImage;
    private Image ailurusImage;

    /**
     * Initialise FXML window
     */
    @FXML
    public void initialize() {
        InputStream pixelImg = this.getClass().getResourceAsStream("/images/Pixel.png");
        InputStream ailurusImg = this.getClass().getResourceAsStream("/images/Ailurus.png");
        assert pixelImg != null : "User image cannot be null";
        assert ailurusImg != null : "Ailurus image cannot be null";
        userImage = new Image(pixelImg);
        ailurusImage = new Image(ailurusImg);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Set and initalise Ailurus bot
     *
     * @param d Ailurus object to be initialised with
     */
    public void setAilurus(Ailurus d) {
        ailurus = d;
        ui = new Ui();
        dialogContainer.setStyle("-fx-background-image: url(\"/images/Ailurus_bg.png\"); ");
        dialogContainer.getChildren().add(DialogBox.getAilurusDialog(ui.showWelcome(), ailurusImage));
        sendButton.disableProperty().bind(
                Bindings.isEmpty(userInput.textProperty())
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Ailurus' reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (userInput.getText().trim().length() != 0) {
            String input = userInput.getText();
            String response = ailurus.getResponse(input);
            assert response.length() != 0 : "response should not be empty";
            assert userImage != null : "userImage should not be null";
            assert ailurusImage != null : "ailurusImage should not be null";
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getAilurusDialog(response, ailurusImage)
            );
            userInput.clear();
            if (Ailurus.isExit()) {
                userInput.setDisable(true);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.exit();
                        System.exit(0);
                    }
                }, 1000);
            }
        } else {
            userInput.clear();
        }
    }
}
