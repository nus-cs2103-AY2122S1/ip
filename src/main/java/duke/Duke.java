package duke;

import duke.exception.DukeException;

import java.io.IOException;
import java.util.Scanner;

/**
 * A Personal Assistant Chat bot that helps to keep track of tasks
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs Duke object
     *
     * @param filepath file location
     */
    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            System.out.println("Past records (if any):");
            taskList = storage.loadData();
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
        parser = new Parser(this.taskList, this.ui, this.storage);
    }

    public Ui getUi() {
        return this.ui;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Activates programme
     */
    public void run() {
        ui.welcome();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            try {
                parser.parse(s);
            } catch (DukeException e) {
                e.printError();
            }
        }
    }

    public String getResponse(String input) {
        try{
            return parser.parse(input);
        } catch (DukeException e) {
            return e.getError();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
