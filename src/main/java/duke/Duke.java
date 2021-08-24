package duke;

import duke.command.CommandManager;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.exception.DukeException;

/**
 * Main class for the bot.
 */
public class Duke {
    private final DukeList list;
    private final CommandManager commandManager;
    private final UserInterface ui;

    private Duke() {
        this.list = new DukeList();
        this.commandManager = new CommandManager();
        this.ui = new UserInterface();
    }

    public static void main(String[] args) {
        new Duke().init();
    }

    private void init() {
        this.ui.greet();

        // Duke commands work with a registry so that add-ons can be developed with
        // commands simply registered like so
        this.commandManager.registerCommands(new ListCommand(this.list, this.ui), new DoneCommand(this.list, this.ui),
                new ToDoCommand(this.list, this.ui), new EventCommand(this.list, this.ui),
                new DeadlineCommand(this.list, this.ui), new DeleteCommand(this.list, this.ui),
                new FindCommand(this.list, this.ui));

        this.run();
    }

    private void run() {
        String input = "";
        try {
            input = this.ui.getInput();
        } catch (DukeException e) {
            this.ui.showError(e);
        }
        if (input.equals("bye")) {
            terminate();
            return;
        } else {
            try {
                this.commandManager.parseInput(input);
            } catch (DukeException e) {
                this.ui.showError(e);
            }
        }
        run();
    }

    private void terminate() {
        this.ui.farewell();
    }

}
