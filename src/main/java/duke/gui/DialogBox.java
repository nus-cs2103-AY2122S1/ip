package duke.gui;

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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.IOException;




/**
 * The dialogBox containing the dialog and photo of user of Duke. A customized HBox.
 */
public class DialogBox extends HBox {
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

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
        dialog.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        displayPicture.setImage(img);
        this.setHeight(dialog.getHeight());
    }

    /**
     * Returns the dialogBox containing user's input message and image.
     *
     * @param text User's input message.
     * @param img User's photo.
     * @return The dialogBox containing user's input message and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(0), Insets.EMPTY)));
        return dialogBox;
    }

    /**
     * Returns the dialogBox containing Duke's response message and image.
     *
     * @param text Duke's response.
     * @param img Duke's photo.
     * @return The dialogBox containing Duke's response and image.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.flip();
        dialogBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(0), Insets.EMPTY)));
        return dialogBox;
    }
}