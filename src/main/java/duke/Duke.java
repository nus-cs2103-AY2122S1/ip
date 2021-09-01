package duke;

import java.util.ArrayList;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskHandler;
import duke.util.Ui;

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

    /** Instantiates Duke **/
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

//    public static void main (String[] args) {
//        Duke duke = new Duke();
//        duke.start();
//    }

    /** Runs Duke **/
//    public void start() {
//        ui.printIntroMessage();
//        Scanner sc = new Scanner(System.in);
//        storage = new Storage();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                ui.prompt();
//                String input = sc.nextLine();
//                Command c = parser.parseRawInput(input);
//                c.execute(taskHandler, storage, ui);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.prettify(e.getMessage());
//            }
//        }
//    }
    public String getResponse(String input) {
        try {
            Command c = parser.parseRawInput(input);
            return c.execute(taskHandler, storage, ui);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
