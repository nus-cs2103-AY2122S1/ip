import java.time.LocalDateTime;
import java.util.Scanner;

import duke.commands.Deadline;
import duke.commands.Event;
import duke.commands.Find;
import duke.commands.Task;
import duke.commands.Todo;
import duke.data.DukeException;
import duke.data.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Encapsulates the entire Duke program.
 * Main class contains the storage, ui, tasklist, parser and finder objects,
 * to help run the program.
 *
 * @author: Jason Ng
 * @version: Duke Level-9
 */
public class Main extends Application {
    /** Storage component for Duke */
    private Storage storage;
    /** UI component for Duke */
    private Ui ui;
    /** TaskList component for Duke */
    private TaskList taskList;
    /** Parser component for Duke */
    private Parser parser;
    /** Find component for Duke */
    private Find finder;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DSC_0352.png"));
    private Image user = new Image(this.getClass().getResourceAsStream("/images/Timetable.png"));

    /**
     * Constructor for a Main() class.
     */
    public Main() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.taskList = new TaskList(storage.Load());
        this.parser = new Parser();
        this.finder = new Find();
    }

    /**
     * Driver for Duke program.
     */
    public void run() {
        this.ui.PrintIntro();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!this.parser.isBye(input)) {
            String[] splitInput = this.parser.splitType(input);
            if (this.parser.isDone(splitInput[0])) {
                int index = this.parser.getIndex(splitInput);
                String returnString = this.taskList.markDone(index);
                this.ui.PrintMessage(returnString);
            } else if (this.parser.isList(splitInput[0])) {
                this.ui.PrintList(taskList);
            } else if (this.parser.isTodo(splitInput[0])) {
                try {
                    this.parser.parseTodo(splitInput);
                    this.taskList.add(new Todo(splitInput[1]));
                    ui.PrintSpecialTasks(taskList);
                } catch (DukeException e) {
                    ui.PrintMessage(e.getMessage());
                }
            } else if (this.parser.isDeadline(splitInput[0])) {
                try {
                    String[] furtherSplits = this.parser.parseDeadline(splitInput);
                    LocalDateTime by = this.parser.parseTime(furtherSplits[1]);
                    this.taskList.add(new Deadline(furtherSplits[0], by));
                    ui.PrintSpecialTasks(this.taskList);
                } catch (DukeException e) {
                    ui.PrintMessage(e.getMessage());
                }
            } else if (this.parser.isEvent(splitInput[0])) {
                try {
                    String[] furtherSplits = this.parser.parseEvent(splitInput);
                    LocalDateTime at = this.parser.parseTime(furtherSplits[1]);
                    this.taskList.add(new Event(furtherSplits[0], at));
                    ui.PrintSpecialTasks(this.taskList);
                } catch (DukeException e) {
                    ui.PrintMessage(e.getMessage());

                }
            } else if (this.parser.isDelete(splitInput[0])) {
                try {
                    int index = this.parser.parseDelete(splitInput);
                    this.parser.checkTaskIndex(index, taskList);
                    Task deleted = this.taskList.delete(index);
                    ui.PrintDelete(deleted, taskList);
                } catch (DukeException e1) {
                    ui.PrintMessage(e1.getMessage());
                } catch (NumberFormatException e2) {
                    DukeException newException = new DukeException("Please input a number!");
                    ui.PrintMessage(newException.getMessage());
                }
            } else if (this.parser.isFind(splitInput[0])) {
                try {
                    this.parser.parseFind(splitInput);
                    TaskList matched = this.finder.findMatch(splitInput[1], taskList);
                    ui.PrintFind(matched);
                } catch (DukeException e) {
                    ui.PrintMessage(e.getMessage());
                }
            } else {
                try {
                    if (this.parser.isBlah(input)) {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                    this.taskList.add(new Task(input));
                    ui.PrintMessage(String.format("added: %s", input));
                } catch (DukeException e) {
                    ui.PrintMessage(e.getMessage());
                }
            }
            this.storage.Save(taskList);
            input = sc.nextLine();
        }
        ui.PrintMessage("Bye. Hope to see you again soon!");
    }

    @Override
    public void start(Stage stage) {
        Font defaultFont = new Font("Arial", 15);

        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        scene = new Scene(helloWorld); // Setting the scene to be our Label
        helloWorld.setFont(defaultFont);

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        userInput.setFont(defaultFont);
        sendButton = new Button("Send");
        sendButton.setFont(defaultFont);

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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(75.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText(), defaultFont));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText(), defaultFont));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(defaultFont);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(defaultFont);
        });
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text, Font font) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setFont(font);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput(Font font) {
        Label userText = new Label(userInput.getText());
        userText.setFont(font);
        Label dukeText = new Label(getResponse(userInput.getText()));
        dukeText.setFont(font);
        dialogContainer.getChildren().addAll(
                new DialogBox(userText, new ImageView(user)),
                new DialogBox(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public static void main(String[] args) {
        new Main().run();
    }
}

