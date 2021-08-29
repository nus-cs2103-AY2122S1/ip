package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.parser.Parser;
import duke.parser.UnableToParseException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This class is the main driver of the Duke app.
 */
public class Duke {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    private Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        ui.printLogo();
        ui.print("Initializing Duke...");
        try {
            this.tasks = this.storage.load();
        } catch (IOException | UnableToParseException e) {
            ui.printFailedInitMessage(e.getMessage());
            ui.exit();
            System.exit(1);
        }
        ui.printAllTasks(this.tasks);
    }

    /**
     * Starts the duke app.
     *
     * @param args First argument can be specified the storage path.
     */
    public static void main(String[] args) {
        String filePath = Storage.DEFAULT_FILE_PATH;
        if (args.length >= 1) {
            filePath = args[0];
        }
        new Duke(filePath).run();
    }

    /**
     * Solution adapted with minor modifications at
     * https://nus-cs2103-ay2122s1.github.io/website/schedule/week3/project.html#a-moreoop
     */
    private void run() {
        ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printErr(e.getMessage());
            } catch (Exception e) {
                ui.printErr("Unexpected Error: " + e.getMessage());
                ui.exit();
                System.exit(1);
            }
        }
    }
}
