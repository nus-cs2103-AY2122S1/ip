package duke.ui;

import java.io.IOException;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
    private HBox dialogBubble;
    @FXML
    private ImageView displayPicture;
    @FXML
    private Circle clip;

    private DialogBox(String text, Image img, Insets labelMargin, Paint backgroundColor, Paint textColor) {
        // Initialisation
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setTextFill(textColor);
        displayPicture.setImage(img);

        // Set circle clip for ImageView
        clip = new Circle(50, 50, 50); // Circular clip for image
        displayPicture.setClip(clip);

        // Add custom styling for dialog bubbles
        dialog.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
        dialogBubble.setPadding(labelMargin);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Factory method to create a DialogBox for the user's input.
     *
     * @param text String containing the user's original input command.
     * @param img Image of the user's profile picture.
     * @return DialogBox containing the user's input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, new Insets(0, 16, 0, 0), Color.LIGHTBLUE, Color.BLACK);
    }

    /**
     * Factory method to create a DialogBox for Duke's usual response when the command is successful.
     *
     * @param text String containing Duke's response to the user's input command.
     * @param img Image of Duke's profile picture.
     * @return DialogBox containing Duke's response.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img, new Insets(0, 0, 0, 16),
            Color.LIGHTGRAY, Color.BLACK
        );
        db.flip();
        return db;
    }

    /**
     * Factory method to create a DialogBox for Duke's response when the command results in an error.
     *
     * @param text String containing Duke's response to the user's input command.
     * @param img Image of Duke's profile picture.
     * @return DialogBox containing Duke's response.
     */
    public static DialogBox getDukeErrorDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img, new Insets(0, 0, 0, 16),
            Color.LIGHTGRAY, Color.RED
        );
        db.flip();
        return db;
    }
}
