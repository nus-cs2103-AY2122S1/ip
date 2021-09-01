package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * This class encapsulates a box to contain the dialog between Duke and the user.
 *
 * @author Kleon Ang
 */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor for a DialogBox.
     *
     * @param l The Label containing the dialog text.
     * @param iv The ImageView containing the picture of Duke or the user.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(text, displayPicture);
        this.setSpacing(10);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tempList = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tempList);
        this.getChildren().setAll(tempList);
    }

    /**
     * Gets a DialogBox containing user dialog.
     *
     * @param l The Label containing the user dialog.
     * @param iv The ImageView containing the image of the user.
     * @return A DialogBox with the user dialog.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Gets a DialogBox containing Duke's dialog.
     *
     * @param l The Label containing Duke's dialog.
     * @param iv The ImageView containing the image of Duke.
     * @return A DialogBox with Duke's dialog.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        DialogBox db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
