package duke.command;

import duke.util.TaskList;

/**
 * This class encapsulates the command dealing with printing the tasks in the tasks list.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class ListCommand extends Command {
    private final TaskList list;

    public ListCommand(TaskList list) {
        this.list = list;
    }

    @Override
    public String getResponse(String input) {
        return list.printList();
    }
}
