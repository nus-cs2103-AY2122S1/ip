package duke.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UserDialogBox extends HBox implements DialogBoxStyle {

    private Label text;
    private ImageView displayPicture;

    /**
     * A constructor for a dialog box that represents the User's input messages.
     * @param l The Label component for the message
     * @param iv The ImageView containing user's picture
     */
    public UserDialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        styleUserTextLabels(text);
        styleDisplayPicture(displayPicture);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }
}