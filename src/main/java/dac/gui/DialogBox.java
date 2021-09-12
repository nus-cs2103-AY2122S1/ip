package dac.gui;

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
    public static final String DIALOG_BOX_BACKGROUND_RADIUS = "30px";
    public static final String DIALOG_BOX_PADDING = "20";
    public static final String DIALOG_BOX_FONT_FAMILY = "Arial";
    public static final String DIALOG_BOX_FONT_SIZE = "12";
    public static final String USER_DIALOG_BOX_BACKGROUND_COLOR = "rgba(0, 200, 255, 0.3)";
    public static final String DUKE_DIALOG_BOX_BACKGROUND_COLOR = "rgba(0, 255, 0, 0.3)";
    public static final String ERROR_DIALOG_BOX_BACKGROUND_COLOR = "rgba(255, 0, 0, 0.3)";
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
                "-fx-background-radius:" + DIALOG_BOX_BACKGROUND_RADIUS + "; "
                + "-fx-background-color: " + textBackgroundColor + "; "
                + "-fx-padding: " + DIALOG_BOX_PADDING + "; "
                + "-fx-font-family: " + DIALOG_BOX_FONT_FAMILY + ";"
                + "-fx-font-size: " + DIALOG_BOX_FONT_SIZE + ";");
        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(60, 50, 50));
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
        return new DialogBox(text, img, USER_DIALOG_BOX_BACKGROUND_COLOR);
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
        var db = new DialogBox(text, img, DUKE_DIALOG_BOX_BACKGROUND_COLOR);
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
        var db = new DialogBox(text, img, ERROR_DIALOG_BOX_BACKGROUND_COLOR);
        db.flip();
        return db;
    }
}
