package duke;


import duke.task.Task;
import duke.task.TaskList;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


/**
 * Class responsible for all UI interactions with user.
 *
 * @author Aiken Wong
 */
public class Ui {

    private Stage stage;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private AnchorPane mainLayout;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userPhoto = new Image(this.getClass().getResourceAsStream("/images/UserDisplayPhoto.jpg"));
    private Image botPhoto = new Image(this.getClass().getResourceAsStream("/images/BotDisplayPhoto.jpg"));
    private Image botErrorPhoto = new Image(this.getClass().getResourceAsStream("/images/BotErrorDisplayPhoto.jpg"));
    private Image backgroundImage = new Image(this.getClass().getResourceAsStream("/Images/BackgroundImage.jpg"));


    /**
     * Constructor for jUnit testing
     */
    public Ui() {

    }

    /**
     * Constructor for Ui class
     */
    public Ui(Stage stage) {
        this.stage = stage;

        // setting variables
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();


        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");
        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);

        // setup styling/ formatting
        stage.setScene(scene);
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
        dialogContainer.setSpacing(20);
        BackgroundImage wrappedBackgroundImage = new BackgroundImage(backgroundImage, null, null, null, null);
        dialogContainer.setPrefHeight(535);
        dialogContainer.setBackground(new Background(wrappedBackgroundImage));
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }


    /**
     * Displays the message of any DukeException thrown by Duke.
     *
     * @param e DukeException to be printed.
     */
    protected void showException(DukeException e) {
        String message = e.getMessage() + "\n";
        displayDukeErrorReply(message);

    }


    /**
     * Gives greetings to users as they log into Duke.
     */
    protected void greet() {
        String message = "Good Day Sir/Mdm, I am 2Butler.\nWhat can I do for you?\n";
        displayDukeReply(message);

    }

    /**
     * Bids users goodbye as they exit the app.
     */
    public void end() {
        String message = "Goodbye Sir/Mdm. Hope to serve you again soon!\n";
        displayDukeReply(message);
        Platform.exit();

    }

    /**
     * Gives confirmation upon user's command to add task.
     *
     * @param task  The given task to be added.
     * @param tasks The task list that the task is added to.
     */
    public void add(Task task, TaskList tasks) {
        String message = "Understood Sir/Mdm, I have added the indicated task: " + "\n   " + task + "\n"
            + "Now you have " + tasks.size() + (tasks.size() == 1 ? " task." : " tasks.") + "\n";
        displayDukeReply(message);

    }

    /**
     * Shows the list of current tasks to the user.
     *
     * @param tasks The current tasks.
     */
    public void showList(TaskList tasks) {
        String message = "Here are your tasks Sir/Mdm:\n" + list(tasks) + "\n";
        displayDukeReply(message);
    }

    /**
     * Gives confirmation to the user that a task is marked as done.
     *
     * @param task The task to be marked as done.
     */
    public void markDone(Task task) {
        String message = "Good job Sir/Mdm! I shall mark this task as complete:\n   "
            + task + "\n";
        displayDukeReply(message);

    }

    /**
     * Gives confirmation that a task has been deleted.
     *
     * @param task  The task to be deleted.
     * @param tasks The user's current tasks.
     */
    public void delete(Task task, TaskList tasks) {
        String message = "Much obliged Sir/Mdm! I shall delete this task:\n   "
            + task + "\n" + "Now you have " + tasks.size()
            + (tasks.size() == 1 ? " task." : " tasks.") + "\n";


        displayDukeReply(message);
    }

    /**
     * Confirms that task list is sorted and shows the list to the user.
     *
     * @param tasks
     */
    public void showSortedList(TaskList tasks) {
        String message = "Roger Sir/Mdm! Your tasks are now sorted:\n"
            + list(tasks) + "\n";
        displayDukeReply(message);
    }


    /**
     * Produces a string enumerating the tasks of the given task list.
     *
     * @param tasks The given tasks list to enumerate.
     * @return
     */
    public String list(TaskList tasks) {
        String list = "";
        for (int i = 0; i < tasks.size(); i++) {
            list = list + (i == 0 ? "" : "\n") + (i + 1) + ". " + tasks.get(i);
        }
        return list;
    }

    /**
     * Informs user of all tasks found by date.
     *
     * @param foundTasks The tasks that have been found by date.
     */
    public void findByDate(TaskList foundTasks) {
        String message = "Here are the deadlines and events that match the date Sir/Mdm:\n"
            + list(foundTasks) + "\n";

        displayDukeReply(message);

    }

    /**
     * Informs user of all tasks found by matching words.
     *
     * @param foundTasks The tasks that have been found by matching words.
     */
    public void findByDescription(TaskList foundTasks) {
        String message = "Here are the results of the search Sir/Mdm:\n"
            + list(foundTasks) + "\n";

        displayDukeReply(message);
    }

    /**
     * Display GUI and greet user.
     */
    public void showGui(TaskList tasks, Storage storage) {
        // Create event listeners
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(tasks, storage);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(tasks, storage);
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        stage.show();
        this.greet();
    }


    private void handleUserInput(TaskList tasks, Storage storage) {

        String userInputText = userInput.getText();
        Label userTextLabel = new Label(userInputText);
        userTextLabel.setPadding(new Insets(20, 10, 0, 0));
        userTextLabel.setStyle("-fx-text-fill: white;");

        ImageView userImage = new ImageView(userPhoto);
        userImage.setTranslateY(10);
        clipImageViewToCircle(userImage, 100);
        DialogBox userDialogBox = DialogBox.getUserDialog(userTextLabel, userImage);
        userDialogBox.setPadding(new Insets(0, 0, 30, 0));
        setDialogBoxBackgroundColor(userDialogBox, 70, 70, 70);

        dialogContainer.getChildren().add(
            userDialogBox
        );

        try {
            Parser.parse(userInputText, this, tasks, storage).execute();
        } catch (DukeException e) {
            this.showException(e);
        }


        userInput.clear();
    }

    private void displayDukeReply(String dukeReply) {
        Label dukeTextLabel = new Label(dukeReply);
        dukeTextLabel.setPadding(new Insets(20, 0, 0, 10));
        dukeTextLabel.setStyle("-fx-text-fill: white;");


        ImageView dukeImage = new ImageView(botPhoto);
        dukeImage.setTranslateY(10);

        clipImageViewToCircle(dukeImage, 100);

        DialogBox dukeDialogBox = DialogBox.getDukeDialog(dukeTextLabel, dukeImage);
        dukeDialogBox.setPadding(new Insets(0, 0, 30, 0));
        dukeDialogBox.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dukeDialogBox.setFillHeight(true);


        setDialogBoxBackgroundColor(dukeDialogBox, 200, 200, 200);

        dialogContainer.getChildren().add(dukeDialogBox);
    }

    private void displayDukeErrorReply(String errorReply) {
        Label dukeTextLabel = new Label(errorReply);

        dukeTextLabel.setPadding(new Insets(20, 0, 0, 10));
        dukeTextLabel.setStyle("-fx-text-fill: white;");


        ImageView dukeImage = new ImageView(botErrorPhoto);
        dukeImage.setTranslateY(10);

        clipImageViewToCircle(dukeImage, 100);

        DialogBox dukeDialogBox = DialogBox.getDukeDialog(dukeTextLabel, dukeImage);


        dukeDialogBox.setPadding(new Insets(0, 0, 30, 0));

        setDialogBoxBackgroundColor(dukeDialogBox, 191, 63, 63);

        dialogContainer.getChildren().add(dukeDialogBox);
    }

    private void clipImageViewToCircle(ImageView imageView, int length) {
        imageView.setPreserveRatio(false);
        imageView.setSmooth(true);
        Circle circle = new Circle(length / 2, length / 2, Math.min(length, length) / 2);
        imageView.setClip(circle);
    }

    private void setDialogBoxBackgroundColor(DialogBox dialogBox, int red, int green, int blue) {
        dialogBox.setBackground(
            new Background(new BackgroundFill(Color.rgb(red, green, blue, 0.75), new CornerRadii(35),
                null)));
    }


}
