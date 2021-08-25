package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a chat bot.
 *
 * @author Willy Angga Prawira.
 */
public class Duke {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * A constructor to create a Duke object.
     *
     * @param filepath Location of the file that keeps track of the list.
     */
    Duke(String filepath) {
        taskList = new TaskList(new ArrayList<Task>());
        storage = new Storage(filepath);
        ui = new Ui();
    }

    /**
     * Runs the whole program.
     */
    public void run() {
        Scanner scan = new Scanner(System.in);
        storage.readFile(taskList);
        ui.printWelcome();
        new Parser(scan, storage, taskList, ui).parse();
        ui.printBye();
    }

    /**
     * Calls the run() method.
     *
     * @param args An array of Strings.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }
}
