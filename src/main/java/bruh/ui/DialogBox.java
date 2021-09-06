package bruh.ui;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * A box consisting of a text message and the sender's display picture, used to represent the view
 * for a single message sent in the chatbot.
 */
public class DialogBox extends HBox {
    /**
     * Constructor for a dialog box, representing a message in the chatbot.
     *
     * @param text The view containing the text content of the message.
     * @param displayPicture The view containing the display picture of the message sender.
     */
    public DialogBox(Label text, ImageView displayPicture) {
        super(10.0);
        text.setWrapText(true);
        text.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

        displayPicture.setClip(new Circle(25.0, 25.0, 25.0, Color.WHITE));
        displayPicture.setPreserveRatio(true);
        displayPicture.setFitWidth(50.0);
        displayPicture.setFitHeight(50.0);
    }
}
