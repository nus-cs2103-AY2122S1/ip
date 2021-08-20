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
            tasks = storage.readTasksFromData(ui);
        } catch (DukeException dukeException) {
            System.out.println(dukeException);

            // If failed to read tasks from save, initialize a new Task ArrayList.
            tasks = new TaskList(ui);
        }
    }

    public void run() {
        // Initialize scanner object.
        Scanner sc = new Scanner(System.in);

        // Prints greeting to user.
        this.ui.showWelcome();

        // Scans user inputs and prints corresponding outputs until a "Bye" input is received.
        String userInput = sc.nextLine();
        while (!userInput.equals(Ui.Commands.BYE.getCommand())) {
            handleUserInput(userInput);
            userInput = sc.nextLine();
        }

        // Prints goodbye message to user.
        this.ui.showGoodbye();

        // Closes scanner object.
        sc.close();
    }

    private void handleUserInput(String userInput) {
        if (userInput.equals(Ui.Commands.LIST.getCommand())) {
            // Print tasks
            this.tasks.printTasks();
        } else {
            // Catches thrown DukeException if any.
            try {
                if (userInput.startsWith(Ui.Commands.DONE.getCommand())) {
                    // Mark task as done.
                    this.tasks.markTask(userInput);
                } else if (userInput.startsWith(Ui.Commands.DELETE.getCommand())) {
                    // Delete a task.
                    this.tasks.deleteTask(userInput);
                } else if (userInput.startsWith(Ui.Commands.DATE.getCommand())) {
                    // Print tasks that fall on given date.
                    this.tasks.printTaskAtDate(userInput);
                } else {
                    // Add a task to tasks.
                    this.tasks.addTask(userInput, '/');
                }

                // Save tasks to save file after each change.
                this.storage.saveTasksToData(this.tasks);
            } catch (DukeException dukeException) {
                System.out.println(dukeException);
            }
        }
    }

    /**
     * The main method is runs the Duke chatbot.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke(SAVE_FILENAME);
        duke.run();
    }

}
