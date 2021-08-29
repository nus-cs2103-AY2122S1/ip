package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.Task;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author Chng Zi Hao
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private final Parser parser;
    private final Ui ui;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        ui = new Ui();
        parser = new Parser();
        try {
            storage = new Storage();
            taskList = new TaskList(storage.retrieveData(), storage);
        } catch (DukeException e) {
            ui.formatPrint(e.getMessage());
            taskList = new TaskList(new ArrayList<Task>(), storage);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the Duke Program.
     */
    public void run() {
        ui.printWelcomeMessage();
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.printPrompt();
                String input = sc.nextLine();
                Command c = parser.parse(input);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.formatPrint(e.getMessage());
            }
        }
    }
}
