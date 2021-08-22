package duke.command;

import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.ToDoList;

/**
 * This class encapsulates the command dealing with deleting tasks from the list.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class DeleteCommand extends Command {
    private final ToDoList list;
    private final String input;

    public DeleteCommand(String input, ToDoList list) {
        this.input = input;
        this.list = list;
    }

    /** Extracts index from user input and removes task from the list and persisted storage. */
    @Override
    public void execute() throws DukeException {
        int index = Parser.extractIndex(input);
        list.removeFromList(index);
        list.updateData();
    }
}
