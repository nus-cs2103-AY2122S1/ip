package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Project Duke
 *
 * @author Willy Angga Prawira
 */

public class Duke {

    private TaskList taskList;
    private Storage storage;
    private Parser parser;
    private Ui ui;

    Duke(String filepath) {
        taskList = new TaskList(new ArrayList<Task>());
        storage = new Storage(filepath);
        ui = new Ui();
    }

    public void run() {
        Scanner scan = new Scanner(System.in);
        storage.readFile(taskList);
        parser = new Parser(scan, storage, taskList, ui);
        ui.printWelcome();
        parser.parse();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }
}
