package duke;

import java.util.Scanner;

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

import duke.command.DukeCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulate a bot which helps user to keep track of a list of task.
 *
 * @author Zhi Bin
 * @version Duke Level 10
 */
public class Duke extends Application {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;
    private final Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor of Duke
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
        parser = new Parser(taskList, storage, ui);
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        scrollPane = new ScrollPane();
        scrollPane.setPrefSize(385,535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer = new VBox();
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));

        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        userInput.setPrefWidth(325.0);
        userInput.setOnAction(event -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        userInput.setOnAction(event -> {
            handleUserInput();
        });

        sendButton = new Button("Send");
        sendButton.setPrefWidth(55.0);
        sendButton.setOnMouseClicked(event -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        sendButton.setOnMouseClicked(event -> {
            handleUserInput();
        });

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.setPrefSize(400.0, 600.0);
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        scene = new Scene(mainLayout);

        primaryStage.setTitle("Duke");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMaxHeight(400.0);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void run() {
        boolean isActive = true;
        taskList.loadFromList(storage.load());

        ui.greet();

        Scanner sc = new Scanner(System.in);

        while (isActive) {
            String input = sc.nextLine();
            try {
                DukeCommand command = parser.processInput(input);
                command.execute();
                isActive = !command.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    /**
     * Starts the program.
     *
     * @param args Arguments needed to start to program
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param  text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them to the dialog container. Clears the user input after processing.
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
     * Currently a stub, to be replaced.
     *
     * @param input The input to be displayed
     * @return A string containing the response to be displayed under duke
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
