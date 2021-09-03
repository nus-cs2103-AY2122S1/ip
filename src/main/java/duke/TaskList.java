package duke;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.tasks.Task;

/**
 * Class that handles an ArrayList of Tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    private enum Format {
        LIST,
        SAVE
    }
    
    @FunctionalInterface
    interface CheckFormat<S, T> {
        String apply(S f, T t);
    }

    /**
     * TaskList constructor for empty tasks list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * TaskList constructor for a given tasks list.
     *
     * @param taskList list of tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Returns a String to display for the list command.
     *
     * @return String for display
     */
    public String stringifyTasksForList() {
        return this.stringify(Format.LIST);
    }

    /**
     * Returns a String to save.
     *
     * @return String for saving
     */
    public String stringifyTasksForSave() {
        return this.stringify(Format.SAVE);
    }

    private String stringify(Format format) {
        if (this.tasks.size() == 0) {
            return "No tasks added yet!";
        }
        
        CheckFormat<Format, Task> check = (type, task) -> type == Format.LIST 
                                                            ? task.toString() 
                                                            : task.toSaveString();

        String res = IntStream
                .range(0, this.tasks.size())
                .mapToObj(i -> (i + 1) + ". " + check.apply(format, this.tasks.get(i)) + "\n")
                .reduce("", (x, y) -> x + y);       
        
        return res.substring(0, res.length() - 1);
    }

    /**
     * Returns a String for Find command.
     *
     * @return String for Find
     */
    public String stringifyTasksForFind(String keyword) {
        if (this.tasks.size() == 0) {
            return "No tasks added yet!";
        }
        
        String res = IntStream
                .range(0, this.tasks.size())
                .filter(i -> this.tasks.get(i).getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .mapToObj(i -> (i + 1) + ". " + this.tasks.get(i).toString() + "\n")
                .reduce("", (x, y) -> x + y);

        if (res.equals("")) {
            return "No tasks match that keyword :(";
        } else {
            return res.substring(0, res.length() - 1);
        }
    }

    /**
     * Returns size of task list.
     *
     * @return size as an int
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns Task at given index.
     *
     * @param x int index
     * @return Task at index x
     */
    public Task getTask(int x) {
        return this.tasks.get(x);
    }

    /**
     * Removes Task at given index.
     *
     * @param x int index
     * @return Removed task
     */
    public Task removeTask(int x) {
        return this.tasks.remove(x);
    }

    /**
     * Adds Task to list.
     *
     * @param t Task to add
     * @return boolean indicating if the operation was a success
     */
    public boolean addTask(Task t) {
        return this.tasks.add(t);
    }
}
