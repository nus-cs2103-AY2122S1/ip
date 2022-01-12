package duke.exception;

import java.util.List;
import java.util.ArrayList;

public class IndexOutOfBoundException extends DukeException {
    private int numTasks;

    public IndexOutOfBoundException(int numTasks) {
        super("The index specified is outside the permissible range.");
        this.numTasks = numTasks;
    }

    public List<String> getHelpMessages() {
        List<String> list = new ArrayList<>();
        if (numTasks == 0) {
            list.add("Your list is empty, try adding some tasks first!");
        } else {
            list.add(String.format("You only have %d tasks in your task list.", numTasks));
            list.add(String.format("Try indexing in the range [1...%d].", numTasks));
        }
        return list;
    }
}