package duke.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;


/**
 * This is a UserDialogBox that displays user's and Duke's Avatar and the contents.
 */
public class UserDialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * This is a private constructor of a UserDialogBox.
     *
     * @param text  A String representing text in Label
     * @param img  An Image instance of the Avatar.
     */
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
        Circle circle = new Circle(displayPicture.getFitWidth() / 2);
        circle.setCenterX(displayPicture.getFitHeight() / 2);
        circle.setCenterY(displayPicture.getFitHeight() / 2);
        displayPicture.setClip(circle);
    }

    /**
     * Displays the User's dialog box and user's avatar.
     *
     * @param text  A String representing text in Label
     * @param img  An Image instance of the Avatar.
     * @return    Returns a User's dialog box.
     */
    public static UserDialogBox getUserDialog(String text, Image img) {
        return new UserDialogBox(text, img);
    }


}
