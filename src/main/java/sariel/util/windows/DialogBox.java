package sariel.util.windows;

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
import javafx.scene.paint.Paint;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    public enum Color {
        RED,
        BLACK,
        GREEN
    };
    private static Paint textColor = Paint.valueOf("black");

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
    }

    /**
     * Sets the color of the text for the dialog box to use.
     *
     * @param color The color used.
     */
    public static void setLabelColor(Color color) {
        switch (color) {
        case RED:
            textColor = Paint.valueOf("Red");
            break;
        case BLACK:
            textColor = Paint.valueOf("black");
            break;
        case GREEN:
            textColor = Paint.valueOf("green");
            break;
        default:
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

    /**
     * Creates a user dialog box from the input image and text.
     *
     * @param text Text to print.
     * @param img Image representing the user.
     * @return DialogBox representing the dialog
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setTextFill(textColor);
        db.dialog.setOpacity(1.0);
        db.dialog.setStyle("-fx-background-color:Azure");
        db.setStyle("-fx-background-color:Azure");
        return db;
    }

    /**
     *Creates a duke dialog box from the input text and image.
     *
     * @param text Text to print.
     * @param img Image representing the Duke.
     * @return
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setTextFill(textColor);
        db.dialog.setOpacity(1.0);
        db.setHeight(db.dialog.getHeight());
        db.flip();
        db.dialog.setStyle("-fx-background-color:LightCyan");
        db.setStyle("-fx-background-color:LightCyan");
        return db;
    }


}
