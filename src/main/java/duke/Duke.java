package duke;

import duke.command.Command;
import duke.exception.InvalidInputException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Duke {

    // Already uses Gradle

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

//    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
//    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));
//
//    private Duke duke = new Duke();

    /**
     * Constructor for a Duke instance.
     *
     * @param filePath The specified path of the file we will write and load information from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/tasks.json");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke chatbot.
     *
     * @throws InvalidInputException If the input is deemed invalid.
     */
    public void run() throws InvalidInputException {

        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String input = ui.readCommand();
            Command c = Parser.parseCommand(input);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }

    }

//    @Override
//    public void start(Stage stage) {
//        //Step 1. Setting up required components
//
//        //The container for the content of the chat to scroll.
//        ScrollPane scrollPane = new ScrollPane();
//        VBox dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        TextField userInput = new TextField();
//        Button sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        Scene scene = new Scene(mainLayout);
//
//        stage.setScene(scene);
//        stage.show();
//
//        // more code to be added here later
//
//        //Step 2. Formatting the window to look as expected
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        // You will need to import `javafx.scene.layout.Region` for this.
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput, 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        //Step 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        userInput.setOnAction((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        //Scroll down to the end every time dialogContainer's height changes.
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//
//        //Part 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput(dialogContainer, userInput);
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput(dialogContainer, userInput);
//        });
//    }
//
//    /**
//     * Iteration 1:
//     * Creates a label with the specified text and adds it to the dialog container.
//     * @param text String containing text to add
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        // You will need to import `javafx.scene.control.Label`.
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }
//
//    /**
//     * Iteration 2:
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    @FXML
//    private void handleUserInput(VBox dialogContainer, TextField userInput) {
//        String input = userInput.getText();
//        String response = duke.getResponse(input);
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(input, userImage),
//                DialogBox.getDukeDialog(response, dukeImage)
//        );
//        userInput.clear();
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return "Duke heard: " + input;
    }


    public static void main(String[] args) throws InvalidInputException {
        new Duke("./data/tasks.json").run();
    }
}
