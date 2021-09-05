package duke;

import duke.data.TaskFileStorage;
import duke.exceptions.DukeException;
import duke.fulfillment.FulfillmentHandler;
import duke.io.GuiUserInputHandler;
import duke.io.GuiUserOutputHandler;
import duke.io.UserInputHandler;
import duke.io.UserOutputHandler;
import duke.messages.Message;
import duke.tasks.TaskList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main class representing the Duke helper chat bot.
 *
 * @author kevin9foong
 */
public class Duke extends Application {
    private FulfillmentHandler fulfillmentHandler;

    /**
     * Constructs an instance of the <code>Duke</code> chat bot without assigning fulfillmentHandler field.
     */
    public Duke() {
    }

    /**
     * Constructs an instance of the <code>Duke</code> chat bot with the provided input and output handlers.
     *
     * @param userInputHandler  handles receiving user inputs from the input source.
     * @param userOutputHandler handles sending responses to the output destination.
     */
    public Duke(UserInputHandler userInputHandler, UserOutputHandler userOutputHandler) {
        try {
            TaskList taskList = new TaskList(new TaskFileStorage());
            this.fulfillmentHandler = new FulfillmentHandler(userInputHandler,
                    userOutputHandler, taskList);
        } catch (DukeException de) {
            userOutputHandler.handleOutput(new Message(de.getMessage()));
        }
    }

    /**
     * Builds the GUI and starts the execution of the JavaFX GUI version
     * of the Duke chat bot.
     *
     * @param primaryStage window which user interacts with to use the chat bot.
     */
    @Override
    public void start(Stage primaryStage) {
        // Container for chat message boxes to provide scroll functionality.
        ScrollPane scrollPane = new ScrollPane();
        // Contains main chat message content vertically
        VBox dialogContainer = new VBox();
        dialogContainer.setPadding(new Insets(20, 10, 20, 10));
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.setSpacing(20.0);
        // configures the scroll pane to scroll down to the end when its height changes.
        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));

        scrollPane.setContent(dialogContainer);
        scrollPane.setPrefSize(385.0, 535.0);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        TextField userChatInputField = new TextField();
        userChatInputField.setPrefWidth(325.0);

        Button sendButton = new Button("Send");
        sendButton.setPrefWidth(55.0);

        // set up the Duke chat bot agent.
        Duke guiDuke = new Duke(new GuiUserInputHandler(userChatInputField, dialogContainer,
                loadImageFromFile("DaUser.png")),
                new GuiUserOutputHandler(dialogContainer,
                        loadImageFromFile("DaDuke.png")));
        guiDuke.fulfillmentHandler.runGuiChatBotSetup();

        // handle command submissions from user
        // handles clicking of send button
        sendButton.setOnMouseClicked(clickEvent -> {
            guiDuke.fulfillmentHandler.handleGuiUserCommandInput();
            userChatInputField.clear();
        });

        // handles entering to send message
        userChatInputField.setOnAction(event -> {
            guiDuke.fulfillmentHandler.handleGuiUserCommandInput();
            userChatInputField.clear();
        });

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userChatInputField, sendButton);
        mainLayout.setPrefSize(400.0, 600.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setBottomAnchor(userChatInputField, 1.0);
        AnchorPane.setLeftAnchor(userChatInputField, 1.0);

        Scene scene = new Scene(mainLayout);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Duke");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);
        primaryStage.show();
    }

    /**
     * Loads the image with given image file name placed in the relative /resources/images directory.
     * @param imageFileName name of image file placed in relative /resources/images directory.
     * @return loaded <code>Image</code>.
     */
    private Image loadImageFromFile(String imageFileName) {
        return new Image(this.getClass().getResourceAsStream("/images/" + imageFileName));
    }
}
