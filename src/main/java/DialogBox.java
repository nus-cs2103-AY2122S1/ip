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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

/**
 * Represents a window controller for dialog box using FXML.
 * Consists of an ImageView to represent the speaker's face and
 * a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    @FXML
    private Polygon triangle;

    /**
     * Constructs DialogBox object.
     *
     * @param text speaker's message.
     * @param img speaker's image.
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
        dialog.setWrapText(true);

        Circle clip = new Circle(50, 50, 48);
        displayPicture.setImage(img);
        displayPicture.setClip(clip);
        triangle.setFill(Color.GREY);
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
     * Gets DialogBox object for User speaker.
     *
     * @param text speaker's message.
     * @param img speaker's image.
     * @return new dialog object.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setUserStyle();
        return db;
    }

    /**
     * Gets DialogBox object for Duke speaker.
     * Flips speaker's image horizontally.
     *
     * @param text speaker's message.
     * @param img speaker's image.
     * @return new dialog object.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setBotStyle();
        db.flip();
        return db;
    }

    public void setUserStyle() {
        triangle.getPoints().setAll(
                10.0, 45.0,
                10.0, 55.0,
                22.0, 49.0
        );
        triangle.setTranslateY(10);
        triangle.setLayoutY(30);
    }

    public void setBotStyle() {
        triangle.getPoints().setAll(
                22.0, 45.0,
                22.0, 55.0,
                10.0, 49.0
        );
        triangle.setTranslateY(10);
        triangle.setLayoutY(120);
    }
}
