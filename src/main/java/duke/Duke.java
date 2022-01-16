package duke;

import duke.command.CommandManager;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.DurationCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.exception.DukeException;
import javafx.application.Application;
import javafx.scene.Node;
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
 * Main class for the bot.
 */
public class Duke extends Application {
    private final DukeList list;
    private final CommandManager commandManager;
    private final UserInterface ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Creates a duke application.
     */
    public Duke() {
        this.list = new DukeList();
        this.commandManager = new CommandManager();
        this.ui = new UserInterface(this);
        this.commandManager.registerCommands(new ListCommand(this.list, this.ui), new DoneCommand(this.list, this.ui),
                new ToDoCommand(this.list, this.ui), new EventCommand(this.list, this.ui),
                new DeadlineCommand(this.list, this.ui), new DeleteCommand(this.list, this.ui),
                new FindCommand(this.list, this.ui), new DurationCommand(this.list, this.ui));
    }

    @Override
    public void start(Stage primaryStage) {
        dialogContainer = new VBox();
        scrollPane = this.getScrollPane(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Duke");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
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

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        ui.greet();
    }

    /**
     * Prints a given response by other classes onto dialog container.
     *
     * @param response of the bot
     */
    public void printResponse(String response) {
        Label dukeText = new Label(response);
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(dukeText, new ImageView(duke)));
    }

    private ScrollPane getScrollPane(Node content) {
        scrollPane = new ScrollPane();
        scrollPane.setContent(content);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        return scrollPane;
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates a dialog box for echoing user input and then appends them to the
     * dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        Label userText = new Label(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(userText, new ImageView(user)));
        userInput.clear();

        try {
            this.commandManager.parseInput(input);
        } catch (DukeException e) {
            this.ui.showError(e);
        }
    }

}
