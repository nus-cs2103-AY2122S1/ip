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
import javafx.scene.paint.Color;

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
        assert this.getAlignment() == Pos.TOP_RIGHT
                : "flip should only be used on a DialogBox that is aligned to TOP_RIGHT";
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a DialogBox meant to echo User input.
     * The returned DialogBox will be anchored to the right of its container.
     *
     * @param text Text String to be displayed in DialogBox.
     * @param img Image to be displayed alongside text in DialogBox.
     * @return DialogBox containing specified text and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        String modifiedText = "You: " + text;
        DialogBox db = new DialogBox(modifiedText, img);
        db.dialog.setStyle(db.dialog.getStyle() + " -fx-background-color: Peachpuff;");
        return db;
    }

    /**
     * Returns a flipped DialogBox meant to display Duke response.
     * The returned DialogBox will be anchored to the left of its container.
     *
     * @param text Text String to be displayed in DialogBox.
     * @param img Image to be displayed alongside text in DialogBox.
     * @return Flipped DialogBox containing specified text and image.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        String modifiedText = "Duke: " + text;
        DialogBox db = new DialogBox(modifiedText, img);
        db.dialog.setStyle(db.dialog.getStyle() + " -fx-background-color: Seashell;");
        db.flip();
        return db;
    }

    /**
     * Returns a flipped DialogBox meant to display error messages.
     *
     * @param text Text String to be displayed in DialogBox.
     * @param img Image to be displayed alongside text in DialogBox.
     * @return Flipped DialogBox containing an image and error message.
     */
    public static DialogBox getErrorDialog(String text, Image img) {
        String modifiedText = "Error: " + text;
        DialogBox db = new DialogBox(modifiedText, img);
        db.dialog.setStyle(db.dialog.getStyle() + " -fx-background-color: Salmon;");
        db.dialog.setTextFill(Color.WHITE);
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
        return getDukeDialog(ui.getWelcomeMessage(), img);
    }

    /**
     * Returns a DialogBox for saying goodbye to the user.
     * The returned DialogBox contains the standard goodbye message.
     *
     * @param img Image to be displayed alongside goodbye message in DialogBox.
     * @return DialogBox containing goodbye message and specified image.
     */
    public static DialogBox getDukeBye(Image img) {
        return getDukeDialog(ui.getGoodbyeMessage(), img);
    }

}
