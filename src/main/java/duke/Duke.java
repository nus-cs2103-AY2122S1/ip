package duke;

import duke.util.*;
import duke.command.*;
import duke.exception.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class encapsulates a CLI bot named Duke.
 *
 * @author Teo Sin Yee
 * @version CS2103T AY21/22 Semester 1
 */
public class Duke {
    private Storage storage;
    private TaskHandler taskHandler;
    private final Ui ui;
    private final Parser parser;

    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage();
            taskHandler = new TaskHandler(storage.loadTasks());
        } catch (DukeException e) {
            ui.prettify(e.getMessage());
            taskHandler = new TaskHandler(new ArrayList<>());
        } finally {
            parser = new Parser();
        }
    }

    /**
     * Runs Duke
     **/
    public void start() {
        // Welcome message
        ui.printIntroMessage();
        Scanner sc = new Scanner(System.in);
        storage = new Storage();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.prompt();
                String input = sc.nextLine();
                Command c = parser.parseRawInput(input);
                c.execute(taskHandler, storage, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.prettify(e.getMessage());
            }
        }
    }

    public static void main (String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}