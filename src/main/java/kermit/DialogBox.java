package kermit;

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

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final int FONT_SIZE = 16;
    private static final String LIGHT_GREEN_HEX_VALUE = "#42cb5f";
    private static final String GREY_HEX_VALUE = "#e9e9eB";

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

        dialog.setText(text);
        displayPicture.setImage(img);
    }


    /*
     * Border radius adapted from
     * José Pereda
     * https://stackoverflow.com/questions/43557722/javafx-border-radius-background-color
     */
    // Code duplication for 2 DialogBoxes due to setStyle overriding previously set styles.
    private DialogBox(String text, Image img, String backgroundColor) {
        this(text, img);
        dialog.setStyle("-fx-font-family: Times New Roman;"
                + "-fx-font-size:" + FONT_SIZE + ";"
                + "-fx-background-color: " + backgroundColor + ";"
                + "-fx-padding: 5 5 5 10;"
                + "-fx-background-radius: 10px;");
    }

    /*
     * Border radius adapted from
     * José Pereda
     * https://stackoverflow.com/questions/43557722/javafx-border-radius-background-color
     */
    private DialogBox(String text, Image img, String backgroundColor, String fontColor) {
        this(text, img);
        dialog.setStyle("-fx-font-family: Times New Roman;"
                + "-fx-font-size:" + FONT_SIZE + ";"
                + "-fx-background-color: " + backgroundColor + ";"
                + "-fx-text-fill: " + fontColor + ";"
                + "-fx-padding: 5 5 5 10;"
                + "-fx-background-radius: 10px;");
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
     * Creates dialogBox for user.
     *
     * @param text user input.
     * @param img User image.
     * @return DialogBox containing user image and text.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, GREY_HEX_VALUE);
    }

    /**
     * Creates dialogBox for Kermit.
     *
     * @param text Kermit's response
     * @param img Kermit's image.
     * @return DialogBox containing Kermit's image and response.
     */
    public static DialogBox getKermitDialog(String text, Image img) {
        var db = new DialogBox(text, img, LIGHT_GREEN_HEX_VALUE, "white");
        db.flip();
        return db;
    }
}
