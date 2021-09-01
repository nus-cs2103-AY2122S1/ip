package duke.gui;


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
import javafx.scene.shape.Rectangle;


import java.io.IOException;
import java.util.Collections;


public class DialogBox extends HBox {
    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image image, boolean isLeftAlign) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // circle crop
        dialog.setText(text);
        if (isLeftAlign) {
            dialog.setAlignment(Pos.TOP_LEFT);
        } else {
            dialog.setAlignment(Pos.TOP_RIGHT);
        }

        Rectangle rectangle = new Rectangle(displayPicture.getFitWidth(), displayPicture.getFitHeight());
        rectangle.setArcHeight(displayPicture.getFitHeight());
        rectangle.setArcWidth(displayPicture.getFitWidth());
        displayPicture.setClip(rectangle);
        displayPicture.setImage(image);

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
        return new DialogBox(text, img, false);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, true);
        db.flip();
        return db;
    }

}
