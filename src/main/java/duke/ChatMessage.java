package duke;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;

/**
 * class ChatMessage: represents a chat message in chat area
 */
public class ChatMessage extends BorderPane {

    private Label message;

    public ChatMessage(String msg, boolean sender) {

        Image image1 = new Image("file:./data/1.png", 50, 50, false, false);
        Image image2 = new Image("file:./data/2.png", 50, 50, false, false);

        this.setMaxWidth(430);
        this.setPadding(new Insets(15));
        this.setStyle("-fx-border-color: black;");

        message = new Label(msg);
        message.setWrapText(true);
        message.setMaxWidth(350);

        this.setCenter(message);

        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);

        if (sender) {
            imageView.setImage(image1);
            this.setRight(imageView);
            message.setAlignment(Pos.BASELINE_LEFT);
            message.setTextAlignment(TextAlignment.LEFT);
            this.setStyle("-fx-border-color: green; -fx-border-radius: 10px; -fx-background-color: linear-gradient(to bottom right, #ccffff 0%, #99ccff 100%);");
        } else {
            imageView.setImage(image2);
            this.setLeft(imageView);
            message.setAlignment(Pos.BASELINE_RIGHT);
            message.setTextAlignment(TextAlignment.LEFT);
            this.setStyle("-fx-border-color: gray; -fx-border-radius: 10px; -fx-background-color: snow;");
        }
    }
}
