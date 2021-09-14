import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Text dialog;
    @FXML
    private TextFlow textFlow;
    @FXML
    private ImageView displayPicture;
    @FXML
    private Button copyButton;

    /**
     * Constructs a dialog box.
     *
     * @param text Text to show in dialog box.
     * @param img  Image to show in dialog box.
     */
    public DialogBox(String text, Image img, Color color) {
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
        displayPicture.setClip(new Circle(40, 40, 40));
        textFlow.setBackground(new Background(new BackgroundFill(color, new CornerRadii(15), null)));

        hideActions();
    }

    /**
     * Returns a user dialog box.
     *
     * @param text Text to show in user dialog.
     * @param img  Image for user dialog.
     * @return A user dialog box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, Color.POWDERBLUE);
    }

    /**
     * Returns a duke dialog box.
     *
     * @param text Text to show in duke dialog box.
     * @param img  Image for duke dialog.
     * @return A duke dialog box.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, Color.PEACHPUFF);
        db.flip();
        return db;
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
     * Shows actions for this dialog box.
     */
    @FXML
    private void showActions() {
        copyButton.setVisible(true);
    }

    /**
     * Hides actions for this dialog box.
     */
    @FXML
    private void hideActions() {
        copyButton.setVisible(false);
    }

    /**
     * Copies dialog text to clipboard.
     */
    @FXML
    private void onClckCopy() {
        System.out.println("click copy");
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent textContent = new ClipboardContent();
        textContent.putString(this.dialog.getText());
        clipboard.setContent(textContent);
    }
}
