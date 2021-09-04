package duke.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class UserDialogBox extends HBox {

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

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }
}