package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class DialogBox extends HBox {
    private static final double imageSize = 90.0;
    private static final Background dukeBG = new Background(new BackgroundFill(
        Color.rgb(83, 215, 105), new CornerRadii(10), new Insets(5)));
    private static final Background userBG = new Background(new BackgroundFill(
        Color.rgb(90, 200, 250), new CornerRadii(10), new Insets(5)));
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        //Solution below adapted from https://stackoverflow.com/a/20708703
        displayPicture.setClip(new Circle(imageSize / 2, imageSize / 2, imageSize / 2));
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

    /**
     * Creates and returns a user dialog box.
     *
     * @param text The text to be used as the message.
     * @param img The image to be used as the icon.
     * @return The user dialog box created.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        Label tempLabel = (Label) db.lookup("#dialog");
        tempLabel.setBackground(userBG);
        return db;
    }

    /**
     * Creates and returns a duke dialog box.
     *
     * @param text The text to be used as the message.
     * @param img The image to be used as the icon.
     * @return The duke dialog box created.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        Label tempLabel = (Label) db.lookup("#dialog");
        db.flip();
        tempLabel.setBackground(dukeBG);
        return db;
    }
}
