package duke.gui;

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
import javafx.scene.shape.Circle;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 *
 * Adapted from JavaFX tutorial 4: https://se-education.org/guides/tutorials/javaFxPart4.html
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, String textBackgroundColor) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        dialog.setText(text);
        dialog.setStyle(
                "-fx-background-radius: 30px; "
                + "-fx-background-color: " + textBackgroundColor + ";"
                + " -fx-padding: 20;");
        displayPicture.setImage(img);
        Circle circlePicture = new Circle(60, 50, 50);
        displayPicture.setClip(circlePicture);
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
     * Creates a dialog box from the user.
     *
     * @param text Text for label.
     * @param img User's image.
     * @return A dialog box with the given text and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, "rgba(0, 200, 255, 0.3)");
    }

    /**
     * Creates a dialog box from Duke.
     * Dialog box is flipped.
     *
     * @param text Text for label.
     * @param img Duke's image.
     * @return A dialog box with the given text and image.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, "rgba(0, 255, 0, 0.3)");
        db.flip();
        return db;
    }

    /**
     * Creates a dialog box from Duke that informs the user of an error.
     * Dialog box is flipped.
     *
     * @param text Text for label.
     * @param img Duke's image.
     * @return A dialog box with the given text and image.
     */
    public static DialogBox getErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img, "rgba(255, 0, 0, 0.3)");
        db.flip();
        return db;
    }
}
