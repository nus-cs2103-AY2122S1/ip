package duke;

/**
 * The Duke chat bot app.
 */
public class Duke {

    /** Duke UI */
    private final Ui ui;

    /** Hard disk storage */
    private final Storage storage;

    /** User input parser */
    private final Parser parser;

    /** The list of tasks */
    private TaskList list;


    /**
     * Duke class constructor.
     * @param directory Relative path to directory of the saved data.
     * @param file Name of the saved data file.
     */
    public Duke(String directory, String file) {
        ui = new Ui();
        storage = new Storage(directory, file);
        parser = new Parser();
        try {
            list = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showMessage(e.getMessage());
            list = new TaskList();
        }
    }

    /**
     * Entry point of the Duke program.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke("./data", "duke.txt").run();
    }


    /**
     * Displays the Duke UI and process user input commands.
     */
    public void run() {

        // Show Greetings.
        ui.showGreetings();

        // Get and process input.
        String rawInput = ui.getInput();
        String output = "";
        label:
        while (true) {
            try {
                // Parses user input.
                String[] inputs = parser.parseInput(rawInput);
                Constant.Command command = Constant.Command.valueOf(inputs[0]);
                String task;

                // Process user input.
                switch (command) {
                case HELP:
                    // Gets user manual.
                    output = ui.getHelpMenu();

                    break;
                case BYE:
                    // Quits program.
                    break label;
                case LIST:
                    // Gets the string represented tasks in the task list.
                    output = list.getAllTask();

                    break;
                case DONE:
                    // Marks a task as being completed.
                    int index = parser.convertToInt(inputs[1]);
                    output = list.markDone(index);

                    // Edits the file content.
                    task = storage.getFileLine(index - 1);
                    task = task.substring(0, 4) + "1" + task.substring(5);
                    storage.updateLineFile(index - 1, task);

                    break;
                case TODO:
                    // Adds a todo-typed task to the task list.
                    output = list.addItem(new Todo(inputs[1]));

                    // Add to file content.
                    task = "T | 0 | " + inputs[1];
                    storage.addToFile(task);

                    break;
                case DEADLINE:
                    // Adds a deadline-typed task in the task list.
                    output = list.addItem(new Deadline(inputs[1], inputs[2]));

                    // Add to file content.
                    task = "D | 0 | " + inputs[1] + " | " + inputs[2];
                    storage.addToFile(task);

                    break;
                case EVENT:
                    // Adds an event-typed task in the task list.
                    output = list.addItem(new Event(inputs[1], inputs[2]));

                    // Add to file content.
                    task = "E | 0 | " + inputs[1] + " | " + inputs[2];
                    storage.addToFile(task);

                    break;
                case DELETE:
                    // Deletes a task from the task list.
                    int id = parser.convertToInt(inputs[1]);
                    output = list.removeItem(id);

                    // Remove from file content.
                    storage.removeFromFile(id - 1);

                    break;
                case DATES:
                    // Gets the accepted date types.
                    output = ui.getAllAcceptedDates();

                    break;
                case FIND:
                    // Gets the task matching the queried keyword.
                    output = list.find(inputs[1]);
                }

                // Displays the output message.
                ui.showMessage(output);
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            } catch (Exception e) {
                ui.showMessage(e.getMessage());
                return;
            }

            // Gets user input
            rawInput = ui.getInput();
        }
        // Good bye message
        ui.showGoodBye();
    }
}
