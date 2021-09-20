package jarvis.controller;

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
 * Encapsulates the Dialog box that is shown in the GUI.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private HBox speechBubble;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/views/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setMinHeight(Label.USE_PREF_SIZE);
        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(
                new Circle(
                        displayPicture.getFitWidth() / 2,
                        displayPicture.getFitHeight() / 2,
                        displayPicture.getFitWidth() / 2
                )
        );
    }

    /**
     * Gets the user dialog box.
     *
     * @param text Text that is inside the dialog box.
     * @param img The image beside the dialog box.
     * @return The user dialog box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.speechBubble.getStyleClass().add("user-speech-bubble");
        return dialogBox;
    }

    /**
     * Gets the jarvis dialog box.
     *
     * @param text Text that is inside the dialog box.
     * @param img The image beside the dialog box.
     * @return The jarvis dialog box.
     */
    public static DialogBox getJarvisDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
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
