package titi.gui;

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


/**
 * Represents a dialog box consisting of an ImageView to represent the speaker
 * and a label containing text from the speaker.
 * Adapted from https://se-education.org/guides/index.html
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;


    /**
     * Initialises a DialogBox instance.
     *
     * @param l text label
     * @param iv picture
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
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }


    /**
     * Returns the dialogue box to represent user input.
     *
     * @param l user input text label
     * @param iv uer profile picture
     * @return dialogue box containing label and picture aligned to the right
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        var b = new DialogBox(l, iv);
        b.setBackground(new Background(new BackgroundFill(
                Color.LAVENDERBLUSH, CornerRadii.EMPTY, Insets.EMPTY)));
        return b;
    }

    /**
     * Returns the dialogue box to represent TiTi output.
     *
     * @param l output text label
     * @param iv TiTi profile picture
     * @return dialogue box containing picture and label aligned to left
     */
    public static DialogBox getTiTiDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        db.setBackground(new Background(new BackgroundFill(
                Color.AZURE, CornerRadii.EMPTY, Insets.EMPTY)));
        return db;
    }

}
