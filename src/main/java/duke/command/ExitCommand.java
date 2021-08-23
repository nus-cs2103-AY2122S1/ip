package duke.command;

import duke.task.TaskHandler;
import duke.ui.Ui;

public class ExitCommand extends Command {

    private static final CommandType COMMAND_TYPE = CommandType.EXIT;

    public ExitCommand(String command) {
        super(command);
        this.mustExit = true;
    }

    @Override
    public void execute(TaskHandler taskHandler, Ui ui) {
        ui.printExitMessage();
    }

    @Override
    void parseCommand(String[] tokens) {

    }

    @Override
    CommandType getCommandType() {
        return COMMAND_TYPE;
    }
}
