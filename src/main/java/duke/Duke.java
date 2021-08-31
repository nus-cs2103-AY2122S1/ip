package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
        Button button = new Button("Enter");
        Text text = new Text("Hi, I'm Duke!");

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20, 20, 20, 20));

        try {
            Duke duke = new Duke("data/duketest.txt");
            button.setOnAction(e -> {
                text.setText(duke.run(cmdInput.getText()));
                cmdInput.clear();
            });
        } catch (FileNotFoundException e){
            text.setText(ui.fileNotFoundMsg());
        }

        layout.getChildren().addAll(cmdInput, button, text);
        Scene scene = new Scene(layout, 600, 500);
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

            } else if (parser.isValidTodo(input)) {
                if(input.length() == 4) {
                    throw new DukeException(ui.taskErrorMsg(TASK_TODO));
                }
                ToDo todo = new ToDo(parser.getTodoDescription(input));
                SL.addTask(todo);
                return ui.taskAddedMsg(todo.toString(), SL.size());


            } else if (parser.isValidDeadline(input)) {
                if (input.length() == 8) {
                    throw new DukeException(ui.taskErrorMsg(TASK_DEADLINE));
                }
                String by = parser.getDeadlineTime(input);
                Deadline dl = new Deadline(parser.getDeadlineDescription(input), by);
                SL.addTask(dl);
                return ui.taskAddedMsg(dl.toString(), SL.size());

            } else if (parser.isValidEvent(input)) {
                if (input.length() == 5) {
                    throw new DukeException(ui.taskErrorMsg(TASK_EVENT));
                }
                String at = parser.getEventTime(input);
                Event event = new Event(parser.getEventDescription(input), at);
                SL.addTask(event);
                return ui.taskAddedMsg(event.toString(), SL.size());

            } else if (parser.isDeleteCmd(input)) {
                if (input.length() == 6) {
                    throw new DukeException(ui.taskErrorMsg(ERROR_UNKNOWN));
                }
                int idx = parser.getDeleteIdx(input);
                String desc = SL.get(idx).getDescription();
                SL.delete(idx);
                return ui.taskDeleteMsg(desc, SL.size());
            } else if (parser.isFindCmd(input)) {
                if (input.length() == 4) {
                    throw new DukeException(ui.taskErrorMsg(ERROR_UNKNOWN));
                }
                String keyword = input.substring(5);
                return SL.findAndReturn(keyword);
            } else {
                Task task = new Task(input);
                String desc = task.getDescription();
                switch (desc) {
                case "bye":
                    ui.bye();
                    return ui.bye();
                case "list":
                    return ui.displayListContents(SL);
                default:
                    throw new DukeException(ui.taskErrorMsg(ERROR_UNKNOWN));
                }
            }
            storage.save(SL);
            return "";
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.println(ui.ioErrorMsg());
        }
        return ui.taskErrorMsg(5);
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
