package duke;

import java.util.Scanner;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Encapsulates a bot that manages tasks for users.
 *
 * @author Owen Tan
 * @version duke.Duke Level-9
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for duke.Duke.
     *
     * @param filepath Path for data to be stored.
     */
    public Duke(String filepath) {
        tasks = new TaskList();
        ui = new Ui(tasks);
        storage = new Storage(filepath, tasks);
        parser = new Parser(tasks, ui);
        storage.load();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        ui.showGreeting();
        String cmd = sc.nextLine();
        assert cmd != null : "Scanner reads null";
        while (!cmd.equals("bye")) {
            try {
                parser.parse(cmd);
                storage.save();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            cmd = sc.nextLine();
        }
        sc.close();
        ui.showFarewellMsg();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Gets the response of Duke after parsing and executing user's input.
     *
     * @return A string representation of Duke's response to user's input.
     */
    public String getResponse(String cmd) {
        String response;
        assert cmd != null : "Input is null";
        if (cmd.equals("bye")) {
            return ui.showFarewellMsg();
        }

        try {
            response = parser.parse(cmd);
            storage.save();
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }
}
