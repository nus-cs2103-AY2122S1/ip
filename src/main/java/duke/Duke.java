package duke;

/**
 * Handles receiving input from user and starting/stopping the program.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static String FILE_PATH = "data/duke.txt";

    /**
     * Creates Ui and Storage instances.
     * Loads data from fisk into TaskList object to create list of tasks user currently has.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = new TaskList(storage.load());
    }
    
    public static void main(String[] args) {
        new Duke();
    }

    /**
     * Retrieves the response that should be displayed to user based on user's input.
     */
    public String getResponse(String input) {
        return run(input);
    }

    public String run(String userInput) {
        try {
            String fullCommand = userInput.trim();
            CommandType commandType = Parser.parse(fullCommand);

            switch (commandType) {
            case LIST:
                return ui.showTasks(tasks.getTaskList());
            case BYE:
                return ui.printGoodbye();
            case FIND:
                return tasks.find(fullCommand.substring(5), ui);
            case DELETE:
                return tasks.delete(fullCommand.substring(7), storage, ui);
            case DONE:
                return tasks.done(fullCommand.substring(5), storage, ui);
            case TODO:
                return tasks.createTodo(fullCommand.substring(5), storage, ui);
            case DEADLINE:
                return tasks.createDeadline(fullCommand.substring(9), storage, ui);
            case EVENT:
                return tasks.createEvent(fullCommand.substring(6), storage, ui);
            default:
                throw new DukeException("Invalid command");
            }
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }

    }
}


