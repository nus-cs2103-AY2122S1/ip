package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a Dialog Box in the Duke GUI.
 */
public class DialogBox extends HBox {
    private final Label text;
    private final ImageView displayPicture;

    /**
     * Constructor of the DialogBox class.
     * @param text The text to be displayed in the dialog box.
     * @param displayPicture The image beside the text of the dialog.
     */
    public DialogBox(Label text, ImageView displayPicture) {
        this.text = text;
        this.displayPicture = displayPicture;

        this.text.setWrapText(true);
        this.displayPicture.setFitWidth(50.0);
        this.displayPicture.setFitHeight(50.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label text, ImageView displayPicture) {
        return new DialogBox(text, displayPicture);
    }

    public static DialogBox getDukeDialog(Label text, ImageView displayPicture) {
        var db = new DialogBox(text, displayPicture);
        db.flip();
        return db;
    }
}
