package duke.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * DialogBox containing the userName and the dialog of the user.
 */
public class DialogBox extends HBox {
    @FXML
    private Label userName;
    @FXML
    private Label dialog;

    private DialogBox(String userNameText, String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        userName.setText(userNameText);
        dialog.setText(text);
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
     * Creates a DialogBox for the user's text.
     *
     * @param userName name of the user
     * @param text text that should be associated with the user
     * @return DialogBox representing the user's half of the conversation
     */
    public static DialogBox getUserDialog(String userName, String text) {
        DialogBox db = new DialogBox(userName, text);
        db.getStyleClass().add("userBox");
        return db;
    }

    /**
     * Creates a DialogBox for Duke's dialog under normal operation.
     *
     * @param userName name of the bot
     * @param text text that the bot replied with
     * @return DialogBox representing bot's half of the conversation
     */
    public static DialogBox getDukeDialog(String userName, String text) {
        var db = new DialogBox(userName, text);
        db.getStyleClass().add("dukeBox");
        db.flip();
        return db;
    }

    /**
     * Creates a DialogBox for Duke's dialog when there is an error.
     *
     * @param userName name of the bot
     * @param text text that the bot replied with
     * @return DialogBox representing bot's half of the conversation
     */
    public static DialogBox getDukeDialogError(String userName, String text) {
        var db = new DialogBox(userName, text);
        db.getStyleClass().add("dukeBoxError");
        db.flip();
        return db;
    }
}
