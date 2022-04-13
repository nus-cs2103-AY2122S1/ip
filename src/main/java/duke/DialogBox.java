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

    /**
     * A private constructor for DialogBox.
     *
     * @param text The text to display.
     * @param img The image in the dialog box.
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
     * Returns a dialogbox containing the user input and user image.
     *
     * @param text The user input.
     * @param img The user image.
     * @return Dialog box containing the input and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setStyle("-fx-background-color:lavender");
        db.setWidth(300);
        db.setHeight(110);
        return db;
    }

    /**
     * Returns a dialog box containing Duke's response and image.
     *
     * @param text Duke's response.
     * @param img Duke's image.
     * @return Dialog box containing the text and image.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setStyle("-fx-background-color:lightsteelblue");
        db.flip();
        return db;
    }

    /**
     * Returns the dialog box containing Duke's user greeting
     * and Duke's image.
     *
     * @param text The user greeting.
     * @param img Duke's image.
     * @return Dialog box containing the text and image.
     */
    public static DialogBox greetUser(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setStyle("-fx-background-color:lightsteelblue");
        db.flip();
        return db;
    }
}
