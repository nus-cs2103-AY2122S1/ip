package duke.command;

import duke.UI;

public class ExitCommand extends Command {

    @Override
    public void execute() {
        System.out.println(
                UI.formatString("Bye. Hope to see you again soon!")
        );
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
