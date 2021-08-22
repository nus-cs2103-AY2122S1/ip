package duke.command;

import duke.exception.DukeException;
import duke.util.ToDoList;

/**
 * This class encapsulates the command dealing with printing the tasks in the tasks list.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class ListCommand extends Command {
    private ToDoList list;

    public ListCommand(ToDoList list) {
        this.list = list;
    }

    @Override
    public void execute() throws DukeException {
        list.printList();
    }
}
