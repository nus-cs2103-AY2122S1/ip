package duke.ui;

import duke.message.DukeMessage;
import duke.message.MessageFactory;
import duke.storage.StorageHandler;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class UserInteraction {

    public static void handleUserInput(TextField userInput
            , VBox dialogContainer, Image duke, Image user) {
        String userMsg = userInput.getText();
        if(userMsg.equals("bye")) {
            exitHandler(userInput, dialogContainer, duke, user);
        } else {
            Label userText = new Label(userMsg);
            Label dukeText = new Label(getResponse(userMsg));
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(user)),
                    DialogBox.getDukeDialog(dukeText, new ImageView(duke))
            );
            userInput.clear();
        }
    }

    public static void exitHandler(TextField userInput
            , VBox dialogContainer, Image duke, Image user) {
        Label goodbye = new Label("Ram Ram!");
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(goodbye, new ImageView(duke))
        );
        userInput.clear();
        boolean isStoreSuccessful = StorageHandler.insertTasks();
        assert isStoreSuccessful == true : "couldn't store tasks";
        Platform.exit();
    }

    private static String getResponse(String input) {
        DukeMessage msg = MessageFactory.createMessage(input);
        return msg.createMessageString();
    }

    public static void greetUser(VBox dialogContainer, Image duke) {
        Label greet = new Label("Namaste chacha!\nKaise yaad kiye humko?");
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greet, new ImageView(duke))
        );
    }

}
