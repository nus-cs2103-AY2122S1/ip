package agent.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Represents a GUI chat dialog box for each message.
 *
 * @author kevin9foong
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialogLabel;
    @FXML
    private Circle avatarImageFrame;

    /**
     * Constructs a <code>DialogBox</code> with the provided
     * message text and user avatar picture to be displayed.
     */
    private DialogBox(String dialogText, Image avatarImage) {
        try {
            String dialogBoxFxmlFilePath = "/view/DialogBox.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(dialogBoxFxmlFilePath));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        dialogLabel.setText(dialogText);
        avatarImageFrame.setFill(new ImagePattern(avatarImage));
    }

    /**
     * Returns an instance of <code>DialogBox</code> which represents user's side of conversation.
     *
     * @param dialogText text which represents user's input.
     * @param avatar     user's avatar image.
     * @return <code>DialogBox</code> which represents user's side of conversation.
     */
    public static DialogBox generateUserDialogBox(String dialogText, Image avatar) {
        DialogBox userDialogBox = new DialogBox(dialogText, avatar);
        userDialogBox.dialogLabel.setBackground(new Background(new BackgroundFill(Color.rgb(178, 255, 89),
                new CornerRadii(5.0), new Insets(-5.0))));
        userDialogBox.flip();
        return userDialogBox;
    }

    /**
     * Returns an instance of <code>DialogBox</code> which represents agent's side of conversation.
     *
     * @param dialogText text which represents agent's response.
     * @param avatar     agent's avatar image.
     * @return <code>DialogBox</code> which represents agent's side of conversation.
     */
    public static DialogBox generateAgentDialogBox(String dialogText, Image avatar) {
        DialogBox agentDialogBox = new DialogBox(dialogText, avatar);
        agentDialogBox.dialogLabel.setBackground(new Background(new BackgroundFill(Color.rgb(144, 202, 249),
                new CornerRadii(5.0), new Insets(-5.0))));
        return agentDialogBox;
    }

    private void flip() {
        this.setAlignment(Pos.CENTER_RIGHT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        assert this.getChildren().size() == tmp.size() : "Number of nodes should be the same after flipping";
        this.getChildren().setAll(tmp);
    }
}
