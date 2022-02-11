package duke.task;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TaskList class that encapsulates the storing of all Task objects in the app.
 */
public class TaskList {

    private List<Task> tasksList;

    /**
     * Constructs a list containing the elements of the specified ArrayList.
     *
     * @param tasks - ArrayList of Task objects retrieved from file in hard disk.
     */
    public TaskList(List<Task> tasks) {
        this.tasksList = tasks;
    }

    /**
     * Prints all items in the list.
     */
    public String displayAllItems() {
        String msg = "Here are the tasks in your list: \n";
        for (int i = 0; i < this.tasksList.size(); i++) {
            String item = "    " + (i + 1) + "." + this.tasksList.get(i) + "\n";
            msg += item;
        }
        return msg;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return Returns the number of elements in this list.
     */
    public int length() {
        return this.tasksList.size();
    }


    /**
     * Retrieves an element from the list based on its position.
     *
     * @param index index of the element to return.
     * @return Returns the element at the specified position in this list.
     */
    public Task getTask(int index) {
        return this.tasksList.get(index);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param task The Task object to be appended to this list.
     */
    public void addTask(Task task) {
        this.tasksList.add(task);
    }


    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     *
     * @param index Index of the element to be removed.
     */
    public void deleteTask(int index) {
        this.tasksList.remove(index);
    }

    /**
     * Constructs a Todo object.
     *
     * @param description String description of the action item to be done.
     * @return A Todo object.
     */
    public Todo createTodo(String description) {
        Todo todo = new Todo(description);
        this.addTask(todo);
        return todo;
    }

    /**
     * Constructs a Deadline object.
     *
     * @param description String description of the action item to be done.
     * @param date        String representation of date of yyyy-mm-dd format.
     * @return A Deadline object.
     */
    public Deadline createDeadline(String description, String date) {
        Deadline dl = new Deadline(description, date);
        this.addTask(dl);
        return dl;
    }

    /**
     * Constructs an Event object.
     *
     * @param description String description of the action item to be done.
     * @param date        String representation of date of yyyy-mm-dd format.
     * @param time        String representation of time.
     * @return An Event object.
     */
    public Event createEvent(String description, String date, String time) {
        Event event = new Event(description, date, time);
        this.tasksList.add(event);
        return event;
    }

    /**
     * Checks if a task exists in the list.
     *
     * @param taskNumber Index of the element to be checked.
     * @return True if the task exists. Otherwise, false.
     */
    public boolean isTaskExists(int taskNumber) {
        return taskNumber >= 1 && taskNumber <= this.tasksList.size();
    }

    /**
     * Returns a list of tasks.
     *
     * @return a List of Task objects.
     */
    public List<Task> getAllTasks() {
        return this.tasksList;
    }

    /**
     * Filters list of tasks by keyword matching in the description.
     *
     * @param keyword Keyword to be filtered.
     * @return A filterd list of tasks.
     */
    public List<Task> filter(String keyword) {
        // String regex = ".*\\b" + keyword + "\\b.*";
        return this.tasksList
                .stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }

}
