/**
 * This function simulates a chat bot who interacts with a user to keep track of a todo list.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */
package duke;

import java.util.Scanner;
import java.io.IOException;
import javafx.application.Application;
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

import duke.command.Command;
import duke.exceptions.CommandDoesNotExist;
import duke.exceptions.DukeExceptions;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;
    private final Command command;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructs a Duke object.
     *
     * @param filePath The location where information will be saved in the project.
     * @throws IOException
     */
    public Duke(String filePath) throws IOException {
        this.ui = new Ui(); // Performs the self introduction upon successful initialization.
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.load());
        this.command = new Command(storage, ui);
    }

    /**
     * Constructs a Duke object.
     *
     * @throws IOException
     */
    public Duke() throws IOException {
        this.ui = new Ui(); // Performs the self introduction upon successful initialization.
        this.storage = new Storage("data/duke.txt"); //TODO
        this.taskList = new TaskList(storage.load());
        this.command = new Command(storage, ui);
    }

    /**
     * This function is the body of the chat bot, and contains the flow and commands.
     */
    public void run() throws DukeExceptions, IOException {
        ui.showWelcome();
        String userCommand; // this is the container for the full command received from the user
        String cmd; // this is the container for the first word of the command received from the user

        ///// This listens for the commands and interprets them
        // This part listens for user input and repeats until the command "bye" is identified
        Scanner sc = new Scanner(System.in);
        while (true) {
            userCommand = sc.nextLine();
            Parser parser = new Parser(userCommand);
            cmd = parser.getFirstWord();

            // 'bye' : Ends the program
            if (userCommand.equals("bye") || cmd.equals("bye")) {
                command.bye();
                sc.close();
                break;

            // 'list' : Retrieves information from the hard drive and prints it
            } else if (cmd.equals("list")) {
                command.list(taskList);

            // 'done [int]' : marks the corresponding number in the list as done
            } else if (cmd.equals("done")) {
                command.done(userCommand, taskList);

            // 'delete [int]' : delete the corresponding number
            } else if (cmd.equals("delete")) {
                command.delete(userCommand, taskList);

            } else if (cmd.equals("find")) {
                command.find(userCommand, taskList);

            // Else, an item has been added to the chat bot
            // Commands are either todo, deadline or event
            } else {
                switch (cmd) {
                case "todo" :
                    command.addTodo(userCommand, cmd, taskList);
                    break;

                case "deadline" :
                    command.addDeadline(userCommand, cmd, taskList);
                    break;

                case "event" :
                    command.addEvent(userCommand, cmd, taskList);
                    break;

                default :
                    throw new CommandDoesNotExist(userCommand);
                    // Fallthrough
                }
            }
        }
    }

    @Override
    public void start(Stage stage) {
        // Step 1: Setting up required components

        // The container for the content of the chat to scroll.
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

        ///Step 2. Formatting the window to look as expected
        stage.setTitle("Notaro");
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


        //Step 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        userInput.setOnAction((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
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
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        textToAdd.setPadding(new Insets(0,10,0,10));

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), userImage),
                DialogBox.getDukeDialog(getResponse(userInput.getText()), dukeImage)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method. TODO
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public static void main(String[] args) throws DukeExceptions, IOException {
        new Duke("data/duke.txt").run();
    }
}
