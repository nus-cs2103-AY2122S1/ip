package retriever;

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
import javafx.scene.text.Font;
import javafx.stage.Stage;

import retriever.exception.RetrieverException;
import retriever.task.TaskList;

/**
 * A Chatbot to help manage your daily schedule.
 */
public class Retriever extends Application {

    /** To perform operations on the tasks stored in the text file*/
    private Storage taskStorage;
    private Ui ui;
    private Parser parser;
    private TaskList taskList;

    /**
     * Initializes various Objects, and also takes in the file path to
     * read the tasks present in that file.
     *
     * @param filePath The file path in which tasks may be stored.
     */
    public Retriever(String filePath) {
        taskStorage = new Storage(filePath);
        taskList = new TaskList(taskStorage);
        parser = new Parser(taskList);
        ui = new Ui();
    }

    /**
     * Initializes various Objects, and assumes the default file path to
     * read the tasks present in that file.
     */
    public Retriever() {
        // In case the user doesn't specify the file path, the default path is taken.
        String filePath = "tasksList.txt";
        taskStorage = new Storage(filePath);
        taskList = new TaskList(taskStorage);
        parser = new Parser(taskList);
        ui = new Ui();
    }


    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @Override
    public void start(Stage stage) {
        Font defaultFont = new Font("Arial", 15);
 /*
        Label helloWorld = new Label("Hello World!");
        helloWorld.setFont(defaultFont);
        Scene scene = new Scene(helloWorld);
        stage.setScene(scene);
        stage.show();

        Stage newStage = new Stage();
        Label description = new Label("I am a Chatbot");
        description.setFont(defaultFont);
        Scene scene2 = new Scene(description);
        newStage.setScene(scene2);
        newStage.show();
*/
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

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
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
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        textToAdd.setFont(new Font("Arial", 15));

        return textToAdd;
    }

    /**
     * Iteration 2:
     *
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        userText.setFont(new Font("Arial", 15));
        Label dukeText = new Label(getResponse(userInput.getText()));
        dukeText.setFont(new Font("Arial", 15));
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
        return "Duke heard: " + input;
    }

    /**
     * Runs the Chatbot engine.
     * Main body of the Retriever Chatbot.
     */
    public void run() {
        // To show the welcome message
        ui.printWelcomeMessage();

        // To store the user input string.
        String userInput;

        // Main body which is repeated till the "bye" keyword is encountered.
        do {
            // Taking input
            userInput = ui.readCommand();

            try {
                parser.parseUserInput(userInput);
            } catch (RetrieverException e) {
                // Catching various exceptions and alerting the user.
                ui.printErrorMessage(e.getMessage());
            }
            ui.printDashedLine();
        } while (!parser.isExit());

        // Printing the good-bye message.
        ui.printGoodByeMessage();
    }

    /**
     * Returns an interactive session with the Chatbot. Basically a mean to interact with it.
     *
     * @param args Here, usually nothing is passed.
     */
    public static void main(String[] args) {
        // Calling the run() method to start the Chatbot.
        new Retriever("tasksList.txt").run();
    }
}

