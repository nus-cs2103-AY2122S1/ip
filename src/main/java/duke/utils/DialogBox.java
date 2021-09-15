package duke.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor that initializes a dialogbox with a label and an image
     * @param l text that will be in the box
     * @param iv image that will be next to the text
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
     * Constructor that initializes a dialogbox with a label only
     * @param l text that will be in the box
     */
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

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

    public static DialogBox getDukeDialog(Label l) {
        var db = new DialogBox(l);
        db.flip();
        return db;
    }

}
