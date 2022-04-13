package ui;

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
 * An example of a custom control using FXML.
 * DialogBox control consists of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private Circle circleMask;

    /**
     * Constructs a new DialogBox.
     *
     * @param text text to be shown on the user interface.
     * @param img image to be shown on the user interface.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class
                    .getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // @@author DrWala-reused
        // Display mask idea by Azeem Arshad Vasanwala from https://github.com/DrWala/.
        setupDisplayMask();

        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(circleMask);
    }

    /**
     * Formats the image to have a circular frame.
     */
    private void setupDisplayMask() {
        double centerX = displayPicture.getFitWidth() / 2;
        double centerY = displayPicture.getFitHeight() / 2;
        double radius = Math.min(displayPicture.getFitWidth(), displayPicture.getFitHeight()) / 2.0;
        circleMask = new Circle(centerX, centerY, radius);
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
     * Retrieves a DialogBox for the user.
     *
     * @param text text the user inputted.
     * @param img image avatar of the user
     * @return DialogBox containing the text of user and image of user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Retrieves a DialogBox for duke.
     *
     * @param text text the user inputted.
     * @param img image avatar of duke.
     * @return DialogBox containing the text from duke and image of duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);

        // @@author DrWala-reused
        // Flip dialogBox's orientation idea by Azeem Arshad Vasanwala from https://github.com/DrWala/.
        db.flip();
        return db;
    }
}
