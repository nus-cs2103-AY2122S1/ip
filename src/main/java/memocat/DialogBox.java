package memocat;

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
 * A dialog box for user and memocat messages in GUI.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox.
     *
     * @param l  Label.
     * @param iv ImageView.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;
        int pictureRadius = 50;

        // create circle clipper for image view
        Circle clipper = new Circle(pictureRadius);
        clipper.setCenterX(pictureRadius);
        clipper.setCenterY(pictureRadius);
        // clip imageview with circle
        iv.setClip(clipper);

        text.setWrapText(true);
        // add text label horizontal paddings
        int textHorizontalPadding = 10;
        text.setPadding(new Insets(0, textHorizontalPadding, 0, textHorizontalPadding));
        displayPicture.setFitWidth(pictureRadius * 2);
        displayPicture.setFitHeight(pictureRadius * 2);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        // add dialog paddings in all directions
        int dialogPadding = 20;
        this.setPadding(new Insets(dialogPadding));
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
        Color userDialogBoxColor = Color.rgb(202, 225, 255);
        userDialog.setBackground(new Background(new BackgroundFill(
                userDialogBoxColor, new CornerRadii(20), new Insets(10))));
        return userDialog;
    }

    /**
     * Gets a dialog box for memocat response.
     *
     * @param l  Label of memocat response.
     * @param iv ImageView of memocat avatar.
     * @return Dialog box for memocat.
     */
    public static DialogBox getmemocatDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        // set memocat dialog background color
        Color memocatDialogColor = Color.rgb(255, 231, 186);
        db.setBackground(new Background(new BackgroundFill(
                memocatDialogColor, new CornerRadii(20), new Insets(10))));
        return db;
    }
}
