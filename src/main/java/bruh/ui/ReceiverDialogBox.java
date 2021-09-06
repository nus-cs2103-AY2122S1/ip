package bruh.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * Represents a dialog box for a received message.
 */
public class ReceiverDialogBox extends DialogBox {
    /**
     * Constructor for a dialog box, representing a received message.
     *
     * @param text The view containing the text content of the message.
     * @param displayPicture The view containing the display picture of the message sender.
     */
    public ReceiverDialogBox(Label text, ImageView displayPicture) {
        super(text, displayPicture);
        this.setAlignment(Pos.TOP_LEFT);
        this.getChildren().addAll(displayPicture, text);
    }
}
