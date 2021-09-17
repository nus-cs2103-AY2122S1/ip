package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *  Runs an application that manages tasks.
 *  Tasks can be added, deleted or marked as done.
 */

public class Duke extends Application{
    private TaskList tasks;
//    private String filePath = "data/tasks.txt";
//    private String directoryName = "data";

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DukeCoin.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DukeSmaug.png"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Storage storage;
    private Ui ui;

    private AnchorPane createContainer() {
        //The container for the content of the chat to scroll. Designing layout.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        return mainLayout;
    }

    public void formatWindow(Stage stage, AnchorPane mainLayout) {
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
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        AnchorPane mainLayout = createContainer();
        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        // Set preferred size and title
        formatWindow(stage, mainLayout);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }
    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

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
            if (input.equals("bye")) {
                storage.saveTask(tasks);
                return ui.showBye();
            }
            if (input.equals("list")) {
                return tasks.printList();
            }
            String[] parsedCommand = Parser.parseCommand(input);

            switch (parsedCommand[0]) {
            case "done":
                //Marks tasks as done
                int index = Integer.parseInt(parsedCommand[1]) - 1;
                assert index > 0 : "index should be positive";
                return tasks.setDone(index);

            case "delete":
                //Deletes tasks
                int indexD = Integer.valueOf(parsedCommand[1]) - 1;
                assert indexD > 0 : "index should be positive";
                return tasks.deleteTask(indexD);

            case "find":
                String keyword = parsedCommand[1];
                return tasks.find(keyword);

            case "todo":
                //Adds a new Todo to the list
                if (parsedCommand.length == 1) {
                    throw new TaskException("The description of a todo cannot be empty");
                }
                Todo newT = new Todo(parsedCommand[1], false);
                return tasks.addTask(newT);

            case "deadline":
                if (parsedCommand.length == 1) {
                    throw new TaskException("The description of a deadline cannot be empty");
                }
                Task newD = Deadline.parseCommand(parsedCommand[1]);
                return tasks.addTask(newD);

            case "event":
                if (parsedCommand.length == 1) {
                    throw new TaskException("The description of an event cannot be empty");
                }
                Task newE = Event.parseCommand(parsedCommand[1]);
                return tasks.addTask(newE);


            default:
                throw new DukeException();
            }
        } catch (DukeException | TaskException | IOException e) {
            return (e.getMessage());
        } catch (DateTimeParseException e) {
            return ("The format of the date should be entered in the form dd-MM-yyyy HH:mm");
        }
//        return("Now you have " + tasks.getNumTask() + " task" + (tasks.getNumTask() > 1 ? "s " : " ") + "in the list");
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
    }


    public void run() {
        try {
            storage.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            ui.showLine();
            String command = scanner.nextLine();
            try {
                if (command.equals("bye")) {
                    ui.showBye();
                    break;
                }
                if (command.equals("list")) {
                    tasks.printList();
                    continue;
                }
                String[] parsedCommand = Parser.parseCommand(command);

                switch (parsedCommand[0]) {
                case "done":
                    //Marks tasks as done
                    int index = Integer.valueOf(parsedCommand[1]) - 1;
                    tasks.setDone(index);
                    storage.saveTask(tasks);
                    break;

                case "delete":
                    //Deletes tasks
                    int indexD = Integer.valueOf(parsedCommand[1]) - 1;
                    tasks.deleteTask(indexD);
                    storage.saveTask(tasks);
                    break;

                case "todo":
                    //Adds a new Todo to the list
                    if (parsedCommand.length == 1) {
                        throw new TaskException("The description of a todo cannot be empty");
                    }
                    Todo newT = new Todo(parsedCommand[1], false);
                    tasks.addTask(newT);
                    storage.saveTask(tasks);
                    break;
                case "deadline":
                    if (parsedCommand.length == 1) {
                        throw new TaskException("The description of a deadline cannot be empty");
                    }
                    Task newD = Deadline.parseCommand(parsedCommand[1]);
                    tasks.addTask(newD);
                    storage.saveTask(tasks);
                    break;
                case "event":
                    if (parsedCommand.length == 1) {
                        throw new TaskException("The description of an event cannot be empty");
                    }
                    Task newE = Event.parseCommand(parsedCommand[1]);
                    tasks.addTask(newE);
                    storage.saveTask(tasks);
                    break;

                default:
                    throw new DukeException();
                }
            } catch (DukeException | TaskException | IOException e) {
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("The format of the date should be entered in the form dd-MM-yyyy HH:mm");
            }
            System.out.println("Now you have " + tasks.getNumTask() + " task" + (tasks.getNumTask() > 1 ? "s " : " ") + "in the list");
        }
    }

//    public static void main(String[] args) {
//        System.out.println("here");
//        new Duke("data/tasks.txt").run();
//    }
}
