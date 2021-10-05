package duke;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Launches main UI
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String filePath = "./data/tasks.txt";

    private Stage primaryStage; // reference of current stage
    private static final int WIDTH = 470, HEIGHT = 500;
    private BorderPane rootNode;
    private VBox chatHistory;
    private TextField chatMessage;
    private Button startBot, sendMessage;

    public Duke(){
    }
    public void init() {
        ui = new Ui(this);
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException | IOException e) {
            ui.displayError(e.getMessage());
            tasks = new TaskList();
            return;
        }

        startBot = new Button("Enter to Chat"); // enter chat with bot
        startBot.setStyle("-fx-background-radius: 30px");
        chatHistory = new VBox(10); // chat history
        chatHistory.setAlignment(Pos.CENTER);
        chatHistory.setPrefWidth(WIDTH - 20);
        chatHistory.setMaxWidth(WIDTH - 20);
        chatHistory.setMinWidth(WIDTH - 20);
        chatHistory.setPadding(new Insets(10));
        chatMessage = new TextField(); // area to type command
        sendMessage = new Button("Send");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        rootNode = new BorderPane();
        rootNode.setCenter(createWelcomeScreen());
        rootNode.setStyle("-fx-background-color: black;");
        Scene scene = new Scene(rootNode, WIDTH, HEIGHT);
        primaryStage.setMaxWidth(WIDTH);
        primaryStage.setMinWidth(WIDTH);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Duke");
        primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/1.png")));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private VBox createWelcomeScreen() {

        VBox container = new VBox(35);
        container.setAlignment(Pos.CENTER);

        Image image = new Image("file:./data/1.png", 150, 150, false, false);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);

        container.getChildren().addAll(imageView, startBot);

        startBot.setOnAction(event -> {
            rootNode.setCenter(null);
            rootNode.setCenter(createChatScreen());
            ui.greetUser();
        });

        return container;
    }

    /**
     * Creates and returns a chat window
     *
     * @return chat window
     */
    private GridPane createChatScreen() {

        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(90);

        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(10);

        GridPane container = new GridPane();
        container.getRowConstraints().addAll( row1, row2);

        ScrollPane chatScroll = new ScrollPane();
        chatScroll.setFitToWidth(true);
        chatScroll.setContent(chatHistory);
        chatScroll.vvalueProperty().bind(chatHistory.heightProperty()); // auto scroll
        container.add(chatScroll, 0, 0);

        chatMessage.setPrefColumnCount(35);
        chatMessage.setPrefHeight(45);

        sendMessage.setPrefSize(60, 45);

        HBox chatControl = new HBox();

        chatControl.getChildren().addAll(chatMessage, sendMessage);

        container.add(chatControl, 0, 1);

        EventHandler handler = event -> {

            String command = chatMessage.getText().trim();
            if (command.isEmpty()) {
                return;
            }
            chatMessage.setText("");
            chatHistory.getChildren().add(new ChatMessage(command, false));
            // simulate some delay
            try {
                Thread.sleep(500);
            } catch (Exception ex) {
            }
            handleInput(command); // handle query
        };

        // handle message type events
        chatMessage.setOnAction(handler);
        sendMessage.setOnAction(handler);

        return container;
    }

    public void handleInput(String inputCommand) {

        boolean isExit = false;
        try {
            String fullCommand = inputCommand;
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            storage.writeFile(tasks);
            isExit = (c instanceof ExitCommand);

            // clean chat and go back to welcome screen if exit
            if (isExit) {

                // close the app after a second delay
                try {
                    // remove duplicate message
                    chatHistory.getChildren().remove(chatHistory.getChildren().size() - 1);
                    ui.sayBye();

                    PauseTransition delay = new PauseTransition(Duration.seconds(1));
                    delay.setOnFinished( event -> primaryStage.close() );
                    delay.play();
                } catch (Exception x) {

                }

            }
        } catch (DukeException | IOException e) {
            ui.displayError(e.getMessage());
        }
    }

    public void showMessage(String message, boolean isDuke) {
        if (isDuke) {
            chatMessage.setText("");
            chatHistory.getChildren().add(new ChatMessage(message, true));
        } else {
            chatMessage.setText("");
            chatHistory.getChildren().add(new ChatMessage(message, false));
        }
    }
}
