package bruh.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * Represents a dialog box for a message sent by Bruh.
 */
public class BruhDialogBox extends DialogBox {
    /**
     * Constructor for a dialog box, representing a message
     * sent by the chatbot.
     *
     * @param text           The view containing the text content of the message.
     * @param displayPicture The view containing the display picture of the message sender.
     */
    public BruhDialogBox(Label text, ImageView displayPicture) {
        super(text, displayPicture);
        this.setAlignment(Pos.TOP_LEFT);
        this.getChildren().addAll(displayPicture, text);
    }
}
