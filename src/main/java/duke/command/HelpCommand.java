package duke.command;

import static duke.parser.Parser.COMMAND_DEADLINE;
import static duke.parser.Parser.COMMAND_DELETE;
import static duke.parser.Parser.COMMAND_DONE;
import static duke.parser.Parser.COMMAND_EVENT;
import static duke.parser.Parser.COMMAND_FIND;
import static duke.parser.Parser.COMMAND_TODO;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {
    private static final String MESSAGE_HELP = "Try out these commands to get started!\n"
            + COMMAND_TODO + "\n"
            + COMMAND_DEADLINE + "\n"
            + COMMAND_EVENT + "\n"
            + COMMAND_DONE + "\n"
            + COMMAND_DELETE + "\n"
            + COMMAND_FIND + "\n";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print(MESSAGE_HELP);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
