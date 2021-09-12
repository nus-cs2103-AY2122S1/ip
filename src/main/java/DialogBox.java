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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private HBox dialogContainer;
    @FXML
    private Label dialog;
    @FXML
    private Circle displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/views/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setFill(new ImagePattern(img));
        setDialogContainer("USER");

    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.setMinHeight(Region.USE_PREF_SIZE);
        setDialogContainer("DUKE");
    }

    /**
     * Obtains the dialog box for the user.
     *
     * @param text The input text to be used for the dialog box.
     * @param img The image to be used for the user image.
     * @return User Dialog Box to be rendered on the main screen.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Sets the color and radius of dialog container
     *
     * @param user The type of user for the dialog box. Limited to DUKE and USER
     */
    private void setDialogContainer(String user) {
        switch (user) {
        case "USER":
            dialogContainer.setStyle("-fx-background-radius: 1.5em 1.5em 1.5em 1.5em;"
                    + "-fx-background-color: #ffa3d0;");
            break;

        case "DUKE":
            dialogContainer.setStyle("-fx-background-radius: 1.5em 1.5em 1.5em 1.5em;"
                    + "-fx-background-color: #a3ffb7;");
            break;

        default:
            // Will not reach here
            break;
        }
    }

    /**
     * Obtains the dialog box for the duke.
     *
     * @param text The input text to be used for the dialog box.
     * @param img The image to be used for the duke image.
     * @return Duke Dialog Box to be rendered on the main screen.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
