package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.DukeException;

/**
 * Represents a list of tasks to do.
 */
public class TaskList {

    /**
     * The todo list that stores tasks.
     */
    private final ArrayList<Task> todoList;

    /**
     * Constructs a task list with no tasks in it.
     */
    public TaskList() {
        this.todoList = new ArrayList<>();
    }

    /**
     * Constructs a task list with the tasks given.
     *
     * @param tasks An array of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.todoList = tasks;
    }

    /**
     * Prints this todo list in order.
     */
    public ArrayList<String> getTaskStrings() {
        ArrayList<String> taskStrings = new ArrayList<>();
        for (int i = 0; i < todoList.size(); i++) {
            Task task = todoList.get(i);
            int num = i + 1;
            taskStrings.add(num + "." + task.toString());
        }

        return taskStrings;
    }

    /**
     * Add a task to this todo list.
     *
     * @param taskType The type of task to be added.
     * @param details  The details of the task.
     * @return The task added into the list.
     * @throws DukeException
     */
    public Task addTask(TaskType taskType, String details) throws DukeException {
        Task task;
        if (taskType.equals(TaskType.TODO)) {
            task = new ToDo(details);
        } else if (taskType.equals(TaskType.DEADLINE)) {
            TaskParams params = getAdditionalDetail(details, "/by",
                "Please indicate the deadline eg \"/by 09-09-2021 23:00\" ");
            task = new Deadline(params.description, params.additional);
        } else if (taskType.equals(TaskType.EVENT)) {
            TaskParams params = getAdditionalDetail(details, "/at",
                "Please indicate the event time eg \"/at Mon 2-4pm\" ");
            task = new Event(params.description, params.additional);
        } else {
            // should not reach here
            throw new DukeException("Invalid task type.");
        }

        todoList.add(task);
        return task;
    }

    private TaskParams getAdditionalDetail(String fullDetails, String label, String errorMessage) throws DukeException {
        int position = fullDetails.indexOf(label);

        if (position < 0) {
            throw new DukeException(errorMessage);
        }

        String description = fullDetails.substring(0, position).trim();
        String additional = fullDetails.substring(position + label.length()).trim();
        return new TaskParams(description, additional);
    }

    /**
     * Mark a task with given task number as done.
     *
     * @param taskNum The task number of the task to be marked as done.
     * @return The task marked as done.
     * @throws DukeException
     */
    public Task markTaskDone(Integer taskNum) throws DukeException {
        assert todoList.size() > 0;

        Task task = todoList.get(taskNum - 1);
        task.markAsDone();

        return task;
    }

    /**
     * Delete a task with given task number.
     *
     * @param taskNum The task number of the task to be deleted from the list.
     * @return The deleted task.
     * @throws DukeException
     */
    public Task deleteTask(Integer taskNum) throws DukeException {
        assert todoList.size() > 0;

        Task task = todoList.remove(taskNum - 1);

        return task;
    }

    /**
     * Finds tasks in the task list that matches a given keyword.
     *
     * @param keyword The keyword that the user wants to search for.
     * @return A list of tasks that match the keyword.
     * @throws DukeException
     */
    public ArrayList<Task> findTask(String keyword) throws DukeException {
        if (keyword == "") {
            throw new DukeException("Looks like you forgot to enter a keyword.");
        }

        return todoList.stream().filter(task -> task.contains(keyword)).collect(
            Collectors.toCollection(ArrayList::new));
    }

    /**
     * Convert this task list into data string format.
     *
     * @return A list of tasks in data string format.
     */
    public List<String> getListData() {
        return todoList.stream().map(task -> task.toDataString("|")).collect(Collectors.toList());
    }

    /**
     * Returns the size of this task list.
     *
     * @return returns the size of this task list.
     */
    public Integer getListSize() {
        return todoList.size();
    }

    /**
     * The types of tasks that this list contains.
     */
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    class TaskParams {
        private final String description;
        private final String additional;

        public TaskParams(String d, String a) {
            this.description = d;
            this.additional = a;
        }
    }
}
