package duke;

import java.util.Scanner;

public class Duke {
    /** Encapsulates the storage of tasks in a file within the user's computer for continuity across Duke sessions. */
    private Storage storage;
    /**
     * Stores the tasks when the Duke program runs.
     * Allows one to perform actions on those tasks like add and delete.
     */
    private TaskList tasks;
    /** Handles the UI aspect of the Duke program, relaying messages to the user. */
    private Ui ui;

    /**
     * Constructs an instance of the Duke program.
     * If there is no file at the specified filePath, a new file will be created;
     * else, the tasks in the file will be read and saved.
     *
     * @param filePath The file path of the file containing the list of tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printResponse(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke's main program.
     * Main logic of the program, which will continue to accept user input until user enters 'bye'.
     */
    public void run() {
        ui.printResponse("Hello! I'm Duke", "What can I do for you?");
        Parser parser = new Parser();
        Scanner scan = new Scanner(System.in);
        boolean active = true;
        while (active) {
            String command = scan.nextLine();
            try {
                active = parser.parse(command, tasks, ui);
            } catch (DukeException e) {
                ui.printResponse(e.getMessage());
            }
            try {
                storage.rewriteData(tasks.convertToSaveFormat());
            } catch (DukeException e) {
                ui.printResponse(e.getMessage());
            }
        }
    }

    /**
     * Creates a new instance of Duke.
     * Instantiates the Duke instance with a file path and runs it.
     *
     * @param args String arguments if needed.
     */
    public static void main(String[] args) {
        new Duke("dukedata.txt").run();
    }
}
