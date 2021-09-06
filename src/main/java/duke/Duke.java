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
            nextLine = ui.nextLine();
            this.handleCommand(nextLine);
        } while (!parser.isBye(nextCommand));

    }

    /**
     * Parses and execute a command.
     *
     * @param command Command to be executed.
     * @return Response from the program.
     */
    public String handleCommand (String command) {
        try {
            Command nextCommand = parser.parseCommand(command);
            String[] arguments = parser.parseArguments(nextCommand, command);
            return execute(nextCommand, arguments);
        } catch (DukeException err) {
            return ui.printDukeException(err);
        }



    }
    /**
     * Execute the given Command with the arguments.
     *
     *
     * @param c Command to be executed
     * @param arguments Arguments of the Command
     */
    private String execute(Command c, String[] arguments) {
        try {
            String response;
            switch (c) {
            case TODO:
                tasks.addTask(new ToDo(arguments[0]));
                response = ui.printNewTask(tasks);
                break;
            case DEADLINE:
                tasks.addTask(new Deadline(arguments[0], parser.parseDate(arguments[1])));
                response =  ui.printNewTask(tasks);
                break;
            case EVENT:
                tasks.addTask(new Event(arguments[0], arguments[1]));
                response = ui.printNewTask(tasks);
                break;
            case DONE:
                int index = parser.parseInt(arguments[0]) - 1;
                tasks.completeTask(index);
                response =  ui.printDoneTask(tasks.get(index));
                break;
            case DELETE:
                tasks.deleteTask(parser.parseInt(arguments[0]) - 1);
                response =  ui.printDeleteTask(tasks.count());
                break;
            case LIST:
                response =  ui.printList(tasks);
                break;
            case FIND:
                ArrayList<Integer> indexes = tasks.find(arguments[0]);
                response =  ui.printSearchResult(indexes, tasks);
                break;
            case BYE:
                response =  ui.printGoodbye();
                ui.closeScanner();
                break;
            default :
                throw new DukeException("Invalid Command");
            }
            return response;
        } catch (DukeException err) {
            return ui.printDukeException(err);
        }
    }
}

