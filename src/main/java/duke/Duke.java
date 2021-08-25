package duke;

import java.util.ArrayList;

/**
 * Todo App.
 */

public class Duke {
    public static TaskList tasks;
    public static Storage storage;
    public static Ui ui;
    public static Parser parser;

    /**
     * Constructor.
     *
     * @param filePath Path of the save file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = storage.load();
            ui.printLoadSuccess();
        } catch (DukeException err) {
            ui.printDukeException(err);
            tasks = new TaskList();
        }
    }

    /**
     * Starts the ToDo app.
     *
     * @param args duke.Command line arguments
     */
    public static void main(String[] args) {
        new Duke("data/save.txt").run();
    }

    private void run() {
        ui.printWelcome();

        // Take user input
        String nextLine;
        Command nextCommand = Command.INVALID;
        do {
            try {
                nextLine = ui.nextLine();
                nextCommand = parser.parseCommand(nextLine);
                String[] arguments = parser.parseArguments(nextCommand, nextLine);
                execute(nextCommand, arguments);
            } catch (DukeException e) {
                ui.printDukeException(e);
            }
        } while (!parser.isBye(nextCommand));

    }

    /**
     * Execute the given Command with the arguments.
     *
     * @param c Command to be executed
     * @param arguments Arguments of the Command
     * @throws DukeException
     */
    private void execute(Command c, String[] arguments) throws DukeException {
        switch (c) {
        case TODO:
            tasks.addTask(new ToDo(arguments[0]));
            ui.printNewTask(tasks);
            break;
        case DEADLINE:
            tasks.addTask(new Deadline(arguments[0], parser.parseDate(arguments[1])));
            ui.printNewTask(tasks);
            break;
        case EVENT:
            tasks.addTask(new Event(arguments[0], arguments[1]));
            ui.printNewTask(tasks);
            break;
        case DONE:
            int index = parser.parseInt(arguments[0]) - 1;
            tasks.completeTask(index);
            ui.printDoneTask(tasks.get(index));
            break;
        case DELETE:
            tasks.deleteTask(parser.parseInt(arguments[0]) - 1);
            ui.printDeleteTask(tasks.count());
            break;
        case LIST:
            ui.printList(tasks);
            break;
        case FIND:
            ArrayList<Integer> indexes = tasks.find(arguments[0]);
            ui.printSearchResult(indexes, tasks);
            break;
        case BYE:
            ui.printGoodbye();
            ui.closeScanner();
            break;
        }
    }
}

