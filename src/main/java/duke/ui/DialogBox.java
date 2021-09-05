package duke.ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * The dialog box component for chat.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private Circle circleDisplayPicture;

    /**
     * Creates a new dialog box ui that contains the message in l and the profile picture in iv.
     *
     * @param l  the message to be displayed
     * @param iv the profile picture to be displayed
     */
    public DialogBox(String l, Image iv) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(l);

        dialog.setWrapText(true);
        circleDisplayPicture.setRadius(30);
        circleDisplayPicture.setFill(new ImagePattern(iv));
    }

    /**
     * Gets the user dialog box with the user input and their profile picture.
     *
     * @param userInput the user input
     * @param profilePic the profile picture
     * @return the user dialog box
     */
    public static DialogBox getUserDialog(String userInput, Image profilePic) {
        return new DialogBox(userInput, profilePic);
    }

    /**
     * Gets the duke dialog box with the output and duke's profile picture. This is flipped from the user dialog box.
     *
     * @param dukeOutput the output message by duke
     * @param dukePic    the profile picture
     * @return the duke dialog box
     */
    public static DialogBox getDukeDialog(String dukeOutput, Image dukePic) {
        var db = new DialogBox(dukeOutput, dukePic);
        db.flip();
        return db;
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
}
