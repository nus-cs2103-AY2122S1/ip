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
    private TaskList taskList;
    private Parser parser;

    /**
     * Constructor for Duke.
     *
     * @param filePath for storing tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = storage.load();
            ui.greetWithFamiliarity(taskList);
        } catch (DukeException e) {
            ui.showDukeException(e.getMessage());
            storage.resetTasks();

            if (taskList != null) {
                taskList.clearTasks();
            }
        } finally {
            parser = new Parser(storage, ui, taskList);
        }
    }

    /**
     * Starts the assistant.
     */
    public void run() {
        String input = ui.getNextInput();

        while (true) {
            try {
                boolean keepParsing = parser.parse(input);
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
        new Duke(filePath).run();
    }

}
