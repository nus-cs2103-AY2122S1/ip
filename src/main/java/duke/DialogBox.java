package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * DialogBox represents a UI container for text and profile images
 */
public class DialogBox extends HBox {

    /**
     * Sole constructor for dialog box.
     *
     * @param l label to display.
     * @param iv picture to display.
     */
    private DialogBox(Label l, ImageView iv) {
        assert l != null : "Label should not be null.";
        assert iv != null : "Image should not be null.";

        final Circle clip = new Circle(40, 40, 40);
        iv.setClip(clip);

        l.setWrapText(true);
        iv.setFitWidth(80.0);
        iv.setFitHeight(80.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(l, iv);
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
     * Creates a user dialog box
     *
     * @param l label to display
     * @param iv image to display
     * @return dialog box for user inputs
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        l.setStyle("-fx-background-color: #4cc5e2; "
                + "-fx-text-fill: #122448;"
                + "-fx-background-radius: 5; "
                + "-fx-padding: 5;");
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

    /**
     * Creates a Duk dialog box
     *
     * @param l label to display
     * @param iv image to display
     * @return dialog box for Duk responses
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        l.setStyle("-fx-background-color: #122448; "
                + "-fx-background-radius: 5;"
                + "-fx-padding: 5; "
                + "-fx-text-fill: #4cc5e2;");
        var db = new DialogBox(l, iv);
        return db;
    }
}
