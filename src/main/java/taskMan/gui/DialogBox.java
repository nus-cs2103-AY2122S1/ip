package taskMan.gui;


import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;

/**
 * Controller for dialogBox
 */
// Solution adapted from https://se-education.org/guides/tutorials/javaFx.html
public class DialogBox extends HBox {

    @FXML
    private Text dialog;

    @FXML
    private ImageView displayPicture;

    /**
     * DialogBox is placeholder for output messages
     *
     * @param text details of the speech
     * @param speakerImage display picture of the current speaker
     * @param isDukeSpeaking check whether user or duke speaking
     */
    private DialogBox(String text, Image speakerImage, boolean isDukeSpeaking) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        alignDialogBoxBasedOnSpeaker(isDukeSpeaking);
        circleCropDisplayPicture(speakerImage);
        displayPicture.setImage(speakerImage);
        displayPicture.setRotationAxis(Rotate.Y_AXIS);
        displayPicture.setRotate(180);
    }

    /**
     * Realigns the box formatting based on whether user or program is currently speaking
     *
     * @param isDukeSpeaking
     */
    private void alignDialogBoxBasedOnSpeaker(boolean isDukeSpeaking) {
        if (isDukeSpeaking) {
            dialog.setTextAlignment(TextAlignment.LEFT);
        } else {
            dialog.setTextAlignment(TextAlignment.RIGHT);
        }
    }

    /**
     * Crops the image into a circle crop
     *
     * @param speakerImage
     */
    private void circleCropDisplayPicture(Image speakerImage) {
        Rectangle crop = createCircleCrop();
        displayPicture.setClip(crop);
        displayPicture.setImage(speakerImage);
    }

    /**
     * Create cicle crop
     *
     * @return rectangle crop
     */
    private Rectangle createCircleCrop() {
        double pictureWidth = displayPicture.getFitWidth();
        double pictureHeight = displayPicture.getFitHeight();
        Rectangle rectangle = new Rectangle(pictureWidth, pictureHeight);
        rectangle.setArcHeight(pictureHeight);
        rectangle.setArcWidth(pictureWidth);
        return rectangle;
    }


    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flipDialogBoxContentAlignment() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Static method that returns a user style message
     *
     * @param text
     * @param img
     * @return
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, false);
    }

    /**
     * Static method that returns a duke style message
     *
     * @param text
     * @param img
     * @return
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var dialogBox = new DialogBox(text, img, true);
        dialogBox.flipDialogBoxContentAlignment();
        return dialogBox;
    }

}