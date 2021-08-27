package duke;

import java.util.Scanner;

import duke.task.TaskList;
import duke.io.Parser;
import duke.io.Storage;
import duke.io.Ui;
import duke.io.TextColor;
import duke.exception.DukeException;

public class Duke {
    public static TaskList taskList = new TaskList();
    public static Parser parser = new Parser();
    public static Storage storage = new Storage("save.csv");
    public static Ui ui = new Ui();

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
