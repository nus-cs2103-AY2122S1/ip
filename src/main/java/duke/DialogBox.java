package duke;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * A class encapsulating a DialogBox, which contains text and an Image representing the user.
 */
public class DialogBox extends HBox {

    /**
     * A Label containing the message by/for the user.
     */
    @FXML
    private Label dialog;

    /**
     * An ImageView containing an Image of either the user or Duke.
     */
    @FXML
    private ImageView displayPicture;

    /**
     * A constructor for a DialogBox.
     *
     * @param text The message to be contained in the DialogBox.
     * @param img The image to be used in the DialogBox.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Change font and font colour of text
        Color c = Color.web("#475161");
        dialog.setText(text);
        dialog.setTextFill(c);
        dialog.setFont(new Font("Helvetica", 16));

        // Make display picture into a circle
        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(47, 45, 45));
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
     * Gets a DialogBox containing the user's interaction with Duke.
     *
     * @param text The text entered by the user.
     * @param img An image representing the user.
     * @return A DialogBox representing a message from the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        Color c = Color.web("#D6EADF");
        BackgroundFill backgroundFill = new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY);
        db.setBackground(new Background(backgroundFill));
        return db;
    }

    /**
     * Gets a DialogBox containing text by Duke in response to a user's command.
     *
     * @param text The text to be sent to the user.
     * @param img  An image representing Duke.
     * @return A DialogBox representing a message from Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        Color c = Color.web("#B8E0D2");
        BackgroundFill backgroundFill = new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY);
        db.setBackground(new Background(backgroundFill));
        return db;
    }
}
