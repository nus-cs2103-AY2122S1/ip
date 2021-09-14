package duke.components;

import java.io.IOException;

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
import javafx.scene.shape.Circle;

/**
 * This control represents a dialog box. It comprises an ImageView to represent the speaker's face
 * and a Label containing the speaker's text.
 */
public class DialogBox extends HBox {

    /** Class level constant which represents the path of the .fxml file. */
    public static final String FXML_STRING_PATH = "/view/DialogBox.fxml";

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            setUpFxmlLoader();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setDialogTextAndImage(text, img);
    }

    private void setDialogTextAndImage(String text, Image img) {
        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(40, 40, 40));
    }

    private void setUpFxmlLoader() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(FXML_STRING_PATH));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        fxmlLoader.load();
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

    /**
     * Creates the user's dialog box.
     *
     * @param text The string representing the dialog.
     * @param img The image of the person.
     * @return the dialog box which contains the text and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setStyle("-fx-background-color: #adb2e6;");
        return db;
    }

    /**
     * Creates the duke's dialog box.
     *
     * @param text The string representing the dialog.
     * @param img The image of duke.
     * @return the dialog box which contains the text and image.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.setStyle("-fx-background-color: #b6ffbd;");
        return db;
    }
}
