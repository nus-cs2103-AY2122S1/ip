package duke.command;

import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.TaskList;

/**
 * This class encapsulates the command dealing with marking tasks as done.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class DoneCommand extends Command {
    private final TaskList list;

    public DoneCommand(TaskList list) {
        this.list = list;
    }

    @Override
    public String getResponse(String input) {
        int index;
        try {
            index = Parser.extractIndex(input);
            String response = list.markTaskAsDone(index);
            list.updateData();
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
