package duke;

import java.util.Scanner;

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

    public void run() {
        Scanner scan = new Scanner(System.in);
        Parser myParser = new Parser();

        Ui.showWelcomeMessage();

        boolean checker = true;
        while (checker) {
            checker = myParser.scanInputs(scan, tasks, true);
        }

        // Save the tasks in duke.txt
        storage.saveToFile(tasks.getTasks());
        Ui.showTasksSaved();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }



}
