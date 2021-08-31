package duke;

import duke.task.Task;
import duke.task.TaskList;
import javafx.application.Application;
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

@SuppressWarnings("checkstyle:Regexp")
public class DukeFx extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/UserCat.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DukeCat.jpg"));

    /**
     * storage instance to handle task list storage.
     */
    private final Storage storage = new Storage("./data/", "duke.txt");

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
        stage.setTitle("DukePlus");
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
        // more code to be added here later
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
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * Flip a dialog box such that the image on the left to differentiate between user input and Duke’s output.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        ImageView userImageView = new ImageView(user);
        ImageView dukeImageView = new ImageView(duke);
        userImageView.setClip(new Circle());
        dukeImageView.setClip(new Circle());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Generate a response to user input.
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
            } catch (DukeException e) {
                return e.getMessage();
            }
        } else {
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
            default:
                // Message for unrecognised task type
                return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
        }
    }

    /**
     * Duke response message for done command.
     *
     * @param input Input command string.
     * @return String of response.
     */
    private String done(String input) {
        try {
            int doneIndex = parser.getCommandActionIndex(input);
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
            return "☹ OOPS!!! The index of a task done must be an integer.";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Duke response message for todo command.
     *
     * @param input Input command string.
     * @return String of response.
     */
    private String todo(String input) {
        Task todo;
        try {
            todo = parser.commandToTask(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
        tasks.add(todo);
        try {
            storage.writeTasksToData(tasks);
        } catch (DukeException e) {
            return e.getMessage();
        }
        return "Got it. I've added this task:\n\t" + todo
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Duke response message for event command.
     *
     * @param input Input command string.
     * @return String of response.
     */
    private String event(String input) {
        Task event;
        try {
            event = parser.commandToTask(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
        tasks.add(event);
        try {
            storage.writeTasksToData(tasks);
        } catch (DukeException e) {
            return e.getMessage();
        }
        return "Got it. I've added this task:\n\t" + event
                + "\nNow you have " + tasks.size() + " tasks in the list.";

    }

    /**
     * Duke response message for deadline command.
     *
     * @param input Input command string.
     * @return String of response.
     */
    private String deadline(String input) {
        Task deadline;
        try {
            deadline = parser.commandToTask(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
        tasks.add(deadline);
        try {
            storage.writeTasksToData(tasks);
        } catch (DukeException e) {
            return e.getMessage();
        }
        return "Got it. I've added this task:\n\t" + deadline
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Duke response message for delete command.
     *
     * @param input Input command string.
     * @return String of response.
     */
    private String delete(String input) {
        try {
            int deleteIndex = parser.getCommandActionIndex(input);
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
            return "☹ OOPS!!! The index of a task to be deleted must be an integer.";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Find tasks from task list that contains a given pattern and print the tasks.
     *
     * @param input Input command string.
     * @return String of response.
     */
    private String find(String input) {
        String pattern = input.split("find ", 2)[1];
        return tasks.getMatchedTasksString(pattern);
    }
}
