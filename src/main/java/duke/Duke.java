package duke;

import duke.commands.Command;
import duke.parser.Parser;
import duke.parser.UnableToParseException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import java.io.IOException;

/**
 * This class is the main driver of the Duke app.
 */
public class Duke {
    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    
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
    
    public static void main(String[] args) {
        String filePath = Storage.DEFAULT_FILE_PATH;
        if (args.length >= 1) {
            filePath = args[0];
        }
        new Duke(filePath).run();
    }

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
