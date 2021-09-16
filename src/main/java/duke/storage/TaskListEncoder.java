package duke.storage;
import java.util.ArrayList;
import java.util.List;

import duke.task.TaskList;
/**
 * Encodes a list of task and saves it in a text file.
 *
 * @author Benjamin Lui
 */

public class TaskListEncoder {
    /**
     * Encodes the list of tasks to be saved into a text file.
     * @param toSave the list of tasks to be saved
     * @return a list of strings from the original list of tasks
     */
    public static List<String> encodeTaskList(TaskList toSave) {
        final List<String> encodedTasks = new ArrayList<String>();
        toSave.getAllTasks().forEach(task -> encodedTasks.add(task.toString()));
        return encodedTasks;
    }
}
