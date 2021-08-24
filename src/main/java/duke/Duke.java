package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.task.TaskList;

/**
 * The main program of the chatbot Duke. Consists of Tasklist, Ui and Storage instances.
 */
public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a Duke chatbot.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(this.ui);
        this.tasks = new TaskList(this.storage.load(), this.ui);
    }

    /**
     * Starts Duke with the loaded tasks list.
     */
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
