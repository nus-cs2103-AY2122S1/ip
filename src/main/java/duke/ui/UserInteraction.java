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

    /**
     * Handles the input provided by the user in the text box
     *
     * @param userInput TextField that houses user input
     * @param dialogContainer The vertical box holding UI elements
     * @param duke Display image of the duke bot
     * @param user Display image of the user
     */
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

    /**
     * Handles program termination, trigerred by the user typing bye
     *
     * @param userInput TextField that houses user input
     * @param dialogContainer The vertical box holding UI elements
     * @param duke Display image of the duke bot
     * @param user Display image of the user
     */
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

    /**
     * Returns the response of the bot for a particular user input.
     *
     * @param input The srting value of the input provided by user
     * @return The return generated in response to user's input
     */
    private static String getResponse(String input) {
        DukeMessage msg = MessageFactory.createMessage(input);
        return msg.createMessageString();
    }

    /**
     * Handles the beginning of the program, greets user with a
     * welcome message.
     *
     * @param dialogContainer The vertical box holding UI elements
     * @param duke Display image of the duke bot
     */
    public static void greetUser(VBox dialogContainer, Image duke) {
        Label greet = new Label("Namaste chacha!\nKaise yaad kiye " +
                "humko?(how can I help?)");
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greet, new ImageView(duke))
        );
    }

}
