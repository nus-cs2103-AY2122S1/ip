package hyddd.uimanager;

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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * @@author Hang Zelin
 *
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final String EXCEPTION_IDENTIFIER = "OOPS";
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

        setDialogText(text);
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
     * Sets text in a Dialog box, with specific style.
     *
     * @param text String value of hyddd's response
     */
    private void setDialogText(String text) {
        dialog.setText(text);
        if (hasExceptionResponse(text)) {
            dialog.setTextFill(Color.rgb(236, 70, 70));
        }
    }

    /**
     * Returns a boolean value showing whether the input is an exception response.
     *
     * @param input String value used to detect exception response.
     * @return boolean value indicating if input is exception response.
     */
    private boolean hasExceptionResponse(String input) {
        return input.contains(EXCEPTION_IDENTIFIER);
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
        dialogBox.setStyle("-fx-border-color:#FFADAD; -fx-border-style:solid; -fx-border-width:1em 0;"
                + "-fx-translate-x:10px;");
        dialogBox.dialog.setStyle("-fx-border-color:#FFADAD; -fx-border-style:solid; -fx-border-width:3;"
                + "-fx-border-radius:10px; -fx-background-color:#FFADAD; -fx-background-radius: 12px;");
        return dialogBox;
    }

    /**
     * Returns a DialogBox for hyddd.
     *
     * @param text Text info in hyddd dialog box.
     * @param img  hyddd's image.
     * @return DialogBox for hyddd.
     */
    public static DialogBox getHydddDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.flip();
        dialogBox.setStyle("-fx-border-color:#3DB2FF; -fx-border-style:solid; -fx-border-width:1em 0;"
                + "-fx-translate-x:10px;");
        dialogBox.dialog.setStyle("-fx-border-color:#3DB2FF; -fx-border-style:solid; -fx-border-width:3;"
                + "-fx-border-radius:10px; -fx-background-color:#3DB2FF; -fx-background-radius:12px;"
                + "-fx-translate-x:10px");
        return dialogBox;
    }
}
