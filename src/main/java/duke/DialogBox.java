package duke;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends VBox {
    @FXML
    private Text dialog;
    @FXML
    private Pane imagePane;
    @FXML
    private Circle imageCircle;
    @FXML
    private HBox textBackgroundBox;

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
        imageCircle.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        textBackgroundBox.setStyle("-fx-background-color: #e7efc5; -fx-background-radius: 20;");
        imagePane.setTranslateX(0);
    }

    /**
     * Returns a dialog box representing a User dialog (i.e.) image on right side and blue background color.
     *
     * @param text Text to be shown in the dialog box.
     * @param img Image to be used as profile image.
     * @return Dialog Box with Text and profile image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns a dialog box representing a Bot dialog (i.e.) image on left side and yellow background color.
     *
     * @param text Text to be shown in the dialog box.
     * @param img Image to be used as profile image.
     * @return Dialog Box with Text and profile image.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
