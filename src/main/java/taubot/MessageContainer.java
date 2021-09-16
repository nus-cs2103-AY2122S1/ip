package taubot;

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
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class MessageContainer extends HBox {
    private static final Color userTextMessageColour = Color.rgb(44, 130, 201, 1);
    private static final Color dukeTextMessageColour = Color.rgb(181, 179, 179, 1);
    private static final Color dukeErrorMessageColour = Color.rgb(255, 89, 89, 1);
    private static final Color blackColour = Color.color(0, 0, 0);
    private static final CornerRadii messageCornerRadius = new CornerRadii(10.0);
    private static final Insets messageInset = new Insets(-5.0);
    private static final String errorMessagePrefix = "sorry,";
    @FXML
    private Label textMessage;
    private MessageContainer(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MessageContainer.class.getResource("/message.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        textMessage.setText(text);
        textMessage.setEffect(new DropShadow(2.0, Color.BLACK));
        textMessage.setBackground(new Background(new BackgroundFill(userTextMessageColour, messageCornerRadius,
                messageInset)));
    }

    public static MessageContainer getUserDialog(String text) {
        return new MessageContainer(text);
    }

    private void reverseMessageDirection() {
        ObservableList<Node> text = FXCollections.observableArrayList(getChildren());
        Label taubotReply = (Label) text.get(0);
        if (textMessage.getText().split(" ")[0].equals(errorMessagePrefix)) {
            taubotReply.setBackground(new Background(new BackgroundFill(dukeErrorMessageColour, messageCornerRadius,
                    messageInset)));
            return;
        }
        taubotReply.setBackground(new Background(new BackgroundFill(dukeTextMessageColour, messageCornerRadius,
                    messageInset)));
        taubotReply.setTextFill(blackColour);
        Collections.reverse(text);
        getChildren().setAll(text);
        setAlignment(Pos.TOP_LEFT);
    }

    public static MessageContainer getTaubotDialog(String text) {
        MessageContainer taubotReply = new MessageContainer(text);
        taubotReply.reverseMessageDirection();
        return taubotReply;
    }
}
