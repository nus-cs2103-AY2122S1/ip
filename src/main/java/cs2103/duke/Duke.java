package cs2103.duke;

import cs2103.duke.controllers.DialogBox;
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
import javafx.stage.Stage;

import java.util.Scanner;
import java.io.IOException;

/**
 * This class encapsulates a Duke chat-bot.
 */
public class Duke extends Application {

    private String dukeFilePath;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button enterButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/TheUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/TheDuke.png"));

    public Duke() {

    }

    public Duke(String dukeFilePath) {
        this.dukeFilePath = dukeFilePath;
        storage = new Storage(dukeFilePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            System.out.println(ui.showLoadingError());
            tasks = new TaskList();
        }
        ui = new Ui(tasks);
    }

    @Override
    public void start(Stage stage) {

        // The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        // The sections for user interaction.
        userInput = new TextField();
        enterButton = new Button("Enter");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, enterButton);

        // Set and render the stage.
        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        // Format the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(800.0);
        stage.setMinWidth(600.0);

        mainLayout.setPrefSize(600.0, 800.0);

        scrollPane.setPrefSize(565, 755);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(530.0);

        enterButton.setPrefWidth(70.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(enterButton, 1.0);
        AnchorPane.setRightAnchor(enterButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Handle mouse click on enter button
        enterButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        // Handle pressing enter key in the text field
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Iteration 3:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing. Duke's image is  placed on the left to
     * differentiate between user input and Duke’s output.
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
     * Generate a response given an input from user.
     *
     * @param input The user input received.
     * @return A string representing the response to the user.
     */
    public String getResponse(String input) {
        return "I sense greatness in you, user: \n" + input;
    }

    public void run() {
        System.out.println(ui.showWelcome());
        boolean canExit = false;
        // Scanner to read user inputs
        Scanner scanner = new Scanner(System.in);

        while (!canExit) {
            String userInput = scanner.next();
            try {
                if (userInput.equals("bye")) { // user inputs "bye", set canExit to true and Exit
                    canExit = true;
                    // store task list
                    String temp = tasks.listBeautify();
                    storage.overwriteFile(dukeFilePath, temp);
                    System.out.println(ui.showGoodbye());
                } else { // check first input
                    System.out.println(ui.handleInput(scanner, userInput, tasks));
                }
            } catch (DukeException | IOException e) {
                e.printStackTrace(); // print stack trace for e
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String dukeFilePath = "./data/duke.txt";
        Storage s = new Storage(dukeFilePath);
        s.initialize();
        new Duke(dukeFilePath).run();
    }
}