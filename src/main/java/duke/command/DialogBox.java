package duke.command;

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

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent profile and a label to
 * display text.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

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
     * Flips the dialog box such that the ImageView is on the right and text on the left.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_RIGHT);
    }

    /**
     * Initializes DialogBox with provided text and image from right to left
     *
     * @param text User inputted text.
     * @param img User image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Initializes DialogBox with provided text and image from left to right
     *
     * @param text User inputted text.
     * @param img Duke image.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        return new DialogBox(text, img);
    }
}
