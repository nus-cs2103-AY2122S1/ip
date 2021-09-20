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
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox.
     *
     * @param text The text to be inputted into the DialogBox.
     * @param img The image associated with the "sender" of the text.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(duke.ui.MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        // dialog.setPadding(new Insets(0,10,0,10));

        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(50, 35, 35));

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
     * This presents the UserDialog in the form of a DialogBox.
     *
     * @param text The text inputted by the user.
     * @param img The image representative of the user.
     * @return the user's dialogue in the form of a DialogBox.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * This presents the Robot's Dialog in the form of a DialogBox.
     *
     * @param text The text outputted by the robot.
     * @param img The image representative of the robot.
     * @return the robot's dialogue in the form of a DialogBox.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        // db.setStyle("-fx-background-color: #c6e6fb; -fx-background-radius: 20px; -fx-border-radius: 20px;");
        db.flip();
        return db;
    }
}
