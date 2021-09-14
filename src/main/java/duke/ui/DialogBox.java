package duke.ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * The dialog box component for chat.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView circleDisplayPicture;

    /**
     * Creates a new dialog box ui that contains the message in l and the profile picture in image.
     *
     * @param l     the message to be displayed
     * @param image the profile picture to be displayed
     */
    public DialogBox(String l, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(l + "\r"); // Adds a new line to resolve truncation issues with javafx.

        dialog.setWrapText(true);
        circleDisplayPicture.setImage(image);
        resizeAndClip(circleDisplayPicture);
    }

    private static void resizeAndClip(ImageView iv) {
        iv.setPreserveRatio(true);
        double aspectRatio = iv.getImage().getWidth() / iv.getImage().getHeight();
        double tempWidthOrHeight = Math.min(iv.getImage().getWidth(), iv.getImage().getHeight());
        iv.setViewport(new Rectangle2D((iv.getImage().getWidth() - tempWidthOrHeight) / 2, (
            iv.getImage().getHeight() - tempWidthOrHeight) / 2, tempWidthOrHeight, tempWidthOrHeight));
        if (aspectRatio < 1) {
            iv.setFitWidth(60);
        } else {
            iv.setFitHeight(60);
        }
        iv.setClip(new Circle(30, 30, 30));
    }

    /**
     * Gets the user dialog box with the user input and their profile picture.
     *
     * @param userInput  the user input
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
