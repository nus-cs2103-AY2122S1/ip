package duke;

import duke.main.DukeException;
import duke.main.Parser;
import duke.main.Storage;
import duke.main.Ui;
import duke.task.TaskList;

import java.io.File;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructor for Duke.
     *
     * @param filePath for storing tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
            ui.greetWithFamiliarity(tasks);
        } catch (DukeException e) {
            ui.showDukeException(e.getMessage());
            storage.resetTasks();
            tasks.clearTasks();
        }
    }

    /**
     * Starts the assistant.
     */
    public void run() {
        String input = ui.getNextInput();

        while (true) {
            try {
                boolean keepParsing = Parser.parse(tasks, storage, input, ui);
                if (!keepParsing) {
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            input = ui.getNextInput();
        }
        ui.closeInput();
    }

    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir") + File.separator + "tasks.txt";
        System.out.println(filePath);
        new Duke(filePath).run();
    }

}
