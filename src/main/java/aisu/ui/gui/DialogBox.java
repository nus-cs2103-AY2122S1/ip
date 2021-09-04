package aisu.ui.gui;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 *
 * @author Liaw Xin Yan
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle displayIcon;
    // Variable to determine chat box design, which depends on user.
    private final Boolean isAisu;

    private DialogBox(String text, Image img, Boolean isAisu) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.isAisu = isAisu;
        this.getChildren().add(buildContent(text, img));

    }

    /**
     * Builds the content for the dialog.
     * @param text Text to be spoken.
     * @param img Profile picture of user/Aisu.
     * @return Vertical Box of the parent dialog box.
     */
    private VBox buildContent(String text, Image img) {
        VBox container = new VBox(5);

        dialog.setText(text);
        dialog.setWrapText(true);
        displayIcon.setFill(new ImagePattern(img));
        displayIcon.setEffect(new DropShadow(+8d, 0d, 0d, Color.GRAY));


        // Add timestamp for message.
        // @@author JQChong-reused
        // Reused from: https://github.com/JQChong with minor modifications.
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Label timestampLabel = new Label(timestamp);
        timestampLabel.setFont(Font.font(10));
        timestampLabel.setTextFill(Color.gray(0.3));
        timestampLabel.setWrapText(true);

        container.getChildren().addAll(dialog, timestampLabel);

        Paint background;
        if (isAisu) {
            background = Color.LIGHTBLUE;
        } else {
            background = Color.GAINSBORO;
        }

        container.setBackground(new Background(
                new BackgroundFill(background, new CornerRadii(5), Insets.EMPTY)));
        container.setPadding(new Insets(10));

        return container;
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
     * Creates a dialogue based on user's input.
     * @param text The user's input.
     * @param img The profile photo of the user.
     * @return The DialogBox.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img, false);
        db.flip();
        return db;
    }

    /**
     * Creates a dialogue for Aisu's response.
     * @param text Aisu's response.
     * @param img The profile photo of Aisu.
     * @return The DialogBox.
     */
    public static DialogBox getAisuDialog(String text, Image img) {
        return new DialogBox(text, img, true);
    }
}
