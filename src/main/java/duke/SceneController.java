package duke;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
    private ImageView dukeAvatar = new ImageView();
    private Duke duke;

    /**
     * Initialise chat when user launches GUI.
     * @param duke The Duke object interacting with the user.
     */
    public void intialiseChat(Duke duke) {
        this.duke = duke;
        dukeAvatar.setImage(new Image(this.getClass().getResourceAsStream("/images/duke.png")));
        MessageContainer helloMessage = MessageContainer.getDukeDialog("Hi there, I'm Duke! I can help you manage "
                + "your tasks! How can I help you today :-)?");
        conversationBox.getChildren().add(helloMessage);
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.runGui(input);
        scrollPane.setVvalue(1D);
        if (response.equals("Goodbye!")) {
            Platform.exit();
            return;
        }
        conversationBox.getChildren().addAll(
                MessageContainer.getUserDialog(input),
                MessageContainer.getDukeDialog(response)
        );
        ObservableList<Node> children = conversationBox.getChildren();
        int nodeListSize = children.size();
        userInput.clear();
    }

}

