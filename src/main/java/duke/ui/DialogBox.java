package duke.ui;

import java.io.IOException;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * The dialog box component for chat.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private Circle circleDisplayPicture;

    public static DialogBox initialDialog() {
        return DialogBox.getDukeDialog("Hi, I am Duke, what is your name?",
            new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/images/gigachad.jpg"))));
    }

    /**
     * Creates a new dialog box ui that contains the message in l and the profile picture in iv.
     *
     * @param l  the message to be displayed
     * @param iv the profile picture to be displayed
     */
    public DialogBox(String l, Image iv) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(l);

        dialog.setWrapText(true);
        circleDisplayPicture.setRadius(30);
        circleDisplayPicture.setFill(new ImagePattern(iv));
    }

    public static DialogBox getUserDialog(String l, Image i) {
        return new DialogBox(l, i);
    }

    public static DialogBox getDukeDialog(String l, Image i) {
        var db = new DialogBox(l, i);
        db.flip();
        return db;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}
