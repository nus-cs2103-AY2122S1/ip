package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * Implements the DialogBox used in the GUI.
 */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox used in the GUI.
     * @param l The label in the DialogBox.
     * @param iv The image displayed in the DialogBox.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;
        Circle clip = new Circle(25, 25, 25);
        displayPicture.setClip(clip);

        text.setWrapText(true);
        text.setPadding(new Insets(0, 10, 0, 10));
        displayPicture.setFitWidth(50.0);
        displayPicture.setFitHeight(50.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Gets the user dialogue.
     * @param l The label in the user dialogue.
     * @param iv The user's image.
     * @return A DialogBox with the user's input and image.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Gets Duke's dialogue.
     * @param l The label in Duke's dialogue.
     * @param iv Duke's image.
     * @return A DialogueBox with Duke's response and image.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
