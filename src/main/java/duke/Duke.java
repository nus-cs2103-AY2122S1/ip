package duke;

/**
 * Handles receiving input from user and starting/stopping the program.
 */
public class Duke {

    private static final String FILEPATH = "data/duke.txt";

    private static final int FIND_INPUT_START_INDEX = 5;
    private static final int DELETE_INPUT_START_INDEX = 7;
    private static final int DONE_INPUT_START_INDEX = 5;
    private static final int TODO_INPUT_START_INDEX = 5;
    private static final int DEADLINE_INPUT_START_INDEX = 9;
    private static final int EVENT_INPUT_START_INDEX = 6;

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates Ui and Storage instances.
     * Loads data from fisk into TaskList object to create list of tasks user currently has.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILEPATH);
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

    /**
     * Parses user's input and returns the appropriate message.
     *
     * @param userInput is user's input that they enter into program.
     * @return appropriate message based on user's input.
     */
    public String run(String userInput) {
        try {
            String fullCommand = userInput.trim();
            CommandType commandType = Parser.parse(fullCommand);

            switch (commandType) {
            case LIST:
                return ui.showTasks(tasks.getTaskList());
            case BYE:
                return ui.showGoodbye();
            case HELP:
                return ui.showHelpMessage();
            case FIND:
                return tasks.find(fullCommand.substring(FIND_INPUT_START_INDEX), ui);
            case DELETE:
                return tasks.delete(fullCommand.substring(DELETE_INPUT_START_INDEX), storage, ui);
            case DONE:
                return tasks.done(fullCommand.substring(DONE_INPUT_START_INDEX), storage, ui);
            case TODO:
                return tasks.createTodo(fullCommand.substring(TODO_INPUT_START_INDEX), storage, ui);
            case DEADLINE:
                return tasks.createDeadline(fullCommand.substring(DEADLINE_INPUT_START_INDEX), storage, ui);
            case EVENT:
                return tasks.createEvent(fullCommand.substring(EVENT_INPUT_START_INDEX), storage, ui);
            default:
                throw new DukeException("Invalid command");
            }
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }

    }
}



