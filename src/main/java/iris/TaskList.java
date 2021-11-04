package iris;

import java.util.ArrayList;
import java.util.List;

import iris.task.Deadline;
import iris.task.Event;
import iris.task.Task;
import iris.task.TaskPriority;
import iris.task.ToDo;

/**
 * Encapsulates the TaskList-related functionality of Iris
 */
public class TaskList {
    private final List<Task> TASKS = new ArrayList<>();

    /**
     * Gets the size of the TaskList
     *
     * @return the size of the TaskList
     */
    public int getCount() {
        return TASKS.size();
    }

    /**
     * Gets the Task from the TaskList at a given index
     *
     * @param index index of task to get
     * @return Task at the given index
     */
    public Task get(int index) {
        return TASKS.get(index);
    }

    /**
     * Adds a ToDo object to the TaskList
     *
     * @param name name of the ToDo to be created
     */
    public void addTodo(String name, TaskPriority taskPriority) {
        TASKS.add(new ToDo(name, taskPriority));
    }

    /**
     * Adds a Deadline object to the TaskList
     *
     * @param name name of the Deadline to be created
     * @param by   due date of the Deadline to be created
     * @throws IrisException for invalid Deadline
     */
    public void addDeadline(String name, String by, TaskPriority taskPriority) throws IrisException {
        TASKS.add(new Deadline(name, by, taskPriority));
    }

    /**
     * Adds an Event object to the TaskList
     *
     * @param name name of the Event to be created
     * @param at   date of the Event to be created
     * @throws IrisException for invalid Event
     */
    public void addEvent(String name, String at, TaskPriority taskPriority) throws IrisException {
        TASKS.add(new Event(name, at, taskPriority));
    }

    /**
     * Validates whether a given Task index is found in the TaskList
     *
     * @param index index of Task to lookup
     * @throws IrisException for invalid Task index e.g. too small or too large
     */
    private void validateTaskIndex(int index) throws IrisException {
        if (index <= 0) {
            throw new IrisException("Please enter a valid task index.");
        }
        int count = TASKS.size();
        if (index > count) {
            throw new IrisException(String.format("Your task list only has %d items", count));
        }
    }

    /**
     * Marks Task at a given index as completed
     *
     * @param index index of Task to mark as completed
     * @return task object that was just marked complete
     * @throws IrisException for invalid index given
     */
    public Task done(int index) throws IrisException {
        validateTaskIndex(index);
        Task task = TASKS.get(index - 1);
        task.markDone();
        return task;
    }

    /**
     * Deletes Task at a given index
     *
     * @param index index of Task to delete from TaskList
     * @return task object that was just deleted
     * @throws IrisException for invalid index given
     */
    public Task delete(int index) throws IrisException {
        validateTaskIndex(index);
        return TASKS.remove(index - 1);
    }

    /**
     * Returns a subset of tasks which contain the searchTerm
     *
     * @param searchTerm keyword to search for
     * @return List of Tasks which contain the searchTerm
     */
    public List<Task> find(String searchTerm) {
        List<Task> result = new ArrayList<>();
        for (Task task : TASKS) {
            if (task.contains(searchTerm)) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Converts all Tasks in TaskList to commands
     *
     * @return array containing commands to re-create TaskList
     */
    public String[] toCommands() {
        List<String> commands = new ArrayList<>();
        for (int i = 0; i < TASKS.size(); i++) {
            commands.add(TASKS.get(i).toCommand(i + 1));
        }
        String[] result = new String[commands.size()];
        return commands.toArray(result);
    }
}
