package duke.gui;

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/**
 * Represents a Dialog Box in the Duke GUI.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor of the DialogBox class.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image beside the text of the dialog.
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

        this.dialog.setText(text);
        this.displayPicture.setImage(img);
        this.displayPicture.setClip(new Circle(28, 28, 28));
        this.dialog.setMinHeight(Region.USE_PREF_SIZE);
    }

    /**
     * Returns a dialog box containing the user image and text.
     *
     * @param text A string representing the user input.
     * @param img The user's avatar.
     * @return A DialogBox containing the user's text and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.dialog.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE,
                new CornerRadii(10.0), new Insets(0))));
        return db;
    }

    /**
     * Returns a dialog box containing the Duke's image and text.
     *
     * @param text A string representing the Duke's reply.
     * @param img The Duke's avatar.
     * @return A DialogBox containing the Duke's reply and image.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.dialog.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON,
                new CornerRadii(10.0), new Insets(0))));
        return db;
    }

    /**
     * Flips the dialog box such that the image is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}
