package mango;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the user's face and a label
 * containing text from the user.
 */
public class UserDialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private UserDialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/UserDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Returns a UserDialogBox with the user's icon and dialog.
     * @param text user's response.
     * @param img user's icon picture.
     * @return A UserDialogBox with the user's icon and dialog.
     */
    public static UserDialogBox getUserDialog(String text, Image img) {
        return new UserDialogBox(text, img);
    }
}