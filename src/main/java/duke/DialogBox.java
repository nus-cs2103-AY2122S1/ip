package duke;

import java.io.IOException;

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
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * Controller for the DialogBox, which represents a message box.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;

    @FXML
    private Circle profilePicture;

    /**
     * Constructs a DialogBox from a text and image.
     * @param text Messsage to be displayed by this DialogBox
     * @param img Image to represent the sender of the message
     */
    public DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        profilePicture.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.dialog.setBackground(new Background(
                new BackgroundFill(
                        Paint.valueOf("d9f0fa"),
                        new CornerRadii(8, 0, 8, 8, false),
                        Insets.EMPTY)));
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialog.setBackground(new Background(
                new BackgroundFill(
                        Paint.valueOf("40aede"),
                        new CornerRadii(0, 8, 8, 8, false),
                        Insets.EMPTY)));
        return db;
    }
}
