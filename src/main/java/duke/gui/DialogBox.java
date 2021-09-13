
package duke.gui;

import java.io.IOException;
import java.util.Collections;

import duke.util.Ui;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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

    private DialogBox(String text, Image img, String textBoxColor) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        dialog.setMinHeight(Region.USE_PREF_SIZE);
        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(45, 45, 45));
        dialog.setStyle(
                "-fx-background-color: " + textBoxColor + "; "
                + "-fx-font-family: Verdana;"
                + "-fx-font-size: 12;"
                + (text.contains("Error")
                        ? "-fx-border-style: solid;" + "-fx-border-color: black;" + "-fx-border-width: 3;"
                        : ""));
        dialog.setPadding(new Insets(10));
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
        return new DialogBox(text, img, "#ffd0cf");
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db;
        if (text.contains("Error")) {
            db = new DialogBox(text, img, "#fdab9f");
        } else {
            db = new DialogBox(text, img, "#a5c5c3");
        }
        db.flip();
        return db;
    }

    public static DialogBox getIntroDialog(Image dukeImage) {
        String introMessage = Ui.getIntroMessage();
        return getDukeDialog(introMessage, dukeImage);
    }
}