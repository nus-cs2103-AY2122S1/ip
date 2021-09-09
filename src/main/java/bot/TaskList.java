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

    /**
     * Constructor for TaskList
     *
     * @param taskList list of tasks
     */
    public TaskList(List<Task> taskList) {
        assert taskList != null : "TaskList cannot be initialized with null list";
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
        assert taskList != null : "TaskList cannot be null";
        this.taskList = taskList;
    }

    /**
     * Add a new task to the list
     *
     * @param newTask task being added
     * @return success boolean
     */
    public String[] addTask(Task newTask) {
        if (this.taskList.size() >= this.MAX_TASKS) {
            return new String[] { "Task list capacity reached" };
        }
        assert newTask != null : "Task added cannot be null";
        this.taskList.add(newTask);
        return new String[]{
            "Got it. I've added this task:",
            Ui.TEXT_BLOCK_MARGIN + newTask.toString(),
            String.format("Now you have %d task(s) in the list", taskList.size())
        };
    }

    /**
     * Remove task from the list
     *
     * @param index index of task in list
     * @return message related to removing task
     */
    public String[] removeTask(int index) {
        if (index < 0 || index >= taskList.size()) {
            return new String[0];
        }
        Task task = getTaskAt(index);
        this.taskList.remove(index);
        return new String[]{
            "Noted. I've removed this task:",
            Ui.TEXT_BLOCK_MARGIN + task.toString(),
            String.format("Now you have %d task(s) in the list", taskList.size())
        };
    }

    /**
     * Get task at given index
     *
     * @param index position of task in list
     * @return Task at given index
     */
    public Task getTaskAt(int index) {
        assert index >= 0 && index < taskList.size() : "Index is out of range";
        return this.taskList.get(index);
    }

    /**
     * Get list of serialized task strings
     *
     * @return list of string representation of tasks
     */
    public List<String> getTaskStringList() {
        List<String> taskStrings = new ArrayList<>();
        for (Task t : taskList) {
            taskStrings.add(t.serialize());
        }
        return taskStrings;
    }

}
