package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulate the TaskList-related functionality of Iris
 */
public class TaskList {
    private final List<Task> taskList = new ArrayList<>();

    /**
     * Get the size of the TaskList
     * @return the size of the TaskList
     */
    public int getCount() {
        return taskList.size();
    }

    /**
     * Get the Task from the TaskList at a given index
     * @param index index of task to get
     * @return
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Add a ToDo object to the TaskList
     * @param name name of the ToDo to be created
     */
    public void addTodo(String name) {
        taskList.add(new ToDo(name));
    }

    /**
     * Add a Deadline object to the TaskList
     * @param name name of the Deadline to be created
     * @param by   due date of the Deadline to be created
     * @throws IrisException
     */
    public void addDeadline(String name, String by) throws IrisException {
        taskList.add(new Deadline(name, by));
    }

    /**
     * Add an Event object to the TaskList
     * @param name name of the Event to be created
     * @param at   date of the Event to be created
     * @throws IrisException
     */
    public void addEvent(String name, String at) throws IrisException {
        taskList.add(new Event(name, at));
    }

    /**
     * Validate whether a given Task index is found in the TaskList
     * @param index index of Task to lookup
     * @throws IrisException for invalid Task index e.g. too small or too large
     */
    private void validateTaskIndex(int index) throws IrisException {
        if (index <= 0) throw new IrisException("Please enter a valid task index.");
        int count = taskList.size();
        if (index > count) throw new IrisException(String.format("Your task list only has %d items", count));
    }

    /**
     * Mark Task at a given index as completed
     * @param index index of Task to mark as completed
     * @return task object that was just marked complete
     * @throws IrisException for invalid index given
     */
    public Task done(int index) throws IrisException {
        validateTaskIndex(index);
        Task task = taskList.get(index - 1);
        task.markComplete();
        return task;
    }

    /**
     * Delete Task at a given index
     * @param index index of Task to delete from TaskList
     * @return task object that was just deleted
     * @throws IrisException
     */
    public Task delete(int index) throws IrisException {
        validateTaskIndex(index);
        return taskList.remove(index - 1);
    }

    /**
     * Convert all Tasks in TaskList to commands
     * @return array containing commands to re-create TaskList
     */
    public String[] toCommands() {
        List<String> commands = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            commands.add(taskList.get(i).toCommand(i + 1));
        }
        String[] result = new String[commands.size()];
        return commands.toArray(result);
    }
}
