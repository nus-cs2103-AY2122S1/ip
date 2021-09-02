package bot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A class that encapsulates a single Dialogue Box.
 * The Dialogue Box can either be to display user input, or to display Duke's response.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor for the DialogBox class.
     *
     * @param l The Label object that contains the text to be displayed to the user.
     * @param iv The ImageView object that contains the image to be displayed to the user.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     *
     * Takes no parameters.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns the DialogBox object that contains the Label and the ImageView given.
     * This DialogBox displays the user's input.
     *
     * @param l The Label object that contains the text to be displayed to the user.
     * @param iv The ImageView object that contains the image to be displayed to the user.
     * @return A DialogBox containing the given parameters.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Returns the DialogBox object that contains the Label and the ImageView given.
     * Flips the DialogBox so that the correct orientation is shown to the user.
     * This DialogBox displays Duke's response.
     *
     * @param l The Label object that contains the text to be displayed to the user.
     * @param iv The ImageView object that contains the image to be displayed to the user.
     * @return A DialogBox containing the given parameters.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}