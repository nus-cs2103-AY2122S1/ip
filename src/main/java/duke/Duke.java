package duke;

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
 * Represents a Duke chatbot. It helps to collate tasks for the user.
 */
public class Duke extends Application {

    private static final String LOCAL_FILE = "data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Duke() {
    }

    /**
     * Class constructor that constructs a Duke object.
     *
     * @param filePath File Path for Storage to obtain saved data. If data does not exist, a new file will be created
     *                 with that filePath.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.load();
        } catch (DukeException e) {
            ui.showError(e);
        }
    }

    private Image user = new Image(this.getClass().getResourceAsStream("/images/Girl.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/tentacle.jpeg"));

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
        scene.getRoot().setStyle("-fx-font-family: 'serif'");

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

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
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
    private String getResponse(String input) {
        return "testing only relax!!";
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

    /**
     * Runs Duke object. Follows the commands scanned by the Scanner. Ends when "bye" command is detected.
     */
    public void run() {
        // Display welcome
        this.ui.welcome();

        // Initialise Scanner and Parser
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(sc.nextLine());

        // Run Duke while command is not bye
        while (!parser.isBye()) {
            try {
                if (parser.isList()) {
                    // Run based on list command
                    ui.listAll(this.tasks);

                    // Scan for next command
                    parser = new Parser(sc.nextLine());
                } else if (parser.isDone()) {
                    try {
                        // Run based on done command
                        this.tasks.done(parser.getSecondPartInInt());
                        this.storage.save(parser.getCommand());
                        ui.doneTask(this.tasks.getMostRecent());

                        // Scan for next command
                        parser = new Parser(sc.nextLine());
                    } catch (DukeException e) {
                        // Display error
                        ui.showError(e);

                        // Scan for next command
                        parser = new Parser(sc.nextLine());
                    }
                } else if (parser.isToDo()) {
                    try {
                        // Run based on todo command
                        ToDo task = new ToDo(parser.getSecondPart());
                        this.tasks.add(task);
                        this.storage.save(parser.getCommand());
                        ui.addTask(this.tasks.getMostRecent(), this.tasks);

                        // Scan for next command
                        parser = new Parser(sc.nextLine());
                    } catch (DukeException e) {
                        // Display error
                        ui.showError(e);

                        // Scan for next command
                        parser = new Parser(sc.nextLine());
                    }
                } else if (parser.isDeadline()) {
                    try {
                        // Run based on deadline command
                        Deadline task = new Deadline(parser.splitSecondPartForDeadline()[0],
                                parser.splitSecondPartForDeadline()[1]);
                        this.tasks.add(task);
                        this.storage.save(parser.getCommand());
                        ui.addTask(this.tasks.getMostRecent(), this.tasks);

                        // Scan for next command
                        parser = new Parser(sc.nextLine());
                    } catch (DukeException e) {
                        // Display error
                        ui.showError(e);

                        // Scan for next command
                        parser = new Parser(sc.nextLine());
                    }
                } else if (parser.isEvent()) {
                    try {
                        // Run based on event command
                        Event task = new Event(parser.splitSecondPartForEvent()[0],
                                parser.splitSecondPartForEvent()[1]);
                        this.tasks.add(task);
                        this.storage.save(parser.getCommand());
                        ui.addTask(this.tasks.getMostRecent(), this.tasks);

                        // Scan for next command
                        parser = new Parser(sc.nextLine());
                    } catch (DukeException e) {
                        // Display error
                        ui.showError(e);

                        // Scan for next command
                        parser = new Parser(sc.nextLine());
                    }
                } else if (parser.isDelete()) {
                    try {
                        // Run based on delete command
                        this.tasks.delete(parser.getSecondPartInInt());
                        ui.deleteTask(this.tasks.getMostRecent(), this.tasks);

                        // Scan for next command
                        parser = new Parser(sc.nextLine());
                    } catch (DukeException e) {
                        // Display error
                        ui.showError(e);

                        // Scan for next command
                        parser = new Parser(sc.nextLine());
                    }
                } else if (parser.isFind()) {
                    try {
                        TaskList taskList = this.tasks.find(parser.getSecondPart());
                        ui.findTask(taskList);

                        parser = new Parser(sc.nextLine());
                    } catch (DukeException e) {
                        ui.showError(e);

                        parser = new Parser(sc.nextLine());
                    }
                } else {
                    throw new DukeException("I do not know what you want to do!");
                }
            } catch (DukeException e) {
                // Display error
                ui.showError(e);

                // Scan for next command
                parser = new Parser(sc.nextLine());
            }
        }

        // Duke ends
        ui.bye();
        sc.close();
    }

    public static void main(String[] args) {
        new Duke(LOCAL_FILE).run();

    }
}
