package duke.main;

import duke.task.Task;
import duke.task.TaskList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private final Scanner inputScanner;
    private Stage stage;
    private Scene scene;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private AnchorPane mainLayout;


    /**
     * Constructor for Duke UI
     */
    public Ui(Stage stage) {
        this.stage = stage;
        this.inputScanner = new Scanner(System.in);
    }

    public void start() {
        //Step 1. Setting up required components
        this.scrollPane = new ScrollPane();
        this.dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        this.userInput = new TextField();
        this.sendButton = new Button("Send");

        this.mainLayout = new AnchorPane();

        this.scene = new Scene(mainLayout);

        formatWindow();

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        stage.setScene(scene);
        stage.show();
        // more code to be added here later
    }

    public void formatWindow() {
        //Step 1. Formatting the window to look as expected
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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // more code to be added here later
    }

    /**
     * Greets user upon starting the assistant.
     */
    public void greetOnStart() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Gets the next input from the user.
     *
     * @return String input.
     */
    public String getNextInput() {
        return inputScanner.nextLine();
    }

    /**
     * Stops taking inputs from the user.
     */
    public void closeInput() {
        inputScanner.close();
    }

    /**
     * Displays error when user input is empty.
     */
    public void showEmptyInputMessage() {
        System.out.println("\tTake your time :)\n");
    }

    /**
     * Displays error when user command is not found.
     */
    public void showUnknownCommandMessage(String command) {
        System.out.println("\tI don't understand " + command + " (yet...)\n");
    }

    /**
     * Displays the message in the DukeException.
     *
     * @param message to display.
     */
    public void showDukeException(String message) {
        System.out.println(message);
    }

    /**
     * Displays the TaskList.
     *
     * @param taskList to display.
     */
    public void displayTaskList(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("\tYou haven't added any tasks yet\n");
        } else {
            System.out.println(taskList);
        }
    }

    /**
     * Greets an existing user.
     *
     * @param tasks User's existing tasks to be displayed with the greeting.
     */
    public void greetWithFamiliarity(TaskList tasks) {
        System.out.println("\tNice to see you again.");
        System.out.println(tasks.taskSummary());
        if (!tasks.isEmpty()) {
            System.out.println(tasks);
        }
    }

    /**
     * Displays matching tasks.
     *
     * @param matchingTasks to be displayed.
     */
    public void showMatchingTasks(List<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println("\tNo matching tasks found!\n");
        } else {
            System.out.println("\tHere are the matching tasks from your list:\n");
            System.out.println(TaskList.enumerateTasks(matchingTasks));
        }
    }

}
