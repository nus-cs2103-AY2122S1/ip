package duke.controller;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * The DialogBox class is a controller class that represents a dialog box consisting of
 * an image of the speaker's avatar and a label containing text from the speaker,
 * similar to most chat-bot user interfaces.
 */
public class DialogBox extends HBox {
    /** Label containing text from the speaker. */
    @FXML
    private Text dialog;

    /** ImageView containing the speaker's avatar. */
    @FXML
    private ImageView displayPicture;

    private static final Image userImage = new Image(DialogBox.class.getResourceAsStream("/images/DaUser.png"));
    private static final Image dukeImage = new Image(DialogBox.class.getResourceAsStream("/images/DaDuke.png"));

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DialogBox.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        dialog.setTextAlignment(TextAlignment.LEFT);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a dialog box for text entered by the user.
     *
     * @param text The text entered by the user.
     * @return A dialog box of text entered by the user to be displayed on the GUI.
     */
    public static DialogBox getUserDialog(String text) {
        return new DialogBox(text, userImage);
    }

    /**
     * Returns a dialog box for text from Duke.
     *
     * @param text The text from Duke to be displayed to the user.
     * @return A dialog box of text from Duke to be displayed on the GUI.
     */
    public static DialogBox getDukeDialog(String text) {
        var db = new DialogBox(text, dukeImage);
        db.flip();
        return db;
    }
}
