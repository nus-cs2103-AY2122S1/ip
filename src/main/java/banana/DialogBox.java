package banana;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;

/**
 * Creates the text and image to
 * be displayed on the stage.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    private Image img;
    private String text;

    /**
     * Constructor for the DialogBox
     * class - loads the FXMLLoader
     * object.
     *
     * @param text the user input
     * @param img  the image to be used
     */
    private DialogBox(String text, Image img) {
        this.text = text;
        this.img = img;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(
                    "/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the label text and the image
     * display.
     *
     * @param font  the font of the text
     * @param color the color of the text
     */
    public void setDialogBox(Font font, Color color) {
        dialog.setFont(font);
        dialog.setTextFill(color);
        dialog.setText(text);
        dialog.setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);
        dialog.setStyle("-fx-background-color : #CBCACA;");
        displayPicture.setImage(img);
    }

    /**
     * Takes the user input and image
     * and returns a DialogBox object
     * which prints to the GUI program.
     *
     * @param text user input
     * @param img  the image
     * @return the DialogBox object
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setDialogBox(new Font("American Typewriter", 15),
                Color.SADDLEBROWN);
        return db;
    }

    /**
     * Takes the parsed output and image
     * and returns a DialogBox object
     * which prints to the GUI program.
     *
     * @param text parsed output
     * @param img  the image
     * @return the DialogBox object
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setDialogBox(new Font("American Typewriter", 15),
                Color.DARKBLUE);
        return db;
    }

    /**
     * Pushes the bot image
     * and text to the left of the screen,
     * and puts the image to the left
     * of the text.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }


}
