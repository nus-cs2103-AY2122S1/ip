package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private StackPane chatBubble;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setAlignment(Pos.CENTER_RIGHT);
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    private void flip() {
        //Reverse order of components
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);

        //Set alignment to top left / left
        setAlignment(Pos.TOP_LEFT);
        dialog.setTextAlignment(TextAlignment.LEFT);
    }

    /**
     * Returns DialogBox belonging to the user.
     *
     * @param text user's message
     * @param img user's profile picture
     * @return user's dialog box
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.chatBubble.getStyleClass().add("userBubble");
        db.dialog.setTextFill(Color.WHITE);
        db.dialog.setTextAlignment(TextAlignment.RIGHT);
        db.dialog.setAlignment(Pos.CENTER_RIGHT);

        return db;
    }

    /**
     * Returns DialogBox belonging to the Duke.
     *
     * @param text Duke's message
     * @param img Duke's profile picture
     * @return Duke's dialog box
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();

        db.chatBubble.getStyleClass().add("dukeBubble");
        db.dialog.setAlignment(Pos.CENTER_LEFT);
        db.dialog.setTextAlignment(TextAlignment.LEFT);

        return db;
    }
}
