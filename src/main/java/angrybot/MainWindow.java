package angrybot;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import angrybot.ui.Ui;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private AngryBot angryBot;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/shrek.png"));
    private final Image angryBotImage = new Image(this.getClass().getResourceAsStream("/images/pepe.png"));

    /**
     * The function to invoke once the Main has loaded. This shows
     * the greeting message and sets some property.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getAngryBotDialog(Ui.showGreetingMessage(), angryBotImage)
        );
    }

    public void setAngryBot(AngryBot angryBot) {
        this.angryBot = angryBot;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Angry Bot's reply and
     * then appends them to the dialog container. Clears the user input after processing. If the
     * response is empty string, it means that user has entered bye command. The platform will
     * close after 5 seconds.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = angryBot.getResponse(input);
        if (!response.equals("")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getAngryBotDialog(response, angryBotImage)
            );
            if (response.equals(Ui.showFarewellMessage())) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.exit();
                        System.exit(0);
                    }
                }, 3000);
            }
        } else {
            dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        }

        userInput.clear();
    }
}
