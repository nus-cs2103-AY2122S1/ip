package bot.utility;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, boolean isBot, Image img) {
        try {
            FXMLLoader fxmlLoader;
            if (isBot) {
                fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/BotDialogBox.fxml"));
                setAlignment(Pos.TOP_LEFT);
            } else {
                fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/UserDialogBox.fxml"));
            }
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Rectangle clip = new Rectangle(displayPicture.getFitWidth(), displayPicture.getFitHeight());
        clip.setArcWidth(100);
        clip.setArcHeight(100);
        displayPicture.setClip(clip);
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Returns a DialogBox with the user input and the accompanying image.
     *
     * @param text The text given by the user input.
     * @param img The image given to represent the user.
     * @return A DialogBox containing a message given by the user input and an image to represent the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, false, img);
    }

    /**
     * Returns a DialogBox with the response by Duke and the accompanying image.
     *
     * @param text The text produced by Duke.
     * @param img The image given to represent Duke.
     * @return A DialogBox containing a message given by Duke and an image to represent Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        return new DialogBox(text, true, img);
    }
}
