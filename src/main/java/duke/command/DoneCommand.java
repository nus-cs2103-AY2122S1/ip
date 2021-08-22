package duke.command;

import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.ToDoList;

/**
 * This class encapsulates the command dealing with marking tasks as done.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class DoneCommand extends Command {
    private final ToDoList list;
    private final String input;

    public DoneCommand(ToDoList list, String input) {
        this.list = list;
        this.input = input;
    }

    /** Extracts index from user input and marks corresponding task from the list and persisted storage as
     * done.
     */
    @Override
    public void execute() throws DukeException {
        int index = Parser.extractIndex(input);
        list.markTaskAsDone(index);
        list.updateData();
    }
}
