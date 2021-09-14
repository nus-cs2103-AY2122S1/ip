package duke.javafx;

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
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    /** Text message for the DialogBox. */
    @FXML
    private Text dialog;
    /** Display picture for the DialogBox. */
    @FXML
    private ImageView displayPicture;

    /**
     * Initialises an instance of DialogBox.
     *
     * @param text text message to show in DialogBox.
     * @param img  image to be used as display picture in DialogBox.
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
        double radius = Math.max(displayPicture.getFitWidth(), displayPicture.getFitHeight()) / 2;
        Circle clip = new Circle(radius);
        clip.setCenterX(displayPicture.getFitWidth() / 2);
        clip.setCenterY(displayPicture.getFitWidth() / 2);
        displayPicture.setClip(clip);
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
     * Gets user dialog.
     *
     * @param text the text input from the user.
     * @param img  the profile image of the user.
     * @return DialogBox with the user input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Gets duke dialog.
     *
     * @param text the text reply from duke.
     * @param img  the profile image of duke.
     * @return DialogBox with the reply from duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

}
