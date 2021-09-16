package duke.io;

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
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class UserDialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private UserDialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/UserDialogBox.fxml"));
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
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates the dialog box containing the user's response and image.
     *
     * @param text The user's input.
     * @param img The user's image ("profile picture")
     * @return The UserDialogBox with the relevant components set.
     */
    public static UserDialogBox getUserDialog(String text, Image img) {
        return new UserDialogBox(text, img);
    }

    /**
     * Creates the dialog box containing The Duke's response and image.
     *
     * @param text The Duke's response
     * @param img The Duke's image ("profile picture")
     * @return The DukeDialogBox with the relevant components set.
     */
    public static UserDialogBox getDukeDialog(String text, Image img) {
        var db = new UserDialogBox(text, img);
        db.flip();
        return db;
    }
}
