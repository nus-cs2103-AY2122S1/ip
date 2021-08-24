package duke.command;

import duke.Duke;

public class ListCommand extends Command {
    public ListCommand() {
        setCommandString("list");
    }

    @Override
    public void parse(String input) {
        Duke.taskList.list();
    }
}
