package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the task list.
 */
public class TaskList extends ArrayList<Task>{

    /**
     * Takes a list of save data string, convert them to tasks and add them into list.
     * @param list lines of save data as list
     */
    protected void load(List<String> list) {
        for(String s : list) {
            try {
                super.add(Parser.fileContentsToTask(s));
            } catch (DukeException e) {
                Ui.printErrorMessage(e);
            }
        }
    }

    public void deleteAll() {
        super.clear();
    }
}
