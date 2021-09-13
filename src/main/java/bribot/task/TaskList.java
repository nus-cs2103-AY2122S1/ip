package bribot.task;

import java.util.ArrayList;
import java.util.Collections;

import bribot.exception.DukeException;

/**
 * Represents the ArrayList where the tasks of the program is stored.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private final int todoLength = 3;
    private final int deadlineLength = 4;
    private final int eventLength = 4;

    /**
     * Reads in from an ArrayList of strings that is formatted to represent a task, and creates and adds
     * the appropriate task into the ArrayList of tasks.
     * @param loadingStrings the given ArrayList of formatted string that represents a task.
     * @throws DukeException if the String is of the wrong format.
     */
    public TaskList(ArrayList<String> loadingStrings) throws DukeException {
        this.tasks = new ArrayList<>();
        for (int i = 0; i < loadingStrings.size(); i++) {
            String taskString = loadingStrings.get(i);
            String[] taskStringArr = taskString.split("\\|");
            String type = taskStringArr[0];
            Task task;
            switch (type) {
            case "T":
                if (taskStringArr.length != todoLength) {
                    throw new DukeException("ERROR READING FROM STORAGE");
                }
                task = new Todo(taskStringArr[2]);
                break;
            case "D":
                if (taskStringArr.length != deadlineLength) {
                    throw new DukeException("ERROR READING FROM STORAGE");
                }
                String[] deadlineDateTimeArr = taskStringArr[3].split(" ");
                String deadlineDateString = deadlineDateTimeArr[0];
                String deadlineTimeString = deadlineDateTimeArr[1];
                task = new Deadline(taskStringArr[2], deadlineDateString, deadlineTimeString);
                break;
            case "E":
                if (taskStringArr.length != eventLength) {
                    throw new DukeException("ERROR READING FROM STORAGE");
                }
                String[] eventDateTimeArr = taskStringArr[3].split(" ");
                String eventDateString = eventDateTimeArr[0];
                String eventTimeString = eventDateTimeArr[1];
                task = new Event(taskStringArr[2], eventDateString, eventTimeString);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
            }
            if (taskStringArr[1].equals("1")) {
                task.markAsDone();
            }
            tasks.add(task);
        }

    }

    /**
     * Creates an empty ArrayList of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the task at the given index of the ArrayList of tasks.
     * @param index of the task that is to be returned.
     * @return the task at the given index of the ArrayList of tasks.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the ArrayList of tasks.
     * @return the ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Removes the Task at the given Index from the ArrayList of Tasks.
     * @param delIndex the given index that the task should be removed.
     */
    public void deleteTask(int delIndex) {
        Task task = tasks.get(delIndex);
        tasks.remove(delIndex);
    }

    /**
     * Marks the task at the given index from the ArrayList of tasks as done.
     * @param index the given index of the task that should be marked as done.
     */
    public void markTask(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
    }

    /**
     * Adds the task to the ArrayList of tasks.
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns the size of the ArrayList of tasks.
     * @return the size of the ArrayList of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Finds tasks from the TaskList whose description matches the given input
     * @param input the given input.
     * @return An ArrayList of tasks that match the given input.
     */
    public ArrayList<Task> find(String input) {
        ArrayList<Task> res = new ArrayList<>();
        String lowerInput = input.toLowerCase();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String lowerDescription = task.getDescription().toLowerCase();
            if (lowerDescription.contains(lowerInput)) {
                res.add(task);
            }
        }
        return res;
    }

    public void sort() {
        Collections.sort(tasks);
    }

}
