package eightbit.gui;

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
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {

    private static final Color LIGHT_BLUE = Color.rgb(0, 255, 255);
    private static final Color LIGHT_GREEN = Color.rgb(3, 252, 152);
    private static final CornerRadii CORNER_RADII = new CornerRadii(5.0);
    private static final Insets INSETS = new Insets(-3.0);

    @FXML
    private Label dialog;
    @FXML
    private Circle displayPicture;

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
        dialog.setBackground(new Background(new BackgroundFill(LIGHT_GREEN, CORNER_RADII, INSETS)));
        displayPicture.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());

        // set dialog background colour to light blue
        Label dialog = (Label) tmp.get(0);
        dialog.setBackground(new Background(new BackgroundFill(LIGHT_BLUE, CORNER_RADII, INSETS)));

        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a dialog box containing the user's input and image.
     *
     * @param text User's input.
     * @param img  User's image.
     * @return Dialog box containing the user's input and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        assert text != null : "Text should be initialized";
        assert img != null : "Image should be initialized";
        return new DialogBox(text, img);
    }

    /**
     * Returns a dialog box containing EightBit's response and image.
     *
     * @param text EightBit's response.
     * @param img  EightBit's image.
     * @return Dialog box containing EightBit's response and image.
     */
    public static DialogBox getEightBitDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
