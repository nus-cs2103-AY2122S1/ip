package Duke.Command;

import Duke.Main.Storage;
import Duke.Main.TaskList;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("help", null);
    }

    @Override
    public String reply() {
        return Storage.processInstructions();
    }
}
