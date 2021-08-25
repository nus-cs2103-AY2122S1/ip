package bot;

import java.util.ArrayList;
import java.util.List;

import tasks.Task;

/**
 * Encapsulates bot task list
 */
public class TaskList {

    private List<Task> taskList;
    private final int MAX_TASKS = 100;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Get the taskList
     *
     * @return Bot's list of tasks
     */
    public List<Task> get() {
        return this.taskList;
    }

    /**
     * Set the taskList
     *
     * @param taskList list of tasks
     */
    public void set(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Add a new task to the list
     *
     * @param newTask task being added
     * @return success boolean
     */
    public Boolean addTask(Task newTask) {
        if (this.taskList.size() >= this.MAX_TASKS) {
            return false;
        }
        this.taskList.add(newTask);
        Ui.print(new String[]{
                "Got it. I've added this task:",
                Ui.TEXT_BLOCK_MARGIN + newTask.toString(),
                String.format("Now you have %d task(s) in the list", taskList.size())
        });
        return true;
    }

    /**
     * Remove task from the list
     *
     * @param index index of task in list
     * @return success boolean
     */
    public Boolean removeTask(int index) {
        if (index < 0 || index >= taskList.size()) {
            return false;
        }
        Task task = getTaskAt(index);
        this.taskList.remove(index);
        Ui.print(new String[]{
                "Noted. I've removed this task:",
                Ui.TEXT_BLOCK_MARGIN + task.toString(),
                String.format("Now you have %d task(s) in the list", taskList.size())
        });
        return true;
    }

    /**
     * Get task at given index
     *
     * @param index position of task in list
     * @return Task at given index
     */
    public Task getTaskAt(int index) {
        return this.taskList.get(index);
    }

    public List<String> getTaskStringList() {
        List<String> taskStrings = new ArrayList<>();
        for (Task t : taskList) {
            taskStrings.add(t.serialize());
        }
        return taskStrings;
    }

}
