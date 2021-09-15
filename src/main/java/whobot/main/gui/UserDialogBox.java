package whobot.main.gui;

import java.io.IOException;

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

public class UserDialogBox extends HBox {

    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    @FXML
    private AnchorPane dialogContainer;

    UserDialogBox(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/UserDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Circle clip = new Circle();
        clip.setRadius(120);
        displayPicture.setClip(clip);

        SnapshotParameters snapshotParameters = new SnapshotParameters();
        snapshotParameters.setFill(Color.TRANSPARENT);
        WritableImage img = displayPicture.snapshot(snapshotParameters, null);

        displayPicture.setClip(null);
        displayPicture.setEffect(new DropShadow(20, Color.BLACK));
        displayPicture.setImage(img);
        displayPicture.setScaleX(-1);
        dialog.setText(text);
    }

    public static UserDialogBox getDialog(String text) {
        return new UserDialogBox(text);
    }

}
