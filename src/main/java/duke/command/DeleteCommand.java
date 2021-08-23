package duke.command;

import duke.exception.DukeException;
import duke.Duke;

public class DeleteCommand extends Command {
    public DeleteCommand() {
        setCommandString("delete");
    }

    @Override
    public void parse(String input) throws DukeException {
        String data = input.substring(getCommandLength()).strip();

        if (data.equals("done")) {
            Duke.taskList.deleteDone();
            return;
        }

        if (data.equals("expired")) {
            Duke.taskList.deleteExpired();
            return;
        }

        int idx;
        try {
            idx = Integer.parseInt(data);
        } catch (NumberFormatException e) {
            throw new DukeException("Please input a number");
        }

        Duke.taskList.deleteTask(idx);
    }
}
