package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Controller class for DialogBox.fxml
 * Code re-used from https://se-education.org/guides/tutorials/javaFxPart4.html
 */
public class DialogBox extends HBox {

    @FXML
    private Label text;
    @FXML
    private final ImageView DISPLAY_PICTURE;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        DISPLAY_PICTURE = iv;

        text.setWrapText(true);
        DISPLAY_PICTURE.setFitWidth(100.0);
        DISPLAY_PICTURE.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, DISPLAY_PICTURE);
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
     * Returns a DialogBox for User Content.
     *
     * @param l Label to add into the DialogBox.
     * @param iv Image to add into the DialogBox.
     * @return DialogBox with Label and Image.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Returns a flipped DialogBox for Duke Content.
     *
     * @param l Label to add into the DialogBox.
     * @param iv Image to add into the DialogBox.
     * @return DialogBox with Label and Image.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
