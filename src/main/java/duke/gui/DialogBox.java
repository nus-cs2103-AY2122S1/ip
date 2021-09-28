package duke.gui;

import java.io.IOException;
import java.util.Collections;

import duke.main.DukeException;
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

/**
 * An example of a custom control using FXML.
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
            dialog.setMinHeight(Label.USE_PREF_SIZE);
            displayPicture.setImage(img);
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
        displayPicture.setFitHeight(100);
        displayPicture.setFitWidth(100);
        setAlignment(Pos.TOP_LEFT);
    }

    private void formatToExceptionFormat() {
        displayPicture.setImage(new Image("/images/AngryDuke.png"));
        dialog.setStyle("-fx-text-fill: #FFFFFFFF; -fx-font-weight:bold;");
        setStyle("-fx-background-color: #FF0101FF;");
    }
    private void modifyUserDialogBox() {
        dialog.setStyle("-fx-background-color: #001935; -fx-text-fill: #e6fbff; -fx-label-padding:5;"
            + " -fx-border-radius: 5; -fx-background-radius: 10;");
        setStyle("-fx-background-color: #09FFD0C9; -fx-background-radius: 10; -fx-insets: 2");
    }
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.modifyUserDialogBox();
        return db;
    }
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        if (text.contains(DukeException.getAngryEmoji()) || text.contains(DukeException.getSadEmoji())) {
            db.formatToExceptionFormat();
        }
        db.flip();
        return db;
    }
}
