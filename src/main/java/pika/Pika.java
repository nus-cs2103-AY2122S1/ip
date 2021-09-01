package pika;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Objects;

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
import pika.command.Command;
import pika.exception.PikaException;
import pika.ui.DialogBox;
import pika.ui.Parser;
import pika.ui.Storage;
import pika.ui.TaskList;
import pika.ui.Ui;


/**
 * Main Class to run the Duke ChatBot
 */
public class Pika extends Application {
    private final Storage storage;
    private TaskList taskLists;
    private final Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/ditto.png")));
    private Image duke = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/pika.png")));

    /**
     * Constructor for the duke class
     * @param filePath The path where the txt file is located/to be created
     */
    public Pika(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            this.taskLists = new TaskList(storage.load());
        } catch (PikaException | IOException e) {
            Ui.showLoadingError(); //Inform user that the existing file is of the wrong format
            this.taskLists = new TaskList(); //Creates a new empty list
        }
    }

    /**
     * Constructor for the duke class
     */
    public Pika() {
        ui = new Ui();
        storage = new Storage("data");
        try {
            this.taskLists = new TaskList(storage.load());
        } catch (PikaException | IOException e) {
            Ui.showLoadingError(); //Inform user that the existing file is of the wrong format
            this.taskLists = new TaskList(); //Creates a new empty list
        }
    }

    /**
     * Starts the bot
     */
    public void run() {
        boolean isRunning = true; //Bot is running on start up
        do {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand); //Converts the input to the proper commands
                c.execute(taskLists, ui, storage); //Run the given command
                isRunning = c.isRunning(); //Updates the status of the bot
            } catch (PikaException e) {
                System.out.println(e.getMessage());
            } catch (IOException e2) {
                System.out.println("Pika pi!!! It seems like your file is corrupted!");
            } catch (DateTimeParseException e3) {
                System.out.println("Pika pi!! It seems like your date/time input is wrong!");
            }
        } while (isRunning);
    }

    /**
     * main function
     */
    public static void main(String[] args) {
        new Pika().run(); //To run the bot
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

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    @FXML
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user),
                DialogBox.getDukeDialog(response, duke)
        );
        userInput.clear();
    }

    @FXML
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String output = c.execute(taskLists, ui, storage);
            return output;
        } catch (PikaException e) {
            return e.getMessage();
        } catch (IOException e2) {
            return "☹ OOPS!!! It seems like your file is corrupted!";
        } catch (DateTimeParseException e3) {
            return "☹ OOPS!!! It seems like your date/time input is wrong!";
        }
    }
}
