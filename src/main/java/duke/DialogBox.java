package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 *
 * @author Toh Wang Bin, with reference to SE-EDU
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor for a DialogBox instance.
     *
     * @param l The label of the dialog box.
     * @param iv The image associated with the DialogBox.
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
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns the DialogBox from a user perspective.
     *
     * @param l The label of the dialog box.
     * @param iv The image associated with the DialogBox.
     * @return The DialogBox instance as required.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Returns the DialogBox from the chatbat's perspective.
     *
     * @param l The label of the dialog box.
     * @param iv The image associated with the DialogBox.
     * @return The DialogBox instance as required.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

}
