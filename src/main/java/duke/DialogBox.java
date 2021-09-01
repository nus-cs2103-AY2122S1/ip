package duke;

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

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    private static final Ui ui = new Ui();

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox.
     *
     * @param text Text String to be displayed in DialogBox.
     * @param img Image to be displayed alongside text in DialogBox.
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
     * Returns a DialogBox.
     * The returned DialogBox will be anchored to the right of its container.
     *
     * @param text Text String to be displayed in DialogBox.
     * @param img Image to be displayed alongside text in DialogBox.
     * @return DialogBox containing specified text and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns a flipped DialogBox.
     * The returned DialogBox will be anchored to the left of its container.
     *
     * @param text Text String to be displayed in DialogBox.
     * @param img Image to be displayed alongside text in DialogBox.
     * @return Flipped DialogBox containing specified text and image.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Returns a DialogBox for greeting user.
     * The returned DialogBox contains the standard welcome message.
     *
     * @param img Image to be displayed alongside welcome message in DialogBox.
     * @return DialogBox containing welcome message and specified image.
     */
    public static DialogBox getDukeWelcome(Image img) {
        DialogBox db = new DialogBox(ui.getWelcomeMessage(), img);
        db.flip();
        return db;
    }

    /**
     * Returns a DialogBox for saying goodbye to the user.
     * The returned DialogBox contains the standard goodbye message.
     *
     * @param img Image to be displayed alongside goodbye message in DialogBox.
     * @return DialogBox containing goodbye message and specified image.
     */
    public static DialogBox getDukeBye(Image img) {
        DialogBox db = new DialogBox(ui.getGoodbyeMessage(), img);
        db.flip();
        return db;
    }
}
