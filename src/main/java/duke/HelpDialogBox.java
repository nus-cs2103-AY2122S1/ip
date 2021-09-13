package duke;

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
 * Class that controls the view of the user's text and image
 */
public class HelpDialogBox extends HBox {
    @FXML
    private Label dialog;

    /**
     * Constructor for DialogBox
     * @param text String of text to be displayed
     */
    private HelpDialogBox(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/HelpDialogBox.fxml"));
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
     * Gets a DialogBox to display duke's response
     *
     * @param text String to display duke response
     * @return A DialogBox that displays duke's response
     */
    public static HelpDialogBox getDukeHelpDialog(String text) {
        var db = new HelpDialogBox(text);
        db.flip();
        return db;
    }
}
