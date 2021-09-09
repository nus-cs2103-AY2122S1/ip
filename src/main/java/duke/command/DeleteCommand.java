package duke.command;

import duke.exceptions.OutOfBoundException;
import duke.parser.Parser;
import duke.taskList.TaskList;

public class DeleteCommand extends Command {
    private boolean isExit;

    public DeleteCommand(TaskList tasks, String input) {
        super(tasks, input);
    }


    @Override
    public boolean isExitCommand() {
        return isExit;
    }

    public String delete() throws OutOfBoundException {
        Parser parser = new Parser(input);
        int index = parser.getIndex(tasks.getSize());
        return "Noted. I've removed this task: \n" + tasks.delete(index) +
                "\nNow you have " + (tasks.getSize()) + " tasks in the list.";
    }

}
