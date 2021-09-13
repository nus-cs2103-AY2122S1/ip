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

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, String backgroundColor) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setStyle("-fx-background-color: " + backgroundColor + ";");

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
     * Gets a user dialog box, image aligned to the right.
     *
     * @param text Message to be included.
     * @param img Image to be included.
     * @return A dialog box with the message and the image.
     */
    public static DialogBox getUserDialog(String text, Image img, String backgroundColor) {
        return new DialogBox(text, img, backgroundColor);
    }

    /**
     * Gets a duke dialog box, image aligned to the left.
     *
     * @param text Message to be included.
     * @param img Image to be included.
     * @return A dialog box with the message and the image.
     */
    public static DialogBox getDukeDialog(String text, Image img, String backgroundColor) {
        var db = new DialogBox(text, img, backgroundColor);
        db.flip();
        return db;
    }
}
