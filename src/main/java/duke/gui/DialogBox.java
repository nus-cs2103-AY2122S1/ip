package duke.gui;

import java.io.IOException;

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
import javafx.scene.paint.Color;


/**
 * Class to encapsulate each response dialogue box in Duke's GUI
 */
public class DialogBox extends HBox {
    private static final Image dukeImage = new Image(DialogBox.class.getResourceAsStream("/images/Duke.png"));
    private static final Image userImage = new Image(DialogBox.class.getResourceAsStream("/images/User.png"));

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for creating a dialogue box to be displayed to the user.
     *
     * @param text The string that is to be displayed in the GUI to the user
     * @param img The image to be displayed with the dialogue
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

        dialog.setText(text);
        displayPicture.setImage(img);
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

    /**
     * Returns the DialogBox corresponding to Duke's respond.
     *
     * @param s The String inputted by the user
     * @return The DialogBox with the user input
     */
    public static DialogBox getUserDialog(String s) {
        return new DialogBox(s, userImage);
    }

    /**
     * Return the DialogBox corresponding to Duke's respond. Duke's DialogBox is flipped
     * that of the user's DialogBox.
     *
     * @param s The String returned from Duke that is to be displayed to the user
     * @return The DialogBox with Duke's respond
     */
    public static DialogBox getDukeDialog(String s) {
        DialogBox db = new DialogBox(s, dukeImage);
        db.flip();
        return db;
    }

    /**
     * Returns the DialogBox corresponding to Duke's respond. Duke's DialogBox is flipped
     * that of the user's DialogBox.
     *
     * @param s The String returned from Duke that is to be displayed to the user
     * @return The DialogBox with Duke's respond
     */
    public static DialogBox getErrorDialog(String s) {

        DialogBox db = new DialogBox(s, dukeImage);
        db.flip();
        db.dialog.setTextFill(Color.RED);
        return db;
    }
}
