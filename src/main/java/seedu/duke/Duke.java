package seedu.duke;

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
import seedu.duke.command.Command;
import seedu.duke.command.DeadlineCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.DoneCommand;
import seedu.duke.command.EventCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.GetCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.ToDoCommand;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Represents a chatbot for organising user tasks and to do list.
 * A <code>Duke</code> object takes in user commands and updates the
 * user's tasks.
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @Override
    public void start(Stage stage) {
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

        // Formatting the window to look as expected
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            // Parse the input
            Command command = parser.parseString(input);
            return "Duke is processing your input...";
        } catch (DukeException e) {
            return e.getMessage();
        }

    }

    /**
     * Represents a parser for interpreting user inputs.
     * A <code>Parser</code> object takes in user inputs and
     * interprets it to Duke to execute relevant tasks.
     */
    private class Parser {
        private String[] listOfWords = new String[0];
        private String userInput = "";
        private DateTimeManager manager = new DateTimeManager(
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        private int taskIndex = -1;
        private LocalDate date = LocalDate.now();

        private Command parseString(String userInput) throws DukeException {
            this.userInput = userInput;

            if (userInput.equals("bye")) {
                return new ExitCommand(ui, taskList);
            } else if (userInput.equals("list")) {
                return new ListCommand(ui, taskList);
            } else if (userInput.equals("help")) {
                return new HelpCommand(ui, taskList);
            }

            // Separate them with space
            String[] arrOfCommandWords = userInput.split(" ");
            if (arrOfCommandWords.length <= 1) {
                handleInvalidInputs(userInput);
            }

            this.listOfWords = arrOfCommandWords;
            // Check the command word
            String commandWord = arrOfCommandWords[0];
            switch (commandWord) {
            case "todo":
                return new ToDoCommand(ui, taskList,
                        arrOfCommandWords[1], storage);
            case "deadline":
                parseDescription(userInput, "/by ");
                return new DeadlineCommand(ui, taskList, listOfWords[1],
                        date, storage);
            case "event":
                parseDescription(userInput, "/at ");
                return new EventCommand(ui, taskList, listOfWords[1],
                        date, storage);
            case "done":
                try {
                    int index = Integer.parseInt(arrOfCommandWords[1]) - 1;
                    this.taskIndex = index;
                } catch (NumberFormatException e) {
                    throw new DukeException("Invalid task number");
                }
                return new DoneCommand(ui, taskList, taskIndex, storage);
            case "find":
                return new FindCommand(ui, taskList, listOfWords[1]);
            case "get":
                try {
                    LocalDate tasksDate = manager.parseDateTime(arrOfCommandWords[1]);
                    return new GetCommand(ui, taskList, tasksDate, dateTasks);
                } catch (DateTimeParseException e) {
                    throw new DukeException("Invalid date format.");
                }
            case "delete":
                try {
                    int index = Integer.parseInt(arrOfCommandWords[1]) - 1;
                    this.taskIndex = index;
                } catch (NumberFormatException e) {
                    throw new DukeException("Invalid task number");
                }
                return new DeleteCommand(ui, taskList, taskIndex, storage);
            default:
                throw new DukeException("Sorry, I don't know what that means.");
            }
        }

        private void parseDescription(String userInput, String command) {
            try {
                int indexOfDate = userInput.indexOf(command);
                int startOfDescription = userInput.indexOf(' ');
                if (indexOfDate < 0) {
                    throw new DukeException("No date specified for task.");
                }

                String description = userInput.substring(startOfDescription, indexOfDate);
                listOfWords[1] = description;
                LocalDate date = manager.parseDateTime(
                        userInput.substring(indexOfDate + command.length())
                );
                this.date = date;
            } catch (DateTimeParseException | DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        /**
         * Handles invalid inputs by the user.
         *
         * @param input The user input to Duke.
         */
        private void handleInvalidInputs(String input) throws DukeException {
            switch (input) {
            case "todo": // fallthrough
            case "deadline": // fallthrough
            case "event": {
                throw new DukeException(
                        String.format(
                                "☹ OOPS!!! The description of a %s cannot be empty.",
                                input
                        )
                );
            }
            case "done": // fallthrough
            case "delete":
                throw new DukeException("Please enter the task index.");
            case "get":
                throw new DukeException("Please enter a date in dd/MM/yyyy format.");
            case "find":
                throw new DukeException("Please enter keyword to search for.");
            default:
                throw new DukeException(
                        "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                );
            }
        }

        /**
         * Execute program per the user input parsed.
         *
         * @param type The type of Command specifying the action to
         *             be executed.
         */
        private void executeTasks(Command type) throws DukeException {
            type.execute();
            type.updateDateTasks(dateTasks, manager);
            if (type.updatesTaskList()) {
                taskList = type.getTaskList();
            }
        }

    }

    /**
     * Task list to keep track.
     */
    private TaskList taskList;
    /**
     * Storage to handle file manipulation.
     */
    private Storage storage;
    private Ui ui;
    private Parser parser = new Parser();
    private HashMap<LocalDate, ArrayList<Task>> dateTasks = new HashMap<>();

    public Duke() {}

    /**
     * Public constructor for Duke
     */
    public Duke(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Prints Duke's greetings.
     */
    private void greet() {
        ui.divide();
        ui.outputMessage(
                String.format("Hello! I'm Duke\n%4sWhat can I do for you?",
                        " "));
        ui.divide();
    }

    /**
     * Runs the Duke chatbot.
     */
    private void run() {
        taskList = storage.loadData(dateTasks, taskList);

        // Greeting the user
        greet();

        // Taking in commands
        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine().strip();
            try {
                Command type = parser.parseString(command);
                parser.executeTasks(type);
                if (type.isExit()) {
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                new HelpCommand(ui, taskList).execute();
            }
        }
        // Close the scanner if "bye" command is given and
        // the Duke exits the while loop.
        sc.close();
    }

    /**
     * Main method to execute Duke's functions.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        String directoryPath = "./data";
        String filePath = "./data/duke.text";

        File directory = new File(directoryPath);
        // Check folder exists
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                System.out.println("Successfully created directory.");
            } else {
                System.out.println("An error occurred");
            }
        }

        Duke duke = new Duke(new TaskList(), new Storage(filePath), new Ui());
        duke.run();
    }
}
