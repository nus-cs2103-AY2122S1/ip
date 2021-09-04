package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Dialog box containing text and profile image
 */
public class DialogBox extends HBox {

    /**
     * Public constructor for dialog box.
     * @param l label to display.
     * @param iv picture to display.
     */
    public DialogBox(Label l, ImageView iv) {
        l.setWrapText(true);
        iv.setFitWidth(100.0);
        iv.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(l, iv);
    }

    /**
     * Creates a user dialog box
     *
     * @param l label to display
     * @param iv image to display
     * @return dialog box for user inputs
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
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
     * Creates a Duk dialog box
     *
     * @param l label to display
     * @param iv image to display
     * @return dialog box for Duk responses
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
