package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Duke extends Application {

    private StorageList SL;
    private static Ui ui;
    private Parser parser;
    private Storage storage;

    private static final int TASK_TODO = 1;
    private static final int TASK_DEADLINE = 2;
    private static final int TASK_EVENT = 3;

    private static final int ERROR_OUTOFBOUNDS = 4;
    private static final int ERROR_UNKNOWN = 5;

    private static final int TEXT_FONTSIZE = 20;

    private static final String INSTRUCTIONS = "To add a todo task  --  todo <task>\n"
            + "To add a deadline  --  deadline <task> /by MMM dd yyyy HH:mm\n"
            + "To add an event  --  event <task> /at MMM dd yyyy HH:mm\n"
            + "To mark tasks as done  --  done <idx from list>\n"
            + "To delete tasks  --  delete <idx from list>\n"
            + "To search for task  --  find <keyword>\n"
            + "To see all contents of list  --  list";

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

        TextField cmdInput = new TextField();

        Button enterButton = new Button("Enter");
        Button instructionsButton = new Button("Instructions");

        Image dukeImage = new Image("https://tinyurl.com/p6yft4bu");
        ImageView imageView = new ImageView(dukeImage);
        imageView.setFitHeight(300);
        imageView.setFitWidth(400);

        Label labelForImgAndText = new Label("Hi, I'm Duke! How can i help you?", imageView);
        labelForImgAndText.setMinSize(1200.0, 800.0);
        labelForImgAndText.setFont(new Font(TEXT_FONTSIZE));

        VBox layout = new VBox(30);
        layout.setPadding(new Insets(20, 20, 20, 20));

        try {
            Duke duke = new Duke("data/duketest.txt");
            enterButton.setOnAction(e -> {
                labelForImgAndText.setText(duke.run(cmdInput.getText()));
                cmdInput.clear();
            });
            instructionsButton.setOnAction(e -> {
                labelForImgAndText.setText(INSTRUCTIONS);
            });
        } catch (FileNotFoundException e){
            labelForImgAndText.setText(ui.fileNotFoundMsg());
        }

        layout.getChildren().addAll(labelForImgAndText, cmdInput, enterButton, instructionsButton);
        Scene scene = new Scene(layout, 1200, 1000);
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
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
                marking(input);
                storage.save(SL);
                return ui.taskDoneConfirmation();

            } else if (parser.isValidTodo(input)) {
                if(input.length() == 4) {
                    throw new DukeException(ui.taskErrorMsg(TASK_TODO));
                }
                ToDo todo = new ToDo(parser.getTodoDescription(input));
                SL.addTask(todo);
                storage.save(SL);
                return ui.taskAddedMsg(todo.toString(), SL.size());


            } else if (parser.isValidDeadline(input)) {
                if (input.length() == 8) {
                    throw new DukeException(ui.taskErrorMsg(TASK_DEADLINE));
                }
                String by = parser.getDeadlineTime(input);
                Deadline dl = new Deadline(parser.getDeadlineDescription(input), by);
                SL.addTask(dl);
                storage.save(SL);
                return ui.taskAddedMsg(dl.toString(), SL.size());

            } else if (parser.isValidEvent(input)) {
                if (input.length() == 5) {
                    throw new DukeException(ui.taskErrorMsg(TASK_EVENT));
                }
                String at = parser.getEventTime(input);
                Event event = new Event(parser.getEventDescription(input), at);
                SL.addTask(event);
                storage.save(SL);
                return ui.taskAddedMsg(event.toString(), SL.size());

            } else if (parser.isDeleteCmd(input)) {
                if (input.length() == 6) {
                    throw new DukeException(ui.taskErrorMsg(ERROR_UNKNOWN));
                }
                int idx = parser.getDeleteIdx(input);
                String desc = SL.get(idx).getDescription();
                SL.delete(idx);
                storage.save(SL);
                return ui.taskDeleteMsg(desc, SL.size());

            } else if (parser.isFindCmd(input)) {
                if (input.length() == 4) {
                    throw new DukeException(ui.taskErrorMsg(ERROR_UNKNOWN));
                }
                String keyword = input.substring(5);
                storage.save(SL);
                return SL.findAndReturn(keyword);

            } else {
                switch (input) {
                case "bye":
                    ui.bye();
                    return ui.bye();
                case "list":
                    return ui.displayListContents(SL);
                default:
                    throw new DukeException(ui.taskErrorMsg(ERROR_UNKNOWN));
                }
            }

        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return ui.ioErrorMsg();
        }
    }


    /**
     * Marks tasks as done, and prints out relevant messages.
     *
     * @param input The user input to be processed.
     * @throws DukeException If task index to be marked is not valid.
     */
    public void marking(String input) throws DukeException {
        if (input.length() >= 6) {
            if (parser.isIntegerToBeMarked(input)) {
                int taskNum = Integer.parseInt(input.substring(5)) - 1;
                if (taskNum < SL.size() && taskNum >= 0) {
                    SL.get(taskNum).markAsDone();
                    ui.taskDoneConfirmation();
                    SL.get(taskNum).displayTask();
                } else {
                    throw new DukeException(ui.taskErrorMsg(ERROR_OUTOFBOUNDS));
                }
            } else {
                throw new DukeException(ui.taskErrorMsg(ERROR_UNKNOWN));
            }
        } else {
            throw new DukeException(ui.taskErrorMsg(ERROR_UNKNOWN));
        }
    }
}
