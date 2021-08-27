package duke;

import java.util.Scanner;

import duke.exception.DukeException;
import duke.io.Parser;
import duke.io.Storage;
import duke.io.TextColor;
import duke.io.Ui;
import duke.task.TaskList;


public class Duke {
    private static TaskList taskList = new TaskList();
    private static Parser parser = new Parser();
    private static Storage storage = new Storage("save.csv");
    private static Ui ui = new Ui();

    public static TaskList getTaskList() {
        return taskList;
    }

    public static void setTaskList(TaskList taskList) {
        Duke.taskList = taskList;
    }

    public static Parser getParser() {
        return parser;
    }

    public static Storage getStorage() {
        return storage;
    }

    public static Ui getUi() {
        return ui;
    }

    /**
     * The main logic of the program. Handles initialising and user input.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        init();

        while (sc.hasNextLine()) {
            try {
                String msg = sc.nextLine();
                parser.parse(msg);
            } catch (DukeException dukeException) {
                dukeException.displayError();
            }
            // print all queued messages
            ui.print();
            ui.prompt();
        }
    }

    private static void init() {
        if (storage.load()) {
            ui.greetReturningUser();
        } else {
            ui.greetNewUser();
        }
    }

    /**
     * Prints a goodbye message and exits the application.
     */
    public static void exit() {
        ui.addMessage("Bye. Hope to see you again soon!", TextColor.DEFAULT);
        ui.print();
        System.exit(0);
    }
}
