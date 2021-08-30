package blue;

import java.util.HashMap;
import java.util.Scanner;

import blue.handler.CommandHandler;
import blue.handler.DeadlineHandler;
import blue.handler.DeleteHandler;
import blue.handler.DoneHandler;
import blue.handler.EventHandler;
import blue.handler.FindHandler;
import blue.handler.ListHandler;
import blue.handler.ToDoHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Entry point of the Blue application.
 * Initializes the application and interacts with the user.
 */
public class Blue extends Application {
    private static final String DEFAULT_FILEPATH = "data/tasks.txt";
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private HashMap<String, CommandHandler> commandHandlers;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private AnchorPane mainLayout;

    /**
     * Default constructor to avoid NoSuchElementExceptions
     */
    public Blue() {
        this(DEFAULT_FILEPATH);
    }

    /**
     * Constructs a Blue instance.
     *
     * @param filePath Path to save tasks.
     */
    public Blue(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BlueException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Keeps engaging the user until the user input the exit command.
     */
    public void run() {
        initCommandHandlers();
        ui.showLogo();
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            boolean shouldContinue = canHandle(input);
            storage.save(tasks);
            if (!shouldContinue) {
                break;
            }
        }
        scanner.close();
    }

    private void initCommandHandlers() {
        // Construct the handlers
        ListHandler listHandler = new ListHandler(tasks);
        ToDoHandler toDoHandler = new ToDoHandler(tasks);
        DeadlineHandler deadlineHandler = new DeadlineHandler(tasks);
        EventHandler eventHandler = new EventHandler(tasks);
        DoneHandler doneHandler = new DoneHandler(tasks);
        DeleteHandler deleteHandler = new DeleteHandler(tasks);
        FindHandler findHandler = new FindHandler(tasks);

        // put the handlers into HashMap
        commandHandlers = new HashMap<>();
        commandHandlers.put(Command.LIST, listHandler);
        commandHandlers.put(Command.TODO, toDoHandler);
        commandHandlers.put(Command.DEADLINE, deadlineHandler);
        commandHandlers.put(Command.EVENT, eventHandler);
        commandHandlers.put(Command.DONE, doneHandler);
        commandHandlers.put(Command.DELETE, deleteHandler);
        commandHandlers.put(Command.FIND, findHandler);
    }

    private boolean canHandle(String input) {
        String command = Parser.getCommand(input);
        if (command.equals(Command.EXIT)) {
            ui.sayGoodbye();
            return false;
        }
        if (commandHandlers.containsKey(command)) {
            CommandHandler commandHandler = commandHandlers.get(command);
            try {
                String response = commandHandler.handle(input);
                ui.speak(response);
            } catch (BlueException e) {
                ui.speak(e.getMessage());
            }
        } else {
            ui.actConfused();
        }
        return true;
    }

    /**
     * Creates a Blue instance and runs it.
     *
     * @param args Ignored.
     */
    public static void main(String[] args) {
        new Blue("data/tasks.txt").run();
    }

    @Override
    public void start(Stage primaryStage) {
        designAndShowLayout(primaryStage);
        styleControls(primaryStage);
    }

    private void designAndShowLayout(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }

    private void styleControls(Stage stage) {
        stage.setTitle("Blue");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setBottomAnchor(userInput, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
    }
}
