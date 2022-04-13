package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Encapsulates the dialog box to be displayed in the GUI.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Creates a dialog box containing an image and a text label.
     *
     * @param l The text label to be displayed.
     * @param iv The image to be displayed.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;
        text.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), Insets.EMPTY)));
        text.setPadding(new Insets(10));
        Circle clip = new Circle(50, 50, 40);
        iv.setClip(clip);

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setSpacing(10);
    }

    /**
     * Flips the dialog box so that the image is on the left and
     * the text label is on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates the dialog box that represents the user, with the user image
     * and text.
     *
     * @param l Text label belonging to the user.
     * @param iv Image belonging to the user.
     * @return A dialog box containing the user's image and text elements.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Creates the dialog box that represents Duke, with the duke image
     * and text.
     *
     * @param l Text label belonging to Duke.
     * @param iv Image belonging to Duke.
     * @return A dialog box containing Duke's image and text elements.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
