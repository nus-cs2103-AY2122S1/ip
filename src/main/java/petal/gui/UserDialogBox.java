package petal.gui;

import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * This represents a user dialogue box on the GUI.
 * It consists of a Label that contains the user input and a
 * circle that is filled with the user image
 */
public class UserDialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private Circle profilePicture;
    private final Image userPicture = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/lauren_tsai.png")));

    /**
     * Constructs a UserDialogBox instance
     *
     * @param text The input
     */
    private UserDialogBox(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/UserDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);

        PictureEditor.setGlow(profilePicture, PictureEditor.COLOR_TEAL, 60);
        PictureEditor.setOutline(profilePicture, PictureEditor.COLOR_TEAL);

        profilePicture.setFill(new ImagePattern(userPicture));
    }

    /**
     * Returns a UserDialogBox instance with the input
     *
     * @param text The input
     * @return The UserDialogBox instance with the input
     */
    public static UserDialogBox getUserDialog(String text) {
        return new UserDialogBox(text);
    }

}
