package duke.gui;

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
     * DialogBox constructor.
     *
     * @param label Label for created dialogBox.
     * @param imageView ImageView for created dialogBox.
     */
    public DialogBox(Label label, ImageView imageView) {
        text = label;
        displayPicture = imageView;

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
     * Returns a user DialogBox.
     *
     * @param label
     * @param imageView
     * @return DialogBox originating from user with specified label and image.
     */
    public static DialogBox getUserDialog(Label label, ImageView imageView) {
        return new DialogBox(label, imageView);
    }

    /**
     * Returns a Duke DialogBox.
     *
     * @param label
     * @param imageView
     * @return DialogBox originating from Duke with specified label and image.
     */
    public static DialogBox getDukeDialog(Label label, ImageView imageView) {
        var db = new DialogBox(label, imageView);
        db.flip();
        return db;
    }
}
