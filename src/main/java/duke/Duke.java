package duke;

import java.time.format.DateTimeFormatter;

import duke.command.Command;
import javafx.DialogBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Duke extends Application {
    private static final String LOCAL_STORAGE_LOCATION = "src/Data/LocalStorage.txt";
    private static final DateTimeFormatter FORMAT_FROM_LOCAL_STORAGE = DateTimeFormatter.ofPattern("dd LLLL yyyy");

    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image apollo = new Image(this.getClass().getResourceAsStream("/images/Apollo.jpg"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit = false;

    /**
     * Constructor for class Duke
     */
    public Duke() {
        assert !user.isError() : "image failed to load, maybe it has been moved or is not there";
        ui = new Ui();
        storage = new Storage(LOCAL_STORAGE_LOCATION);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
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

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private void handleUserInput() {
        dialogContainer.getChildren().addAll(
                new DialogBox(userInput.getText(), user),
                new DialogBox(userInput.getText(), apollo)
        );
        userInput.clear();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            this.isExit = c.isExit();
        } catch (DukeException e) {
            ui.reply(e.getMessage());
        }
        return ui.getCurrentResponse();
    }

    public boolean isDukeExit() {
        return this.isExit;
    }

    /**
     * Returns the formatter used for local storage
     *
     * @return formatter used for local storage
     */
    public static DateTimeFormatter getFormatter() {
        return FORMAT_FROM_LOCAL_STORAGE;
    }

    /**
     * Returns location of local storage
     *
     * @return location of local storage
     */
    public static String getLocalStorageLocation() {
        assert !LOCAL_STORAGE_LOCATION.equals("") : "Local storage should be defined";
        return LOCAL_STORAGE_LOCATION;
    }
}
