package duke.command;

import duke.Duke;

public class ListCommand extends Command {
    public ListCommand() {
        setCommandString("list");
    }

    /**
     * Lists all tasks
     *
     * @param input Full user input
     */
    @Override
    public void parse(String input) {
        Duke.taskList.list();
    }
}
