package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages historical data of statelist.
 */
public class HistoryManager {
    private ArrayList<TaskList> stateList;
    private int statePointer;

    /**
     * Creates VersionManager with an existing tasklist.
     */
    public HistoryManager(TaskList taskList) {
        stateList = new ArrayList<TaskList>(List.of(taskList.clone()));
        statePointer = 0;
    }

    /**
     * Commits taskList to stateList.
     *
     * @param taskList taskList to be saved.
     */
    public void commit(TaskList taskList) {
        while (stateList.size() > statePointer + 1) {
            stateList.remove(stateList.size() - 1);
        }
        stateList.add(taskList.clone());
        statePointer += 1;
    }

    /**
     * @return TaskList after undo.
     * @throws DukeException If there are no previous states.
     */
    public TaskList undo() throws DukeException {
        if (statePointer == 0) {
            throw new DukeException("No previous state to undo.");
        }
        statePointer -= 1;
        return stateList.get(statePointer).clone();
    }

    /**
     * @return TaskList after redo.
     * @throws DukeException If there are no next states.
     */
    public TaskList redo() throws DukeException {
        if (statePointer + 1 == stateList.size()) {
            throw new DukeException("No next state to redo.");
        }
        statePointer += 1;
        return stateList.get(statePointer).clone();
    }
}
