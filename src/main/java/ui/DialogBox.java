package ui;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

//@@author wanyu-l-reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
// with minor modifications
public final class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle displayPicture;
    @FXML
    private Label text;

    private final double userFontSize = 30.0;

    /**
     * Constructs a ui.DialogBox object.
     *
     * @param text the input by user or output by user wrapped by Label
     */
    private DialogBox(String text, Image img, Color imageBorder, Color backgroundColor, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load(getClass().getResourceAsStream("/view/DialogBox.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        if (isUser) {
            dialog.setFont(Font.font(userFontSize));
        }
        displayPicture.setFill(new ImagePattern(img));
        displayPicture.setStroke(imageBorder);
        this.setBackground(new Background(new BackgroundFill(backgroundColor, new CornerRadii(20.0),
                new Insets(5.0))));
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Gets a dialog box representing the user in the conversation.
     * @param text message to be shown as user's query
     * @return a dialog box representing the user
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text + "    ", img, Color.BISQUE, Color.LIGHTBLUE, true);
    }

    /**
     * Gets a dialog box representing the Duke in the conversation.
     * @param text message to be shown as Duke's answer
     * @return a dialog box representing Duke
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, Color.LIGHTCORAL, Color.DARKSEAGREEN, false);
        db.flip();
        return db;
    }
}
//@@author
