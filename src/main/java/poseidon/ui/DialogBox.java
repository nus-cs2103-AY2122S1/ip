package poseidon.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Represents a controller for {@code DialogBox} used by JavaFX to create GUI, consisting of
 * a {@code Circle} containing an image to represent the speaker's face and
 * a {@code Text} box containing text from the speaker.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class DialogBox extends VBox {

    // Constants to be used for creation of a User's DialogBox.
    private static final Font USER_FONT = Font.loadFont(DialogBox.class.getResource("/fonts/JetBrainsMono-Italic.ttf")
                    .toExternalForm(),
            14);
    private static final String USER_BOX_STYLE = "-fx-background-color: #A3C4BC; -fx-background-radius: 20;";
    private static final String USER_ICON_STYLE =
            "-fx-border-color: #A3C4BC; -fx-border-width: 5; -fx-border-radius: 50;";

    // Constants to be used for creation of the Bot's DialogBox.
    private static final Font BOT_FONT = Font.loadFont(DialogBox.class.getResource("/fonts/JetBrainsMono-Regular.ttf")
                    .toExternalForm(),
            14);
    private static final String BOT_BOX_STYLE = "-fx-background-color: #E7EFC5; -fx-background-radius: 20;";
    private static final String BOT_ICON_STYLE =
            "-fx-border-color: #E7EFC5; -fx-border-width: 5; -fx-border-radius: 50;";

    // Background and content box of the text box.
    @FXML
    private HBox textBackgroundBox;
    @FXML
    private Text dialog;

    // Background and content container of the image icon.
    @FXML
    private Pane imagePane;
    @FXML
    private Circle imageCircle;

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
        imageCircle.setFill(new ImagePattern(img));
    }

    /**
     * Modifies the {@code DialogBox} to set the style and font intended for User dialog boxes.
     */
    private void modifyForUser() {
        imagePane.setStyle(USER_ICON_STYLE);
        textBackgroundBox.setStyle(USER_BOX_STYLE);
        dialog.setFont(USER_FONT);
    }

    /**
     * Modifies the {@code DialogBox} to set the style and font intended for Bot dialog boxes
     * and flips the contents to the left.
     */
    private void modifyForBot() {
        imagePane.setStyle(BOT_ICON_STYLE);
        imagePane.setTranslateX(0);
        textBackgroundBox.setStyle(BOT_BOX_STYLE);
        textBackgroundBox.setTranslateX(0);
        dialog.setFont(BOT_FONT);
    }

    /**
     * Returns a {@code DialogBox} representing a User dialog (i.e.) image on right side,
     * user background color and user font.
     *
     * @param text Text to be shown in the {@code DialogBox}.
     * @param img {@code Image} to be used as profile image.
     * @return {@code DialogBox} with Text and profile image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.modifyForUser();
        return db;
    }

    /**
     * Returns a {@code DialogBox} representing a Bot dialog
     * (i.e.) image on left side, bot background color and bot font.
     *
     * @param text Text to be shown in the {@code DialogBox}.
     * @param img {@code Image} to be used as profile image.
     * @return {@code DialogBox} with Text and profile image.
     */
    public static DialogBox getBotDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.modifyForBot();
        return db;
    }
}
