package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.task.Tasklist;

public class Duke {
    private Tasklist tasks;
    private Ui ui;
    private Storage storage;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(this.ui);
        this.tasks = new Tasklist(this.storage.load(), this.ui);
    }

    public void run() {
        this.ui.greetUser();
        boolean shouldContinue = true;
        while (shouldContinue) {
            try {
                Command command = this.ui.readCommand();
                shouldContinue = Parser.parse(command, this.tasks, this.storage);
            } catch (IllegalArgumentException e) { // caused by user entering a command that is invalid
                this.ui.clearInput();
                this.ui.showError(new InvalidCommandException());
            } catch (DukeException e) {
                this.ui.showError(e);
            }
        }
        this.ui.showFarewell();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
