package taubot;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class SceneController extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox conversationBox;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private ImageView taubotAvatar = new ImageView();
    private Taubot tauBot;

    /**
     * Initialise chat when user launches GUI.
     *
     * @param tauBot The Taubot object interacting with the user.
     */
    public void intialiseChat(Taubot tauBot) {
        this.tauBot = tauBot;
        taubotAvatar.setImage(new Image(this.getClass().getResourceAsStream("/images/taubot.png")));
        MessageContainer helloMessage = MessageContainer.getTaubotDialog("hey, i'm taubot. how can i help you manage "
                + "your tasks?");
        conversationBox.getChildren().add(helloMessage);
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tauBot.runGui(input);
        if (response.equals("Goodbye!")) {
            Platform.exit();
            return;
        }
        conversationBox.getChildren().addAll(
                MessageContainer.getUserDialog(input),
                MessageContainer.getTaubotDialog(response)
        );
        conversationBox.heightProperty().addListener(observable -> scrollPane.setVvalue(1D));
        userInput.clear();
    }

}

