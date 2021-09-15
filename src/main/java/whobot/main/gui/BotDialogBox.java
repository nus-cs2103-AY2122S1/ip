package whobot.main.gui;

import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
        } else {
            Circle clip = new Circle();
            clip.setRadius(120);
            displayPicture.setClip(clip);

            SnapshotParameters snapshotParameters = new SnapshotParameters();
            snapshotParameters.setFill(Color.TRANSPARENT);
            WritableImage img = displayPicture.snapshot(snapshotParameters, null);

            displayPicture.setClip(null);
            displayPicture.setEffect(new DropShadow(20, Color.BLACK));
            displayPicture.setImage(img);
        }
        displayText(text, delay);
        //dialog.setMinHeight(Region.USE_PREF_SIZE);
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
