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
 * A class encapsulating a DialogBox, which contains text and an Image representing the user.
 */
public class DialogBox extends HBox {

    /**
     * A Label containing the message by/for the user.
     */
    @FXML
    private Label dialog;

    /**
     * An ImageView containing an Image of either the user or Duke.
     */
    @FXML
    private ImageView displayPicture;

    /**
     * A constructor for a DialogBox.
     *
     * @param text The message to be contained in the DialogBox.
     * @param img The image to be used in the DialogBox.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
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
     * Gets a DialogBox containing the user's interaction with Duke.
     *
     * @param text The text entered by the user.
     * @param img An image representing the user.
     * @return A DialogBox representing a message from the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Gets a DialogBox containing text by Duke in response to a user's command.
     *
     * @param text The text to be sent to the user.
     * @param img  An image representing Duke.
     * @return A DialogBox representing a message from Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
