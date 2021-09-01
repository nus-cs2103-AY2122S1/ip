package whobot.main.gui;

import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public class BotDialogBox extends HBox {

    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    @FXML
    private AnchorPane dialogContainer;

    /***
     * Constructor for BotDialogBox Class
     *
     * @param text String to Display
     */
    BotDialogBox(String text, boolean isContinued, double delay) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/BotDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isContinued) {
            displayPicture.setVisible(false);
        }
        displayText(text, delay);
        dialog.setMinHeight(Region.USE_PREF_SIZE);
    }

    private void displayText(String text, double delay) {
        dialog.setText("");
        final IntegerProperty i = new SimpleIntegerProperty(0);
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame (
                Duration.millis(delay), event -> {
            if (i.get() > text.length()) {
                timeline.stop();
            } else {
                dialog.setText(text.substring(0, i.get()));
                i.set(i.get() + 1);
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public static BotDialogBox getDialog(String text, boolean isContinued, double delay) {
        var db = new BotDialogBox(text, isContinued, delay);
        return db;
    }

}
