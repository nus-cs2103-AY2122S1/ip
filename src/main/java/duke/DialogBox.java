package duke;

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

        // Some magic numbers to get the circle to fit nicely
        double circleHeight = displayPicture.getFitHeight() / 2;
        double circleWidth = displayPicture.getFitWidth() / 2;
        displayPicture.setClip(
                new Circle(circleWidth - 2, circleHeight,displayPicture.getFitHeight() / 2.1));
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.dialog.getStyleClass().add("user-fill");
        return db;
    }

    public static DialogBox getDukeDialog(Pair<String, Boolean> response, Image img) {
        String dukeResponse = response.getFirst();
        boolean isError = response.getSecond();
        DialogBox db = new DialogBox(dukeResponse, img);
        db.flip();
        if (isError) {
            db.dialog.getStyleClass().add("error-fill");
        } else {
            db.dialog.getStyleClass().add("duke-fill");
        }
        return db;
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
}
