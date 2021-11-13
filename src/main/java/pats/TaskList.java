package pats;

import java.util.ArrayList;
import java.util.List;

import pats.task.Task;
import pats.ui.Ui;

/**
 * Contains the task list.
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Takes a list of save data string, convert them to tasks and add them into list.
     * If error occurs during parsing, the method will print error message.
     *
     * @param list lines of save data as list
     */
    protected void load(List<String> list) {
        assert list != null : "lines of save data should not be null";

        for (String s : list) {
            try {
                assert s != null : "a line of save data should not be null";

                super.add(Parser.fileContentsToTask(s));
            } catch (PatsException e) {
                Ui.printErrorMessage(e);
            }
        }
    }

    public void deleteAll() {
        super.clear();
    }

    /**
     * Checks if there is an unfinished task which equals to the given task.
     *
     * @param newTask given task to check
     * @param <T> type of task
     * @return if there is an unfinished task in list which equals to the given task
     */
    public <T extends Task> boolean hasDuplicate(T newTask) {
        return super.stream().anyMatch(t -> (!t.getStatus()) && t.equals(newTask));
    }
}
