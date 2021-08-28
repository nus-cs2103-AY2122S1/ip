package duke;

import java.util.Scanner;

/**
 * Main class. Starts the program.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor.
     *
     * @param filePath filepath to load tasks from
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Method to start running the program and start taking user input.
     */
    public void run() {
        Scanner scan = new Scanner(System.in);
        Parser myParser = new Parser();

        Ui.welcomeMessage();

        boolean checker = true;
        while (checker) {
            checker = myParser.scanInputs(scan, tasks, true);
        }

        // Save the tasks in duke.txt
        storage.saveToFile(tasks.getTasks());
        Ui.showTasksSaved();
    }

    /**
     * Main method, pretty self-explanatory.
     * @param args arguments
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }



}
