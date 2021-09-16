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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

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

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setTextProperties();

        dialog.setText(text);
        displayPicture.setImage(img);
        dialog.setMinHeight(Region.USE_PREF_SIZE);
    }

    /**
     * Creates a new dialog box using the string of text and image given.
     *
     * @param text The string to be displayed.
     * @param img The image to be shown.
     * @return A dialog box containing both items.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a new dialog box for the program instead of the user.
     *
     * @param text The string to be displayed.
     * @param img The image of duke.Duke.
     * @return A dialog box containing both items.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Sets the text properties suc as paddings, text alignments, border and background.
     */
    private void setTextProperties() {
        dialog.setPadding(new Insets(5, 20, 5, 20));
        dialog.setAlignment(Pos.CENTER);
        dialog.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(10.0), BorderWidths.DEFAULT)));
        dialog.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,
                new CornerRadii(10.0), new Insets(0))));

    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,
                new CornerRadii(10.0), new Insets(0))));
    }
}
