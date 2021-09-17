package duke.gui;

import java.io.IOException;
import java.util.Collections;

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
import javafx.scene.shape.Circle;

/**
 * Represents an example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox object, assigns the text to the Label and the image to be
     * the display picture.
     *
     * @param text Text to be assigned to the Label.
     * @param img Display Picture to be assigned to the Dialog Box.
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
     * Sets the Dimensions of the Circle for the Profile Picture.
     *
     * @param centreX x coordinate of the centre.
     * @param centreY y coordinate of the centre.
     * @param radius radius of the circle.
     */
    public void setPictureDimensions(int centreX, int centreY, int radius) {
        displayPicture.setClip(new Circle(centreX, centreY, radius));
    }

    /**
     * Creates a DialogBox for the User.
     *
     * @param text Text to be displayed by the User.
     * @param img Display picture of the User.
     * @return Dialog Box object for the User.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setPictureDimensions(48, 32, 30);
        db.setStyle("-fx-background-color: #FFFFFF;");
        db.setLabelPadding(new Insets(0, 0, 0, 25));
        return db;
    }

    /**
     * Sets padding of Label.
     *
     * @param insets Amount of padding to set for label.
     */
    private void setLabelPadding(Insets insets) {
        dialog.setPadding(insets);
    }


    /**
     * Creates a Dialog Box for Duke.
     *
     * @param text Text to be displayed by Duke.
     * @param img Display picture of Duke.
     * @return DialogBox object for Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setPictureDimensions(57, 30, 30);
        return db;
    }
}
