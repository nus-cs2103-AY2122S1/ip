package duke.controller;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * The DialogBox class is a controller class that represents a dialog box consisting of
 * an image of the speaker's avatar and a label containing text from the speaker,
 * similar to most chat-bot user interfaces.
 */
public class DialogBox extends HBox {
    private static final Image userImage = new Image(DialogBox.class.getResourceAsStream("/images/DaUser.png"));
    private static final Image dukeImage = new Image(DialogBox.class.getResourceAsStream("/images/DaDuke.png"));

    /** Label containing text from the speaker. */
    @FXML
    private Label dialog;

    /** ImageView containing the speaker's avatar. */
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DialogBox.class.getResource("/view/DialogBox.fxml"));
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
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a dialog box for text entered by the user.
     *
     * @param text The text entered by the user.
     * @return A dialog box of text entered by the user to be displayed on the GUI.
     */
    public static DialogBox getUserDialog(String text) {
        DialogBox userDialog = new DialogBox(text, userImage);
        userDialog.dialog.setBackground(new Background(
                new BackgroundFill(Color.web("#56c5e0"), new CornerRadii(20), null)));
        return userDialog;
    }

    /**
     * Returns a dialog box for text from Duke.
     *
     * @param text The text from Duke to be displayed to the user.
     * @return A dialog box of text from Duke to be displayed on the GUI.
     */
    public static DialogBox getDukeDialog(String text) {
        DialogBox dukeDialog = new DialogBox(text, dukeImage);
        dukeDialog.flip();
        dukeDialog.dialog.setBackground(new Background(new BackgroundFill(Color.GRAY, new CornerRadii(20), null)));
        return dukeDialog;
    }
}
