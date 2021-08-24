package Duke.Command;

import Duke.Duke;
import Duke.Parser;

public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public void run(Duke duke, Parser parser) {
        Duke.formatAndPrint(duke.getList().toString());
    }
}
