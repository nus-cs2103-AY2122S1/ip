package duke;

import duke.command.Command;
import duke.command.MalformedCommandException;
import duke.command.UnsupportedCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.storage.StorageException;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList();
    }

    /**
     * Reads user input and executes user commands.
     */
    public void run() {
        ui.showWelcomeMessage();

        try {
            tasks.loadTasks(storage.load());
        } catch (StorageException e) {
            ui.showErrorMessage(e);
        }


        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.getUserInput();
                Command userCommand = Parser.parse(userInput);
                userCommand.execute(tasks, ui);
                isExit = userCommand.isExit();
            } catch (UnsupportedCommandException | MalformedCommandException e) {
                ui.showErrorMessage(e);
            }
        }

        try {
            storage.saveTasks(tasks);
        } catch (StorageException e) {
            ui.showErrorMessage(e);
        }
    }

    public static void main(String[] args) {
        Duke chatBot = new Duke();
        chatBot.run();
    }
}
