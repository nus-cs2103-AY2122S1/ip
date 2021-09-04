package javafx;

import duke.Ui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Collections;

public class DialogBox extends HBox {

    /** RGB values for dialog box background color */
    private static final Color DUKE_COLOR = Color.rgb(57, 255, 90, 0.7);
    private static final Color USER_COLOR = Color.rgb(236, 229, 221, 0.7);

    @FXML
    private Label dialog;

    private DialogBox(String text, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        if (isUser) {
            this.setStyle(USER_COLOR);
        } else {
            this.setStyle(DUKE_COLOR);
        }
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
        return new DialogBox(text + " ", true);
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
        var db = new DialogBox(text + " ", false);
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
        var db = new DialogBox(startMessage + " ", false);
        db.flip();
        return db;
    }

    private void setStyle(Color color) {
        this.setBackground(new Background(new BackgroundFill(
                color,
                new CornerRadii(25),
                new Insets(5, 5, 5, 5)
        )));
    }
}
