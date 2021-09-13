package workdone.ui;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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

    /**
     * Constructor of the class `DialogBox`.
     *
     * @param text Text to be displayed.
     * @param img Avatar of WorkDone or the user.
     */
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
        Circle clip = new Circle(40, 40, 40);
        displayPicture.setClip(clip);
    }

    private DialogBox(String text, Image img, Color color, Background background) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        if (color != null) {
            dialog.setTextFill(color);
        }
        dialog.setBackground(background);
        displayPicture.setImage(img);
        Circle clip = new Circle(40, 40, 40);
        displayPicture.setClip(clip);
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
     * Creates and returns a user dialog.
     *
     * @param text Command received.
     * @param img Avatar of the user.
     * @return DialogBox of the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates and returns a WorkDone dialog.
     *
     * @param text Response generated from a command.
     * @param img Avatar of WorkDone.
     * @return DialogBox of WorkDone.
     */
    public static DialogBox getWorkDoneDialog(String text, Image img) {
        Color backgroundColor = new Color(0.125490196, 0.870588235, 0.82745098, 0.1);
        Background background = new Background(new BackgroundFill(
                backgroundColor, new CornerRadii(10), new Insets(0, 10, 0, 10)));
        var db = new DialogBox(text, img, null, background);
        db.flip();
        return db;
    }

    /**
     * Creates and returns a dialog with error message.
     *
     * @param text Error message generated from a command.
     * @param img Avatar of WorkDone.
     * @return DialogBox showing Error.
     */
    public static DialogBox getErrorDialog(String text, Image img) {
        Color textColor = new Color(0.74117647, 0.309803921, 0.423529411, 1);
        Color backgroundColor = new Color(1, 0.572549019, 0.545098039, 0.2);
        Background background = new Background(new BackgroundFill(
                backgroundColor, new CornerRadii(10), new Insets(0, 10, 0, 10)));
        var db = new DialogBox(text, img, textColor, background);
        db.flip();
        return db;
    }
}
