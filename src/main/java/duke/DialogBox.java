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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

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
    @FXML
    private Polygon triangle;

    /**
     * Constructor of Dialog box
     *
     * @param text Text inside dialogbox.
     * @param img Picture of sender of the message.
     * @param type Either duke or user.
     */
    private DialogBox(String text, Image img, String type) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (type.equals("user")) {
            triangle.getPoints().setAll(
                    10.0, 45.0,
                    10.0, 55.0,
                    17.0, 50.0
            );
        } else if (type.equals("duke")){
            triangle.getPoints().setAll(
                    10.0, 45.0,
                    10.0, 55.0,
                    3.0, 50.0
            );
        }
        triangle.setTranslateY(30);
        triangle.setLayoutY(30);
        triangle.setFill(Color.LIGHTPINK);

        displayPicture.setImage(img);
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

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, "user");
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, "duke");
        db.flip();
        return db;
    }
}
