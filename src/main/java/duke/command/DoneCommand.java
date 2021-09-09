package duke.command;

import duke.exceptions.OutOfBoundException;
import duke.parser.Parser;
import duke.taskList.TaskList;

public class DoneCommand extends Command {
    private boolean isExit;

    public DoneCommand(TaskList tasks, String input) {
        super(tasks, input);
    }

    @Override
    public boolean isExitCommand() {
        return isExit;
    }

    public String done() throws OutOfBoundException {
        Parser parser = new Parser(input);
        int index = parser.getIndex(tasks.getSize());
        return tasks.done(index);
    }

}
