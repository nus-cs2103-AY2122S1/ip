package bob;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a message sent in the chat, with the message sender's image and their message.
 */
public class DialogBox extends HBox {

    /** Message sent in the chat */
    private Label text;

    /** Image of message sender */
    private ImageView displayPicture;

    /**
     * Constructor for a DialogBox instance.
     *
     * @param l Label object containing the dialog message to be displayed.
     * @param iv Image of entity that is "speaking" the dialog.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.CENTER_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.CENTER_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Factory method for producing a DialogBox for the user.
     *
     * @param l Label containing the dialog message to be displayed.
     * @param iv Image of entity that is "speaking" the dialog.
     * @return DialogBox with image on the right and text on the left.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Factory method for producing a DialogBox for Bob.
     * DialogBox for Bob is flipped horizontally, meaning the image is on the left instead of the right.
     *
     * @param l Label containing the dialog message to be displayed.
     * @param iv Image of entity that is "speaking" the dialog.
     * @return DialogBox with text on the right and image on the left.
     */
    public static DialogBox getBobDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}