package duke.uimanager;

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

import java.io.IOException;
import java.util.Collections;

/**
 * @@author Hang Zelin
 *
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

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
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a DialogBox for user.
     *
     * @param text Text info in user dialog box.
     * @param img User's image.
     * @return DialogBox for user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.setStyle("-fx-border-color:#FFADAD; -fx-border-style:solid; -fx-border-radius:12px;" +
                "-fx-border-style: solid; -fx-border-width: 1em");
        dialogBox.dialog.setStyle("-fx-border-color:#FFADAD; -fx-border-style:solid; -fx-border-width:3;" +
                "-fx-border-radius:10px; -fx-background-color: #FFADAD; -fx-background-radius: 12px;");
        return dialogBox;
    }

    /**
     * Returns a DialogBox for Duke.
     *
     * @param text Text info in Duke dialog box.
     * @param img  Duke's image.
     * @return DialogBox for Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.setStyle("-fx-border-color: #3DB2FF; -fx-border-style:solid; -fx-border-radius:12px;" +
                "-fx-border-style: solid; -fx-border-width: 1em;");
        dialogBox.dialog.setStyle("-fx-border-color: #3DB2FF; -fx-border-style:solid; -fx-border-width:3;" +
                "-fx-border-radius:10px; -fx-background-color:  #3DB2FF; -fx-background-radius: 12px;");
        dialogBox.flip();
        return dialogBox;
    }
}