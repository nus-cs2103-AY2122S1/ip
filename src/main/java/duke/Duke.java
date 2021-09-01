package duke;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/** This is the main class for the program. */
public class Duke {
    private static final String FILE_PATH = "data.txt";
    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Construct an instance of Duke program.
     *
     * @param filePath The path of the file to load saved data from.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            System.out.println(Ui.showLoadingError());
            this.tasks = new TaskList();
        }
    }

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data.txt");
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            System.out.println(Ui.showLoadingError());
            this.tasks = new TaskList();
        }
    }

    /** Start of the program. */
    public void run() {
        ui.welcomeMessage();
        boolean isExit = false;

        // Main loop of program
        while (!isExit) {
            try {
                // Deal with user's input
                ui.enterCommand();
                String fullCommand = ui.readInput();
                ui.lineGenerator();

                // Execute command based on user's input
                duke.parser.Parser parser = new duke.parser.Parser(fullCommand);
                parser.execute(tasks, ui, storage);
                isExit = parser.getIsByeCommand();
            } catch (DukeException e) {
                System.out.println(ui.printError(e));
            } finally {
                ui.lineGenerator();
            }
        }
    }

//    @Override
//    public void start(Stage stage) {
//        //Step 1. Setting up required components
//
//        //The container for the content of the chat to scroll.
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout);
//
//        stage.setScene(scene);
//        stage.show();
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
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        //Step 3. Add functionality to handle user input.
//        MainWindow main = new MainWindow();
//        sendButton.setOnMouseClicked((event) -> {
//            main.handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            main.handleUserInput();
//        });
//
//        //Scroll down to the end every time dialogContainer's height changes.
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//    }

    public String getResponse(String input) {
        try {
            duke.parser.Parser parser = new duke.parser.Parser(input);
            return parser.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.printError(e);
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}
