package duke;

import java.util.Scanner;

import duke.components.Parser;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import javafx.application.Platform;

/**
 * Duke is an application that can store Tasks for users.
 * Users can store three types of tasks, Deadline, Event, and Todo.
 * They can also mark a task as completed and view all tasks they have.
 */
public class Duke {

    private static final String FILE_PATH = "./data/data.txt";
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;

    /**
     * Creates a Duke object.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        this.parser = new Parser();
        this.taskList = new TaskList(this.storage, this.ui, this.parser);
        storage.loadInto(taskList);
    }

    /**
     * Activates and runs the Duke application.
     */
    public void run() {
        storage.loadInto(taskList);
        ui.displayWelcomeMessage();
        Scanner myObj = new Scanner(System.in);

        // String input
        while (true) {
            String input = myObj.nextLine();

            if (parser.isMarkDoneCommand(input)) {
                taskList.markDone(input);
                continue;
            }

            if (parser.isDeleteTaskCommand(input)) {
                taskList.deleteTask(input);
                continue;
            }

            if (!parser.isEnd(input) && !parser.isDisplay(input) && !parser.isFindTask(input)) {
                taskList.addTaskFromInput(input);
                continue;
            }

            if (parser.isDisplay(input)) {
                taskList.displayAllTasks();
                continue;
            }

            if (parser.isFindTask(input)) {
                taskList.displayFindTasks(input);
                continue;
            }

            if (parser.isEnd(input)) {
                ui.displayExitMessage();
                break;
            }
            ui.displayOtherInputsMessage();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {

        if (parser.isMarkDoneCommand(input)) {
            return taskList.markDone(input);
        }

        if (parser.isDeleteTaskCommand(input)) {
            return taskList.deleteTask(input);
        }

        if (!parser.isEnd(input) && !parser.isDisplay(input) && !parser.isFindTask(input)) {
            return taskList.addTaskFromInput(input);
        }

        if (parser.isDisplay(input)) {
            return taskList.displayAllTasks();
        }

        if (parser.isFindTask(input)) {
            return taskList.displayFindTasks(input);
        }

        if (parser.isEnd(input)) {
            Platform.exit();
        }
        return ui.displayOtherInputsMessage();

    }
}
