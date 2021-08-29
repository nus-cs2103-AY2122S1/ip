package duke;

import java.util.Scanner;
import duke.storage.Storage;
import duke.ui.TextUi;


/**
 * Represents a personalised chat bot for CS2103/T iP.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * A constructor for a Duke chat bot.
     *
     * @param filePath The path of the file the task list is saved in.
     */
    public Duke(String filePath) {
        tasks = new TaskList();
        storage = new Storage(filePath, tasks);
        try {
            storage.readFile();
        } catch (DukeException e) {
            TextUi.showErrorMessage(e.getMessage());
        }

    }

    /**
     * Runs the programme.
     */
    public void run() {
        TextUi.showWelcomeMessage();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            try {
                String text = sc.nextLine();
                if (text.equals("q")) {
                    TextUi.showGoodbyeMessage();
                    break;
                } else if (text.equals("ls")) {
                    tasks.printList();
                } else if (text.startsWith("done")) {
                    tasks.markAsDone(text);
                    storage.copyToFile();
                } else if (text.startsWith("delete")) {
                    tasks.deleteTask(text);
                    storage.copyToFile();
                } else if (text.startsWith("find")) {
                    tasks.findTask(text);
                } else {
                    tasks.addTask(text);
                    storage.copyToFile();
                    TextUi.showTaskAdded(tasks);
                    TextUi.showUpdatedNumberOfTasks(tasks);
                }
            } catch (DukeException e) {
                TextUi.showErrorMessage(e.getMessage());
            }
        }
        sc.close();
    }

    /**
     * The main method of Nat's chat bot.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }


}
