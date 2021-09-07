package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke extends Application {

    enum TaskType{
        TODO,
        EVENT,
        DEADLINE,
    }

    private StorageList SL;
    private static Ui ui;
    private Parser parser;
    private Storage storage;

    private static final int TASK_TODO = 1;
    private static final int TASK_DEADLINE = 2;
    private static final int TASK_EVENT = 3;

    private static final int ERROR_OUTOFBOUNDS = 4;
    private static final int ERROR_UNKNOWN = 5;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/spongebob.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));

    public Duke() {}

    /**
     * Constructor to initialise variables.
     *
     * @param filePath File path to receive input from and write to.
     * @throws FileNotFoundException If file is not found.
     */
    public Duke(String filePath) throws FileNotFoundException {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        SL = new StorageList(storage.load());
    }


    public static void main(String[] args) {
    }

    @Override
    public void start(Stage stage) {

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        dialogContainer.setPadding(new Insets(20, 20, 20, 20));
        dialogContainer.setSpacing(10);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(700.0);
        stage.setMinWidth(800.0);

        mainLayout.setPrefSize(800.0, 700.0);

        scrollPane.setPrefSize(785, 635);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(725.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }


    private void handleUserInput() {
        assert user != null && duke != null : "User and duke images should not be null";
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    String getResponse(String input) {
        try {
            return new Duke("data/duketest.txt").run(input);
        } catch (FileNotFoundException e) {
            return ui.fileNotFoundMsg();
        }

    }

    /**
     * Executes the program after reading input from file or user.
     *
     * @param input The input given to the program.
     * @return The confirmation message depending on what the user inputted.
     */
    private String run(String input) {

        try {
            if (parser.isDoneCmd(input)) {
                return marking(input);

            } else if (parser.isValidTodo(input)) {
                if(input.length() == 4) {
                    throw new DukeException(ui.taskErrorMsg(TASK_TODO));
                }
                return putTaskInList(TaskType.TODO, input);


            } else if (parser.isValidDeadline(input)) {
                if (input.length() == 8) {
                    throw new DukeException(ui.taskErrorMsg(TASK_DEADLINE));
                }

                return putTaskInList(TaskType.DEADLINE, input);


            } else if (parser.isValidEvent(input)) {
                if (input.length() == 5) {
                    throw new DukeException(ui.taskErrorMsg(TASK_EVENT));
                }
                return putTaskInList(TaskType.EVENT, input);


            } else if (parser.isDeleteCmd(input)) {
                if (input.length() == 6) {
                    throw new DukeException(ui.taskErrorMsg(ERROR_UNKNOWN));
                }
                assert input.substring(8).length() == 1 : "There should be an index";

                int idx = parser.getDeleteIdx(input);
                String desc = SL.get(idx).getDescription();
                SL.delete(idx);
                storage.save(SL);
                return ui.taskDeleteMsg(desc, SL.size());

            } else if (parser.isFindCmd(input)) {
                if (input.length() == 4) {
                    throw new DukeException(ui.taskErrorMsg(ERROR_UNKNOWN));
                }
                assert input.substring(6).length() == 1 : "There should be an index";

                String keyword = input.substring(5);
                return SL.findAndReturn(keyword);

            } else if(parser.isViewCmd(input)) {
                if (input.length() == 4) {
                    throw new DukeException(ui.taskErrorMsg(ERROR_UNKNOWN));
                }
                String dateQuery = parser.getDateTime(input);
                return SL.viewSchedule(dateQuery);
            } else {

                switch (input) {
                case "bye":
                    ui.bye();
                    return ui.bye();
                case "list":
                    return ui.displayListContents(SL);
                default:
                    assert false : input + "is not a valid command";
                    throw new DukeException(ui.taskErrorMsg(ERROR_UNKNOWN));
                }
            }

        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return ui.ioErrorMsg();
        }
    }

    private String putTaskInList(TaskType taskType, String input) throws IOException {
        Task task = null;
        switch (taskType) {
        case TODO:
            task = new ToDo(parser.getTodoDescription(input));
            break;
        case EVENT:
            String at = parser.getEventTime(input);
            task = new Event(parser.getEventDescription(input), at);
            break;
        case DEADLINE:
            String by = parser.getDeadlineTime(input);
            task = new Deadline(parser.getDeadlineDescription(input), by);
            break;
        default:
            // code should not come into here
            break;
        }
        SL.addTask(task);
        storage.save(SL);
        return ui.taskAddedMsg(task.toString(), SL.size());
    }



    private String marking(String input) throws DukeException, IOException {
        if (input.length() >= 6 && input.substring(5).matches("[0-9]+")) {
            int taskNum = parser.getDoneIdx(input);
            if (parser.hasValidInteger(taskNum, SL.size())) {
                SL.get(taskNum).markAsDone();
                ui.taskDoneConfirmation();
                storage.save(SL);
                return ui.taskDoneConfirmation();
            } else {
                throw new DukeException(ui.taskErrorMsg(ERROR_OUTOFBOUNDS));

            }
        }
        throw new DukeException(ui.taskErrorMsg(ERROR_UNKNOWN));

    }
}
