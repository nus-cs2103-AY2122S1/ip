package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.shape.Circle;
import javafx.scene.text.TextFlow;


enum DialogBoxType {
    USER, BOT, ERROR
}

/**
 * Class that represent a dialog made in the GUI.
 *
 * @author Aiken Wong
 */
public class DialogBox extends HBox {

    private Image userPhoto = new Image(this.getClass().getResourceAsStream("/images/UserDisplayPhoto.jpg"));
    private Image botPhoto = new Image(this.getClass().getResourceAsStream("/images/BotDisplayPhoto.jpg"));
    private Image errorPhoto = new Image(this.getClass().getResourceAsStream("/images/BotErrorDisplayPhoto"
        + ".jpg"));
    private Label textLabel;
    private TextFlow textFlow = new TextFlow();
    private ImageView displayPicture;

    /**
     * Instantiates a DialogBox object.
     *
     * @param text The text to be added into the DialogBox.
     */
    public DialogBox(String text) {
        textLabel = new Label(text);
        textLabel.setPadding(new Insets(20, 10, 0, 0));
        textLabel.setStyle("-fx-text-fill: white;");
        textLabel.setWrapText(true);
        textLabel.setMaxWidth(250);
        textFlow.getChildren().add(textLabel);
        this.setPadding(new Insets(0, 0, 30, 0));
        this.setAlignment(Pos.TOP_RIGHT);
        this.setSpacing(10);
    }


    private static DialogBox generateErrorDialogBox(String text) {
        DialogBox errorDialogBox = new DialogBox(text);
        errorDialogBox.displayPicture = new ImageView(errorDialogBox.errorPhoto);
        errorDialogBox.displayPicture.setFitWidth(100.0);
        errorDialogBox.displayPicture.setFitHeight(100.0);
        errorDialogBox.getChildren().addAll(errorDialogBox.textFlow, errorDialogBox.displayPicture);
        errorDialogBox.displayPicture.setTranslateY(10);
        clipImageViewToCircle(errorDialogBox.displayPicture, 100);
        errorDialogBox.setDialogBoxBackgroundColor(191, 63, 63);
        errorDialogBox.flip();
        return errorDialogBox;
    }

    private static DialogBox generateUserDialogBox(String text) {
        DialogBox userDialogBox = new DialogBox(text);
        userDialogBox.displayPicture = new ImageView(userDialogBox.userPhoto);
        userDialogBox.displayPicture.setFitWidth(100.0);
        userDialogBox.displayPicture.setFitHeight(100.0);
        userDialogBox.getChildren().addAll(userDialogBox.textFlow, userDialogBox.displayPicture);
        userDialogBox.displayPicture.setTranslateY(10);
        clipImageViewToCircle(userDialogBox.displayPicture, 100);
        userDialogBox.setPadding(new Insets(0, 0, 30, 0));
        userDialogBox.setDialogBoxBackgroundColor(70, 70, 70);
        return userDialogBox;
    }

    private static DialogBox generateBotDialogBox(String text) {
        DialogBox botDialogBox = new DialogBox(text);
        botDialogBox.displayPicture = new ImageView(botDialogBox.botPhoto);
        botDialogBox.displayPicture.setFitWidth(100.0);
        botDialogBox.displayPicture.setFitHeight(100.0);
        botDialogBox.getChildren().addAll(botDialogBox.textFlow, botDialogBox.displayPicture);
        botDialogBox.displayPicture.setTranslateY(10);
        clipImageViewToCircle(botDialogBox.displayPicture, 100);
        botDialogBox.setPadding(new Insets(0, 0, 30, 0));
        botDialogBox.setDialogBoxBackgroundColor(200, 200, 200);
        botDialogBox.flip();
        return botDialogBox;
    }

    /**
     * Factory method to produce dialogue boxes given the dialogue type and input text.
     *
     * @param text Input text to be printed as dialogue.
     * @param dialogBoxType Type of the dialogue box.
     * @return
     */
    public static DialogBox of(String text, DialogBoxType dialogBoxType) {
        switch (dialogBoxType) {
        case BOT:
            return DialogBox.generateBotDialogBox(text);
        case USER:
            return DialogBox.generateUserDialogBox(text);
        case ERROR:
            return DialogBox.generateErrorDialogBox(text);
        default:
            return DialogBox.generateErrorDialogBox("Something went wrong: Not a valid DialogBoxType");
        }
    }


    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);

        this.getChildren().setAll(tmp);

    }


    private void setDialogBoxBackgroundColor(int red, int green, int blue) {
        this.setBackground(
            new Background(new BackgroundFill(Color.rgb(red, green, blue, 0.75), new CornerRadii(35),
                null)));
    }

    private static void clipImageViewToCircle(ImageView imageView, int length) {
        imageView.setPreserveRatio(false);
        imageView.setSmooth(true);
        Circle circle = new Circle(length / 2, length / 2, Math.min(length, length) / 2);
        imageView.setClip(circle);
    }

}
