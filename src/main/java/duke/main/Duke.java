package duke.main;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes Ui, storage and load TaskLists from specific filePath for Duke.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Initializes the duke.Duke chatbot program for the duke.Launcher
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Run the programme of Duke.
     */
    public void run() {
        String input = "";
        ui.greet();
        Scanner keyboardIn = new Scanner(System.in);
        do {
            input = keyboardIn.nextLine();
            getResponse(input);

        } while (!input.equals("bye"));
        storage.updateData();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public String getResponse(String input) {
        Parser parser = new Parser(input);
        try {
            Command command = parser.parse(tasks, ui);
            return command.execute();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        } final {
            storage.updateData();
        }
    }

}
