package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Dialog Box for Duke program GUI.
 */
public class DialogBox extends HBox {
    private Label text;


    public DialogBox(Label l) {
        text = l;

        text.setWrapText(true);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text);
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
     * Gets user dialog and return a DialogBox.
     *
     * @param l The user dialog
     * @return DialogBox object containing the dialog.
     */
    public static DialogBox getUserDialog(Label l) {
        return new DialogBox(l);
    }

    /**
     * Gets user dialog and return a DialogBox.
     *
     * @param l The user dialog
     * @return DialogBox object containing the dialog.
     */
    public static DialogBox getDukeDialog(Label l) {
        var db = new DialogBox(l);
        db.flip();
        return db;
    }
}
