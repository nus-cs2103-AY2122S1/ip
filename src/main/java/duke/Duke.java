package duke;

import java.util.Scanner;

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

/**
 * Implements a ChatBot named Duke that helps to create and maintain a task list.
 */
public class Duke extends Application {
    private final Scanner scanner = new Scanner(System.in);
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;

    private final Image user = new Image(this.getClass().getResourceAsStream("/images/perry.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/doofenshmirtz.jpg"));

    /**
     * Creates Duke ChatBot.
     */
    public Duke() {
        storage = new Storage("data/TaskList.txt");
        ui = new Ui();
        try {
            tasks = new TaskList(storage.loadTask());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Starts up Duke and reads user inputs for commands.
     */
    public void run() {
        String exitMessage = "i zao first";
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            isExit = Parser.parse(fullCommand, ui, storage, tasks).equals(exitMessage);
            ui.showLine();
        }
        scanner.close();
    }

    /**
     * Runs Duke.
     * @param args Null
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    @Override
    public void start(Stage stage) {
        double layoutHeight = 600.0;
        double layoutWidth = 400.0;
        double scrollPaneWidth = 385;
        double scrollPaneHeight = 535;
        double userInputWidth = 325.0;
        double sendButtonWidth = 55.0;
        double anchorValue = 1.0;
        double scrollPaneVValue = 1.0;
        double dialogPadding = 10;
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Perry");
        stage.setResizable(false);
        stage.setMinHeight(layoutHeight);
        stage.setMinWidth(layoutWidth);

        mainLayout.setPrefSize(layoutWidth, layoutHeight);

        scrollPane.setPrefSize(scrollPaneWidth, scrollPaneHeight);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(scrollPaneVValue);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(userInputWidth);

        sendButton.setPrefWidth(sendButtonWidth);

        AnchorPane.setTopAnchor(scrollPane, anchorValue);

        AnchorPane.setBottomAnchor(sendButton, anchorValue);
        AnchorPane.setRightAnchor(sendButton, anchorValue);

        AnchorPane.setLeftAnchor(userInput , anchorValue);
        AnchorPane.setBottomAnchor(userInput, anchorValue);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(scrollPaneVValue));
        dialogContainer.setPadding(new Insets(dialogPadding));
        Label welcomeMessage = new Label(ui.welcome());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(welcomeMessage, new ImageView(duke)));
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

    private String getResponse(String input) {
        return Parser.parse(input, ui, storage, tasks);
    }
}
