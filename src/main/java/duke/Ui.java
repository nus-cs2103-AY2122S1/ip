package duke;

import java.util.ArrayList;

import duke.task.Task;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class that interacts with the user.
 */
public class Ui {
    private static final String BYE_OUTPUT = "Bye. Hope to see you again soon!";
    private static final String GREET_OUTPUT = "Hello! I'm Duke. What can I do for you?";
    private static final String INDENT = "    ";
    private ScrollPane scrollPane;
    private Duke duke;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private AnchorPane mainLayout;
    private Stage stage;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image admin = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    /**
     * Constructor for duke's UI.
     */
    public Ui(Duke duke) {
        this.duke = duke;
    }
    public Ui() {}

    /**
     * Shows appropriate error messages to user.
     *
     * @param e Error to be shown.
     */
    public void showError(DukeException e) {
        echo(e.toString());
    }

    /**
     * Closes duke's UI.
     */
    public void exit() {
        echo(BYE_OUTPUT);
        Platform.exit();
    }
    private void greet() {
        echo(GREET_OUTPUT);
    }

    /**
     * Prints a list of tasks.
     *
     * @param tasks List of tasks to be printed.
     * @param heading The heading to precede the list of tasks.
     */
    public void printAll(ArrayList<Task> tasks, String heading) {
        String s = "";
        s += INDENT + heading + "\n";
        for (int i = 1; i <= tasks.size(); i++) {
            s += INDENT + i + " " + tasks.get(i - 1) + "\n";
        }
        echo(s);
    }

    /**
     * Starts duke's ui.
     */
    public void start() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        mainLayout = new AnchorPane();
        userInput = new TextField();
        sendButton = new Button("send");
        stage = new Stage();
        scrollPane.setContent(dialogContainer);
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(500.0);
        mainLayout.setPrefSize(700.0, 600.0);
        dialogContainer.setPadding(new Insets(10));
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.setMaxWidth(800.0);
        scrollPane.setPrefSize(800, 560);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPadding(new Insets(10, 0, 10, 10));
        userInput.setPrefWidth(720.0);
        sendButton.setPrefWidth(70.0);
        sendButton.setPrefHeight(35);
        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        stage.setScene(scene);
        stage.show();
        greet();
        sendButton.setOnMouseClicked((event) -> {
            if (userInput.getText().trim().length() > 0) {
                handleUserInput(userInput.getText().trim());
            }
        });

        userInput.setOnAction((event) -> {
            if (userInput.getText().trim().length() > 0) {
                handleUserInput(userInput.getText().trim());
            }
        });
    }
    private void handleUserInput(String fullCommand) {
        ImageView v = new ImageView(user);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(INDENT + fullCommand + INDENT, v, 0));
        userInput.clear();
        duke.run(fullCommand);
    }

    /**
     * Prints duke's response to the user.
     *
     * @param input Duke's response as a String.
     */
    public void echo(String input) {
        ImageView v = new ImageView(admin);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(INDENT + input, v, 1));
    }
}
