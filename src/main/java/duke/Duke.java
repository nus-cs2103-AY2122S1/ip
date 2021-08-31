package duke;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main class of the Duke chat-bot. When the main class is run, it creates an
 * instance of Duke which allows text-based user interaction.
 */
public class Duke extends Application {
    private final Storage storage;
    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaUser.jpg")));
    private final Image dukeImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaDuke.jpg")));
    private TaskList tasks = new TaskList();
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Class constructor for Duke.
     */
    public Duke() {
        storage = new Storage("duke.txt");
    }

    @Override
    public void start(Stage stage) {
        // Code reused from Jeffry Lum (https://se-education.org/)

        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        mainLayout.setPrefSize(400.0, 600.0);
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Initialise and show intro message
        try {
            tasks = storage.readSave();
        } catch (EOFException e) {
            addDialogsInChatBox(dukeDialog(Ui.NEW_SAVE_STRING));
        } catch (IOException | ClassNotFoundException e) {
            addDialogsInChatBox(dukeDialog(Ui.READ_SAVE_STRING));
        }
        addDialogsInChatBox(dukeDialog(Ui.INTRO_STRING));

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                userDialog(userText),
                dukeDialog(dukeText)
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        Command command;
        try {
            command = Parser.parseUserInput(input);
        } catch (DukeException e) {
            return e.toString();
        }
        Task task;
        switch (command.getOperation()) {
        case "bye":
            try {
                storage.writeSave(tasks);
            } catch (IOException e) {
                return Ui.WRITE_SAVE_STRING;
            }
            Platform.exit();
            return "";
        case "list":
            return Ui.taskListString(tasks);
        case "done":
            task = tasks.get(command.getIndex() - 1);
            task.setDone(true);
            return Ui.doneString(task);
        case "todo":
            task = new ToDo(command.getDescription());
            tasks.add(task);
            return Ui.addedString(task, tasks.size());
        case "deadline":
            task = new Deadline(command.getDescription(), command.getTime());
            tasks.add(task);
            return Ui.addedString(task, tasks.size());
        case "event":
            task = new Event(command.getDescription(), command.getTime());
            tasks.add(task);
            return Ui.addedString(task, tasks.size());
        case "delete":
            try {
                task = tasks.get(command.getIndex() - 1);
            } catch (IndexOutOfBoundsException e) {
                return Ui.outOfBoundsString(command.getIndex());
            }
            tasks.delete(command.getIndex() - 1);
            return Ui.deletedString(task, tasks.size());
        case "find":
            TaskList filteredTasks = tasks.find(command.getDescription());
            return Ui.matchesString(filteredTasks);
        default:
            return "";
        }
    }

    private void addDialogsInChatBox(DialogBox... dialogs) {
        dialogContainer.getChildren().addAll(dialogs[0]);
        if (dialogs.length != 1) {
            addDialogsInChatBox(Arrays.copyOfRange(dialogs, 1, dialogs.length));
        }
    }

    private DialogBox dukeDialog(String message) {
        return DialogBox.getDukeDialog(new Label(message), new ImageView(dukeImage));
    }

    private DialogBox userDialog(String message) {
        return DialogBox.getUserDialog(new Label(message), new ImageView(userImage));
    }
}
