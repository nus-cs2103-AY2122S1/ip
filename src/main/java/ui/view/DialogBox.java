package ui.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Collections;
import java.util.logging.Logger;

// @author Jeffry Lum
// Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
// with minor modifications

// @author xyliew
// Reused from https://github.com/xyliew25/ip/blob/master/src/main/resources/view/DialogBox.fxml
// with major modification
// Thanks xyliew for his inspiring way to get the chat bubble and removing the box entirely

/**
 * DialogBox view inside the MainWindow.
 */
public class DialogBox extends HBox {
    private final Logger logger = Logger.getLogger(DialogBox.class.getName());
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    
    private DialogBox(String text, Image img) {
        assert text != null && img != null;
    
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
    
        dialog.setText(text);
        displayPicture.setImage(img);
    }
    
    /**
     * Gets DialogBox for user.
     *
     * @param text String to be displayed.
     * @param img user profile pic.
     * @return DialogBox.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(-5);
        dropShadow.setOffsetY(10);
        dropShadow.setColor(Color.GRAY);
        
        DialogBox db = new DialogBox(text, img);
        db.setEffect(dropShadow);
        return db;
    }
    
    /**
     * Gets DialogBox for user.
     *
     * @param text String to be displayed.
     * @param img user profile pic.
     * @return DialogBox.
     */
    public static DialogBox getUserDialogError(String text, Image img) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(-5);
        dropShadow.setOffsetY(10);
        dropShadow.setColor(Color.GRAY);
        
        DialogBox db = new DialogBox(text, img);
        db.dialog.setStyle("-fx-background-color: red;");
        db.setEffect(dropShadow);
        return db;
    }
    
    /**
     * Gets DialogBox for the Bot.
     *
     * @param text String to be displayed.
     * @param img bot profile pic.
     * @return DialogBox.
     */
    public static DialogBox getBotDialog(String text, Image img) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(10);
        dropShadow.setColor(Color.GRAY);
        
        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.setEffect(dropShadow);
        return db;
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
}
