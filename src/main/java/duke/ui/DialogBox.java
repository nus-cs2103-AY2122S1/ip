package duke.ui;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle displayPicture;

    /**
     * Constructor for DialogBox
     * Creates a dialog box containing the user or duke image and text
     */
    private DialogBox(String text, Image img, Paint backgroundColour) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setFill(new ImagePattern(img));
        dialog.setTextFill(Color.BLACK);
        dialog.setBackground(new Background(new BackgroundFill(backgroundColour, CornerRadii.EMPTY, Insets.EMPTY)));

    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        this.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a dialog box such that the ImageView is on the right and text on the left.
     *
     * @return DialogBox returns dialog box containing user image and user text
     */
    public static DialogBox getUserDialog(String text, Image img) {

        return new DialogBox(text, img, Color.LIGHTGRAY);

    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     *
     * @return DialogBox returns dialog box containing Duke image and Duke text
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, Color.LIGHTBLUE);
        db.flip();
        return db;
    }
}
