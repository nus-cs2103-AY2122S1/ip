package duke.gui;

import java.io.IOException;
import java.util.Collections;

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
import javafx.scene.shape.Circle;

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
        
        // crop displayPicture image to be circular
        double centerX = displayPicture.getFitWidth() / 2;
        double centerY = displayPicture.getFitHeight() / 2;
        double radius = (centerX + centerY) / 2;
        Circle clip = new Circle(centerX, centerY, radius);
        displayPicture.setClip(clip);
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

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox userDialog = new DialogBox(text, img);
        userDialog.dialog.setStyle("-fx-background-color: aquamarine; -fx-padding: 9px; " +
                "-fx-background-radius: 9px;");
        return userDialog;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox dukeDialog = new DialogBox(text, img);
        dukeDialog.flip();
        dukeDialog.dialog.setStyle("-fx-background-color: antiquewhite; -" +
                "fx-padding: 9px; -fx-background-radius: 9px;");
        return dukeDialog;
    }
}
