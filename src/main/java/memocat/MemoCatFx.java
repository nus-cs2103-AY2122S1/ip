package memocat;

import javafx.animation.PauseTransition;
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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import memocat.task.Task;
import memocat.task.TaskList;

public class MemoCatFx extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/UserCat.jpeg"));
    private Image memocat = new Image(this.getClass().getResourceAsStream("/images/MemoCat.jpg"));

    /**
     * storage instance to handle task list storage.
     */
    private final Storage storage = new Storage("./data/", "memocat.txt");

    /**
     * task list instance to store tasks.
     */
    private final TaskList tasks = this.storage.readData();

    /**
     * Parser instance to handle parsing.
     */
    private final Parser parser = new Parser();

    public static void main(String[] args) {
        // ...
    }

    @Override
    public void start(Stage stage) {
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
        stage.setTitle("MemoCat");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

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
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // initial greeting message
        greet(dialogContainer);

        // more code to be added here later
    }

    /**
     * Sends greeting message of MemoCat.
     *
     * @param dialogContainer Dialog container of the chat.
     */
    private void greet(VBox dialogContainer) {
        Label greetMessage = new Label("Greetings!\nThis is memocat.\nHow can I help you?");
        dialogContainer.getChildren().add(
                DialogBox.getmemocatDialog(greetMessage, new ImageView(memocat)));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 3:
     * Creates two dialog boxes, one echoing user input and the other containing memocat's reply and then appends them
     * to the dialog container. Clears the user input after processing.
     * Flip a dialog box such that the image on the left to differentiate between user input and memocat’s output.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label memocatText = new Label(getResponse(userInput.getText()));

        ImageView userImageView = new ImageView(user);
        ImageView memocatImageView = new ImageView(memocat);
        userImageView.setClip(new Circle());
        memocatImageView.setClip(new Circle());

        if (userInput.getText().equals("bye")) {
            exitApp();
            return;
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getmemocatDialog(memocatText, new ImageView(memocat))
        );
        userInput.clear();
    }

    /**
     * Adds user and MemoCat dialog for bye.
     *
     * @param dialogContainer The dialog container.
     */
    private void addByeDialog(VBox dialogContainer) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(new Label("bye"), new ImageView(user)),
                DialogBox.getmemocatDialog(new Label("Bye. See you next time!"), new ImageView(memocat))
        );
    }

    /**
     * Exits app after the stated seconds.
     *
     * @param seconds Number of seconds before exiting.
     */
    private void exitAfter(int seconds) {
        PauseTransition delay = new PauseTransition(Duration.seconds(seconds));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }

    /**
     * Exits the app after 1 second.
     */
    private void exitApp() {
        userInput.clear();
        addByeDialog(dialogContainer);
        exitAfter(1);
    }

    /**
     * Generates a response to user input.
     *
     * @param input String of user input.
     */
    private String getResponse(String input) {
        if (input.equals("bye")) {
            // end bot
            try {
                // save all data
                storage.writeTasksToData(tasks);
                return "Bye. See you next time!";
            } catch (MemoCatException e) {
                return e.getMessage();
            }
        }

        switch (parser.getCommandAction(input)) {
        case "list":
            // view list
            return tasks.toString();
        case "done":
            return done(input);
        case "todo":
            return todo(input);
        case "event":
            return event(input);
        case "deadline":
            return deadline(input);
        case "delete":
            return delete(input);
        case "find":
            return find(input);
        case "sort":
            tasks.sortByTime();
            return "Your tasks have been sorted.\n" + tasks.toString();
        default:
            // Message for unrecognised task type
            return "OH NO !!! I'm sorry, but I don't know what that means :-(";
        }
    }

    /**
     * Responses for done command.
     *
     * @param input Input command string.
     * @return String of response.
     */
    private String done(String input) {
        try {
            int doneIndex = parser.getCommandActionIndex(input);
            assert doneIndex <= tasks.size() : "Task index out of bound";

            try {
                tasks.get(doneIndex - 1).markAsDone();
                storage.writeTasksToData(tasks);
                return "Nice! I've marked this task as done:\n\t" + tasks.get(doneIndex - 1);

            } catch (IndexOutOfBoundsException e) {
                // Task at doneIndex does not exist
                return "Task " + doneIndex + " does not exist. Please check your task list!";

            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            // command done is not followed by a number
            return "OH NO!!! The index of a task done must be an integer.";
        } catch (MemoCatException e) {
            return e.getMessage();
        }
    }

    /**
     * Responses for todo command.
     *
     * @param input Input command string.
     * @return String of response.
     */
    private String todo(String input) {
        Task todo;
        try {
            todo = parser.commandToTask(input);
        } catch (MemoCatException e) {
            return e.getMessage();
        }
        tasks.add(todo);
        try {
            storage.writeTasksToData(tasks);
        } catch (MemoCatException e) {
            return e.getMessage();
        }
        return "Got it. I've added this task:\n\t" + todo
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Responses for event command.
     *
     * @param input Input command string.
     * @return String of response.
     */
    private String event(String input) {
        Task event;

        try {
            event = parser.commandToTask(input);
        } catch (MemoCatException e) {
            return e.getMessage();
        }

        tasks.add(event);

        try {
            storage.writeTasksToData(tasks);
        } catch (MemoCatException e) {
            return e.getMessage();
        }

        return "Got it. I've added this task:\n\t" + event
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Responses for deadline command.
     *
     * @param input Input command string.
     * @return String of response.
     */
    private String deadline(String input) {
        Task deadline;

        try {
            deadline = parser.commandToTask(input);
        } catch (MemoCatException e) {
            return e.getMessage();
        }

        tasks.add(deadline);

        try {
            storage.writeTasksToData(tasks);
        } catch (MemoCatException e) {
            return e.getMessage();
        }

        return "Got it. I've added this task:\n\t" + deadline
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Responses for delete command.
     *
     * @param input Input command string.
     * @return String of response.
     */
    private String delete(String input) {
        try {
            int deleteIndex = parser.getCommandActionIndex(input);
            assert deleteIndex <= tasks.size() : "Task index out of bound";

            try {
                Task removed = tasks.remove(deleteIndex - 1);
                storage.writeTasksToData(tasks);
                return "Noted. I've removed this task:\n\t" + removed;

            } catch (IndexOutOfBoundsException e) {
                // Task at deleteIndex does not exist
                return "Task " + deleteIndex + " does not exist. Please check your task list!";
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            // command delete is not followed by a number
            return "OH NO!!! The index of a task to be deleted must be an integer.";
        } catch (MemoCatException e) {
            return e.getMessage();
        }
    }

    /**
     * Finds tasks from task list that contains a given pattern and print the tasks.
     *
     * @param input Input command string.
     * @return String of response.
     */
    private String find(String input) {
        String pattern = input.split("find ", 2)[1];
        return tasks.getMatchedTasksString(pattern);
    }
}
