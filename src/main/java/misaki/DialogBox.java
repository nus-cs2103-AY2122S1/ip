package misaki;

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
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import misaki.ui.MainWindow;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the
 * speaker's face and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private final Circle clip = new Circle(50, 50, 50);

    /**
     * A constructor for DialogBox.
     *
     * @param text Text of dialog account.
     * @param img  Image of dialog account.
     */
    private DialogBox(String text, Image img, String path) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(path));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setClip(clip);
        displayPicture.setImage(img);
        dialog.setPadding(new Insets(0, 10, 0, 10));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * Returns user dialog box.
     *
     * @param text Text of dialog account.
     * @param img  Image of dialog account.
     * @return user dialog box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, "/view/UserDialogBox.fxml");
    }

    /**
     * Returns Misaki dialog box.
     *
     * @param text Text of dialog account.
     * @param img  Image of dialog account.
     * @return Misaki dialog box.
     */
    public static DialogBox getMisakiDialog(String text, Image img) {
        var db = new DialogBox(text, img, "/view/MisakiDialogBox.fxml");
        db.flip();
        return db;
    }
}
