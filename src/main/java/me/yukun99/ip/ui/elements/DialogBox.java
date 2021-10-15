package me.yukun99.ip.ui.elements;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, boolean isBot, boolean isError) {
        try {
            FXMLLoader fxmlLoader;
            if (isBot) {
                if (isError) {
                    fxmlLoader = new FXMLLoader(Window.class.getResource("/views/ErrorDialogBox.fxml"));
                } else {
                    fxmlLoader = new FXMLLoader(Window.class.getResource("/views/BotDialogBox.fxml"));
                }
            } else {
                fxmlLoader = new FXMLLoader(Window.class.getResource("/views/UserDialogBox.fxml"));
            }
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        Image image = isBot
                ? new Image(this.getClass().getResourceAsStream("/images/HelpBot.png"))
                : new Image(this.getClass().getResourceAsStream("/images/User.png"));
        displayPicture.setImage(image);
    }

    /**
     * Factory method for a new DialogBox instance for bot messages.
     *
     * @param text Bot message to include in the DialogBox.
     * @param isError Whether the message is an error message.
     * @return DialogBox instance created.
     */
    public static DialogBox getBotDialog(String text, boolean isError) {
        return new DialogBox(text, true, isError);
    }

    /**
     * Factory method for a new DialogBox instance for user messages.
     *
     * @param text User message to include in the DialogBox.
     * @return DialogBox instance created.
     */
    public static DialogBox getUserDialog(String text) {
        return new DialogBox(text, false, false);
    }
}
