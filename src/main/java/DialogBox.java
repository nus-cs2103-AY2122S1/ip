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
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an circular cropped ImageView to represent the speaker's face and
 * a label containing text from the speaker ie the inputs.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private final int Image_Radius = 40;
    private final String DIALOG_BOX_FXML_FILE = "/view/DialogBox.fxml";

    /**
     * Constuctor to create new Dialog Boxes
     * @param text Text is the String value that needs to be displayed in the dialog box
     * @param img It is the image of the speaker that needs to be displayed
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(DIALOG_BOX_FXML_FILE));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(this.cropImage());
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
     * Method to get the Dialog Box for the given text and image
     * @param text The String to be displayed in the Dialog Box
     * @param img The image of the User shown for the Dialog box
     *
     * @return A new Dialog Box with the given inputs
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Method to get the Dialog box for the Duke output
     * @param text The Sting message to be displayed in the Dialog Box
     * @param img The image of the Duke
     *
     * @return A new Dialog Box with the given inputs
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Crops image to a circle shape.
     *
     * @return Circle object.
     */
    private Circle cropImage() {
        Circle circle = new Circle(Image_Radius);
        circle.setStrokeWidth(5);
        circle.setCenterX(displayPicture.getFitWidth() / 2);
        circle.setCenterY(displayPicture.getFitHeight() / 2);
        return circle;
    }
}
