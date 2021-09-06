package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
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
 * Duke chat bot main class.
 */
public class Duke extends Application {
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private final Ui ui;
    private TaskList taskList;
    private final Storage storage;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Creates a new duke object
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printError(e.getMessage());
            taskList = new TaskList();
        }
    }
    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Provides response to user based on given input.
     * @param input Input that the user enters.
     * @return The output string from duke.
     */
    public String getResponse(String input) {
        return run(input);
    }

    /**
     * Initiates the Duke chat bot GUI.
     * @param stage JavaFX stage to be passed to the chat bot.
     */
    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        dialogContainer.setPadding(new Insets(10, 10, 10, 10));
        dialogContainer.setSpacing(10);
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // more code to be added here later

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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private String run(String input) {
        int prevSize = taskList.getSize(); //for debug use, keeps track of the size of the tasklist before operation
        String[] commandAndParameter = Parser.inputParser(input);
        Command currentCommand = Parser.parseCommand(commandAndParameter[0]);
        String currentParameter = commandAndParameter[1];

        try {
            String output;
            StringBuilder sb = new StringBuilder();
            String[] descriptionAndTimeParts;
            switch (currentCommand) {
            case UNKNOWN:
                assert (taskList.getSize() == prevSize);
                throw new DukeException("Unknown input");
            case LIST:
                return taskList.listTasks();
            case TODO:
                if (currentParameter == "") {
                    throw new DukeException("TODO cannot have empty parameter.");
                }
                output = taskList.addTask(Command.TODO, currentParameter);
                Storage.updateLocalFile(taskList);
                assert (taskList.getSize() - prevSize == 1);
                return output;
            case EVENT:
                if (currentParameter.equals("")) {
                    throw new DukeException("The description of a event cannot be empty.");
                } else if (!currentParameter.contains(" /at ")) {
                    throw new DukeException("Missing /at command");
                }
                System.out.println(currentParameter);
                descriptionAndTimeParts = Parser.dateParameterParser(Command.EVENT, currentParameter);
                System.out.println(descriptionAndTimeParts);
                output = taskList.addTask(Command.EVENT, descriptionAndTimeParts[0], descriptionAndTimeParts[1]);
                Storage.updateLocalFile(taskList);
                assert (taskList.getSize() - prevSize == 1);
                return output;
            case DEADLINE:
                if (currentParameter.equals("")) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                } else if (!currentParameter.contains(" /by ")) {
                    throw new DukeException("Missing /by command");
                }
                descriptionAndTimeParts = Parser.dateParameterParser(Command.DEADLINE, currentParameter);
                output = taskList.addTask(Command.DEADLINE, descriptionAndTimeParts[0], descriptionAndTimeParts[1]);
                Storage.updateLocalFile(taskList);
                assert (taskList.getSize() - prevSize == 1);
                return output;
            case DELETE:
                if (currentParameter.equals("")) {
                    throw new DukeException("Please indicate item to be deleted.");
                }
                int index = Integer.parseInt(currentParameter) - 1;
                if (index > taskList.getSize() - 1) {
                    throw new DukeException("Item does not exist.");
                }

                output = taskList.removeTask(index);
                Storage.updateLocalFile(taskList);
                assert (taskList.getSize() - prevSize == -1);
                return output;
            case DONE:
                if (currentParameter.equals("")) {
                    throw new DukeException("Please indicate item to be completed.");
                }
                int number = Integer.parseInt(currentParameter) - 1;
                if (number > taskList.getSize() - 1 || number < 0) {
                    throw new DukeException("Invalid item does not exist");
                }
                output = taskList.markAsDone(number);
                Storage.updateLocalFile(taskList);
                return output;
            case FIND:
                if (currentParameter.equals("")) {
                    throw new DukeException("Please indicate word to be found.");
                }
                output = taskList.listMatchingTasks(currentParameter);
                return output;
            case BYE:
                Platform.exit();
                break;
            case HELP:
                String helpMessage =
                    "Welcome to Duke!\n"
                      + "Here are the available commands:\n"
                      + "help: Show the current help message.\n"
                      + "todo DESCRIPTION: Adds a new TODO task.\n"
                      + "event DESCRIPTION /at TIME: Adds a new EVENT task.\n"
                      + "deadline DESCRIPTION /at TIME: Adds a new DEADLINE task.\n"
                      + "find WORD: Find tasks with given word in the description.\n"
                      + "bye: Exits the program.\n"
                      + "list: Shows all tasks.\n"
                      + "delete INDEX: Removes the task at the INDEX number on the task list.\n"
                      + "done INDEX: Marks the task at the INDEX number on the task list as done.\n";
                return helpMessage;
            default:
                throw new DukeException("How did you get here?");
            }
        } catch (DukeException | IOException e) {
            return ("OOPS!!! " + e.getMessage());
        }
        return "How did you get here?";
    }
}
