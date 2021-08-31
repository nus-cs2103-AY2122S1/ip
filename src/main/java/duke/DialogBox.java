package duke;

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
 * A dialog box for user and duke messages in GUI.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor of DialogBox.
     *
     * @param l  Label.
     * @param iv ImageView
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        // create circle clipper for image view
        Circle clipper = new Circle(50);
        clipper.setCenterX(50);
        clipper.setCenterY(50);
        // clip imageview with circle
        iv.setClip(clipper);

        text.setWrapText(true);
        // add text label horizontal paddings
        text.setPadding(new Insets(0, 10, 0, 10));
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        // add dialog paddings in all directions
        this.setPadding(new Insets(20, 20, 20, 20));
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
     * Gets a dialog box for user input.
     *
     * @param l  Label of user input.
     * @param iv ImageView of user avatar.
     * @return Dialog box for the user.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        var userDialog = new DialogBox(l, iv);
        // set user dialog background color
        userDialog.setBackground(new Background(new BackgroundFill(Color.rgb(202, 225, 255),
                new CornerRadii(20), new Insets(10))));
        return userDialog;
    }

    /**
     * Gets a dialog box for duke response.
     *
     * @param l  Label of duke response.
     * @param iv ImageView of duke avatar.
     * @return Dialog box for duke.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        // set duke dialog background color
        db.setBackground(new Background(new BackgroundFill(Color.rgb(255, 231, 186),
                new CornerRadii(20), new Insets(10))));
        return db;
    }
}
