package kayu.ui;

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

/**
 * Represents a dialog box consisting of an ImageView to represent the user's avatar and a label
 * containing text from Kayu.
 */
public class DialogBox extends HBox {

    // Resource path (starting from /src/main/java/resources).
    private static final String FXML_PATH = "/view/DialogBox.fxml";

    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    /**
     * Initializes a DialogBox based on the parameters. To act as a helper method.
     *
     * @param text Input text.
     * @param img Source's image.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(FXML_PATH));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            dialog.setText(text);
            displayPicture.setImage(img);

        } catch (IOException e) {
            e.printStackTrace();
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
     * Creates the respective user DialogBox for render.
     *
     * @param text User's input text.
     * @param img User image.
     * @return A DialogBox representing the user's input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates the respective Kayu DialogBox for render.
     *
     * @param text Kayu's response text.
     * @param img Kayu's image.
     * @return A DialogBox representing Kayu's response text.
     */
    public static DialogBox getKayuDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
