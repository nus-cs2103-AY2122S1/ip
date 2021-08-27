package duke;

import java.util.Scanner;

import duke.Ui.Commands;
import duke.command.Command;

/**
 * Handles initialization of storage and tasks and running of Duke chatbot.
 */
public class Duke {
    private static final String SAVE_FILENAME = "dukeSave.txt";
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for Duke.
     * Creates a Duke with a Storage and an Ui.
     * Storage will read tasks from specified file to TaskList.
     * If there are errors reading file, TaskList will be empty by default.
     *
     * @param fileName Filename that Storage will save tasks to and read tasks from.
     */
    public Duke(String fileName) {
        this.storage = new Storage(fileName);
        this.ui = new Ui();

        // Read tasks from save file.
        try {
            tasks = this.storage.readTasksFromData();
        } catch (DukeException dukeException) {
            System.out.println(dukeException);

            // If failed to read tasks from save, initialize a new duke.task.Task ArrayList.
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke chatbot.
     * Takes in user input and prints the corresponding output to the user.
     * Also manages the user's tasks by saving to and reading from a specified file.
     */
    public void run() {
        // Initialize scanner object.
        Scanner sc = new Scanner(System.in);

        // Prints greeting to user.
        this.ui.showWelcome();

        // Scans user inputs and prints corresponding outputs until a "Bye" input is received.
        String userInput = sc.nextLine();
        // Only exactly "bye" is read as exit command.
        while (!userInput.equals(Commands.BYE.getCommand())) {
            Command command = Parser.parse(userInput);
            command.execute(this.tasks, this.ui, this.storage);
            userInput = sc.nextLine();
        }

        // Prints goodbye message to user.
        this.ui.showGoodbye();

        // Closes scanner object.
        sc.close();
    }

    /**
     * The main method runs the duke.Duke chatbot.
     *
     * @param args Commandline arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke(SAVE_FILENAME);
        duke.run();
    }

}
