package duke;

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

    public Duke() {
        try {
            this.ui = new Ui();
            this.storage = new Storage();
            this.tasks = new TaskList(Parser.parseSaveFile(this.storage.getSavedContents()));
        } catch (DukeException e) {
            this.ui.printErrorMessage(e);
            this.tasks = new TaskList();
        }
    }

    public void run() {
        boolean isExit = false;
        this.ui.printStartMessage();
        while (!isExit) {
            try {
                String userInput = this.ui.readUserInput();
                Command command = Parser.parseUserInput(userInput);
                command.execute(this.tasks, this.ui, this.storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                this.ui.printErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
