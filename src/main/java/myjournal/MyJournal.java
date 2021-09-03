package myjournal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
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

/**
 * Creates chatBot called MyJournal.
 *
 * @author Felissa Faustine
 */
public class MyJournal extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image myJournal = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructs the object MyJournal.
     *
     * @param filepath The filepath to MyJournal file.
     */
    public MyJournal(String filepath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            tasks = new TaskList();
        }
    }

    /**
     * Constructs the object MyJournal.
     */
    public MyJournal() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("./tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the whole programme of MyJournal.
     *
     * @throws IOException The exception thrown when file is corrupted.
     */
    public void run() throws IOException {
        String input;
        Scanner reader = new Scanner(System.in);
        ui.welcomeMessage();
        while (true) {
            input = reader.nextLine();
            Scanner currLine = new Scanner(input);
            if (input.equals("bye")) {
                break;
            } else {
                parser.parse(currLine, tasks, ui);
            }
            storage.saveFile(tasks.toString());
        }
        ui.goodByeMessage();
    }

    /**
     * Starts GUI.
     *
     * @param stage The stage.
     */
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

        stage.setTitle("MyJournal");
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
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

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

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        Scanner input = new Scanner(userText);
        String parsedInput = parser.parse(input, tasks, ui);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new Image(String.valueOf(user))),
                DialogBox.getMyJournalDialog(parsedInput, new Image(String.valueOf(myJournal)))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws IOException {
        Scanner text = new Scanner(input);
        String parsedInput = parser.parse(text, tasks, ui);
        storage.saveFile(tasks.toString());
        return "MyJournal: " + parsedInput;
    }

    //    /**
    //     * The main method of the MyJournal class.
    //     *
    //     * @param args An input of an array of strings.
    //     */
    //    public static void main(String[] args) {
    //        try {
    //            new MyJournal().run();
    //            //new MyJournal("./tasks.txt").run();
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        }
    //    }
}
