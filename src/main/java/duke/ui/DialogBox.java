package duke.ui;

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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle displayPicture;

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
        displayPicture.setFill(new ImagePattern(img));
    }

    /**
     * Sets dialog red.
     */
    private void setBlackBackground() {
        dialog.setStyle("-fx-background-color: black; -fx-background-radius: 10;");
    }

    private void setRedBackground() {
        dialog.setStyle("-fx-background-color: red; -fx-background-radius: 10;");
    }

    private void setWhiteText() {
        dialog.setTextFill(Color.WHITE);
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
     * Returns a user dialog with given image and text.
     *
     * @param text Text in the user dialog.
     * @param img Image in the user dialog.
     * @return user dialog with given image and text.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns a Duke dialog with given image and text.
     *
     * @param text Text in the user dialog.
     * @param img Image in the user dialog.
     * @return Duke dialog with given image and text.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setBlackBackground();
        db.setWhiteText();
        return db;
    }

    /**
     * Returns a Duke dialog with given image and text.
     *
     * @param text Text in the user dialog.
     * @param img Image in the user dialog.
     * @return Duke dialog with given image and text.
     */
    public static DialogBox getDukeFailureDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setRedBackground();
        db.setWhiteText();
        return db;
    }
}
