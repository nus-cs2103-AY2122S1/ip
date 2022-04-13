// Solution below are adapted from https://se-education.org/guides/tutorials/javaFx.html
// Solution below are adapted from https://github.com/lll-jy/ip/blob/master/src/main/java/DialogBox.java
package catobot;

import java.io.IOException;
import java.util.Collections;

import catobot.exception.BotException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents the User Dialog Box.
 */
public class BotDialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private BotDialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    MainWindow.class.getResource("/view/BotDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        if (text.startsWith(BotException.INDICATOR)) {
            setExceptionStyle();
        }
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    private void setExceptionStyle() {
        String backgroundSetting = "-fx-background-color: #FCD5CB; -fx-text-fill: #8C062B;";
        String otherSetting = "-fx-label-padding: 8; -fx-border-radius: 5; -fx-background-radius: 5";
        dialog.setStyle(backgroundSetting + otherSetting);
    }

    /**
     * Gets the bot dialog box.
     *
     * @param text The text to be displayed.
     * @param img The profile image of bot.
     * @return The dialog box for bot response.
     */
    public static BotDialogBox getBotDialog(String text, Image img) {
        var db = new BotDialogBox(text, img);
        db.flip();
        return db;
    }
}
