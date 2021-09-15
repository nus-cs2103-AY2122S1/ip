import java.io.IOException;
import java.util.Collections;

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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * An example of a custom control using FXML. This control represents a dialog
 * box consisting of an ImageView to represent the speaker's face and a label
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

        Rectangle clip = new Rectangle(displayPicture.getFitWidth(), displayPicture.getFitHeight());
        clip.setArcWidth(100);
        clip.setArcHeight(100);
        displayPicture.setClip(clip);

        dialog.setText(text);
        dialog.setWrapText(true);
        dialog.setTextFill(Color.WHITE);
        dialog.setStyle("-fx-background-color: #1f304e;");
        dialog.setPadding(new Insets(10));

        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the
     * right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * A component of the user dialog, which displays user side of the conversation
     * {@code Stage}.
     * 
     * @param text is the text message which is written by the user.
     * @param img  is the user display picture.
     * @return a component which displays the user side of the component, where the
     *         display picture is on the right-hand side.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * A component of the user dialog, which displays {@code Duke} side of the
     * conversation {@code Stage}.
     * 
     * @param text is the text message which is replied by {@code Duke}.
     * @param img  is {@code Duke} display picture.
     * @return a component which displays the {@code Duke} side of the component,
     *         where the display picture is on the left-hand side.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
