import java.util.Scanner;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs Duke objects
     * @param filePath file path where Duke reads stored data from
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.showLoadingError(e.toString());
            taskList = new TaskList();
        }
    }

    /**
     * Runs program.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showGreeting();

        ui.readInput(taskList);

        try {
            storage.saveData(taskList);
        } catch (DukeException e) {
            System.out.println(e.toString());
        }

    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
