package javaFx;

import duke.Ui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Collections;

public class DialogBox extends HBox {

    @FXML
    private Label dialog;

    private DialogBox(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
     * Displays the user input on the GUI.
     *
     * @param text The user input.
     * @return The DialogBox containing the user input.
     */
    public static DialogBox getUserDialog(String text) {
        var db = new DialogBox("You:\n" + text);
        db.flip();
        return db;
    }

    /**
     * Displays Duke's response onto the GUI.
     *
     * @param text Duke's response to an input by the user.
     * @return The DialogBox containing the user input.
     */
    public static DialogBox getDukeDialog(String text) {
        // Remove blank lines at the end of the list.
        text = text.replaceAll("([\\n\\r]+\\s*)*$", "");
        var db = new DialogBox("Duke:\n" + text + " ");
        db.flip();
        return db;
    }

    /**
     * Displays the welcome message to the user on the GUI.
     *
     * @return The dialog box containing the startup message.
     */
    public static DialogBox getStartDialog() {
        String startMessage = Ui.getStartMessage();
        return new DialogBox(startMessage + " ");
    }
}
