package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Collections;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    private static final int TOP_PADDING = 5;
    private static final int RIGHT_PADDING = 10;
    private static final int BOTTOM_PADDING = 5;
    private static final int LEFT_PADDING = 10;
    private static final int DUKE_BOX_AND_IMAGE_DISTANCE = 5;

    private static final String DUKE_BG_COLOR = "#444444";
    private static final int DUKE_BG_RADIUS = 20;

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
     * Sets certain properties on flipped box to distinguish it from the normally-oriented box.
     */
    private void setDukeDialogBox() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.setTranslateX(DUKE_BOX_AND_IMAGE_DISTANCE);
        dialog.setPadding(new Insets(TOP_PADDING, RIGHT_PADDING, BOTTOM_PADDING, LEFT_PADDING));
        String style = String.format("-fx-background-color: %s;"
                + " -fx-background-radius:%d", DUKE_BG_COLOR, DUKE_BG_RADIUS);
        dialog.setStyle(style);
    }

    /**
     * Sets certain properties on  box to distinguish it from the flipped box.
     */
    private void setUserDialogBox() {
        dialog.setPadding(new Insets(TOP_PADDING, RIGHT_PADDING, BOTTOM_PADDING, LEFT_PADDING));
    }

    /**
     * Creates a dialog box to represent user information.
     *
     * @param text represents input shown by user.
     * @param img represents picture of user.
     * @return dialog box containing user's message and picture.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setUserDialogBox();
        return db;
    }

    /**
     * Creates a dialog box to represent program information.
     *
     * @param text represents message shown by program.
     * @param img represents picture of program.
     * @return dialog box containing program's message and picture.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setDukeDialogBox();
        return db;
    }
}

