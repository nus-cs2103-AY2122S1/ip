package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Collections;

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
     * Sets certain properties on flipped box to distinguish it from the normally-oriented box.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.setTranslateX(5);
        dialog.setPadding(new Insets(5, 10, 5, 10));
        dialog.setStyle("-fx-background-color: #444444; -fx-background-radius: 20");
    }

    /**
     * Sets certain properties on  box to distinguish it from the flipped box.
     */
    private void normal() {
        dialog.setPadding(new Insets(5, 10, 5, 10));
    }

    /**
     * Creates a dialog box to represent user information.
     *
     * @param text represents input shown by user
     * @param img represents picture of user
     * @return dialog box containing user's message and picture
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.normal();
        return db;
    }

    /**
     * Creates a dialog box to represent program information.
     *
     * @param text represents message shown by program
     * @param img represents picture of program
     * @return dialog box containing program's message and picture
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}

