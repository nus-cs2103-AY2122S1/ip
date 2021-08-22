package duke;

import duke.Ui.Commands;
import duke.command.Command;

import java.util.Scanner;

/**
 * Handles initialization of storage and tasks and running of chatbot.
 */
public class Duke {
    private static final String SAVE_FILENAME = "dukeSave.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String fileName) {
        storage = new Storage(fileName);
        ui = new Ui();

        // Read tasks from save file.
        try {
            tasks = storage.readTasksFromData();
        } catch (DukeException dukeException) {
            System.out.println(dukeException);

            // If failed to read tasks from save, initialize a new duke.task.Task ArrayList.
            tasks = new TaskList();
        }
    }

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
     * The main method is runs the duke.Duke chatbot.
     *
     * @param args duke.command.Command line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke(SAVE_FILENAME);
        duke.run();
    }

}
