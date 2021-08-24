import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class encapsulates a CLI bot named Duke.
 *
 * @author Teo Sin Yee
 * @version CS2103T AY21/22 Semester 1
 */
public class Duke {
    // Initialise message constants
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String BLANK_INPUT_MESSAGE = "Please enter something! ANYTHING!";
    private static final String BLANK_DESCRIPTION_MESSAGE = "OOPS!!! The description of %s cannot be empty! x_x";
    private static final String TODO_ERROR_MESSAGE = "Invalid use of 'todo' command!! @_@\n\tTo add a new todo, use 'todo <task>'.";
    private static final String DEADLINE_ERROR_MESSAGE = "Invalid use of 'deadline' command!! @_@\n\tTo add a new deadline, use 'deadline <task> /by <due-date>'.";
    private static final String EVENT_ERROR_MESSAGE = "Invalid use of 'event' command!! @_@\n\tTo add a new event, use 'event <title> /at <time-stamp>'.";
    private static final String DONE_ERROR_MESSAGE = "Invalid use of 'done' command!! @_@\n\tTo mark a task as done, use 'done <task-number>'.";
    private static final String INPUT_PROMPT = "Enter a command *_*";

    private Storage storage;
    private TaskHandler taskHandler;
    private final Ui ui;
    private final Parser parser;


    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage();
            taskHandler = new TaskHandler(storage.loadTasks(), storage);
        } catch (DukeException e) {
            ui.prettify(e.getMessage());
            taskHandler = new TaskHandler(new ArrayList<Task>(), storage);
        } finally {
            parser = new Parser(taskHandler, storage);
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
                Command c = parser.parse(input);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.prettify(e.getMessage());
            }
        }
    }


        public static void main (String[]args){
            Duke duke = new Duke();
            duke.start();
        }
    }
