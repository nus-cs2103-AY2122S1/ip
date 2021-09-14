package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Contains a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a new TaskList by parsing an array of Strings into an array of tasks.
     *
     * @param stringList Arraylist of strings read from the file.
     * @throws DukeException If there is an error with parsing.
     */
    TaskList(ArrayList<String> stringList) throws DukeException {
        tasks = new ArrayList<>();
        for (int i = 0; i < stringList.size(); i++) {
            String s = stringList.get(i);
            String[] stringArr = s.split("_");
            Task t = null;
            switch (stringArr[0].charAt(0)) {
            case 'T':
                t = new Todo(stringArr[1]);
                if (stringArr.length > 2) {
                    t.setTag(stringArr[2]);
                }
                break;
            case 'D':
                t = new Deadline(stringArr[1], stringArr[2]);
                if (stringArr.length > 3) {
                    t.setTag(stringArr[3]);
                }
                break;
            case 'E':
                t = new Event(stringArr[1], stringArr[2]);
                if (stringArr.length > 3) {
                    t.setTag(stringArr[3]);
                }
                break;
            default:
                throw new DukeException("File not in the correct format");
            }
            if (stringArr[0].charAt(1) == 'X') {
                t.markedAsDone();
            }
            tasks.add(t);
        }
    }

    /**
     * Adds task to task list
     *
     * @param t Task to be added
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Removes a task from the list.
     *
     * @param i Index of task to be removed.
     */
    public void remove(int i) {
        tasks.remove(i);
    }

    /**
     * Returns the size of the list.
     *
     * @return Number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets the element at an index in the list.
     *
     * @param i Index number.
     * @return Task at index.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Finds a task containing the string s
     *
     * @param s String to filter with
     * @throws DukeException If s is not found
     */
    public String find(String s) throws DukeException {
        List<Task> filteredList = tasks.stream()
                    .filter(task -> task.toString().contains(s)).collect(Collectors.toList());
        if (filteredList.size() == 0) {
            throw new DukeException(s + " not found in list");
        }
        return listItems(filteredList);
    }

    /**
     * Lists out current items.
     */
    public String listItems() {
        String message = "Here are the tasks in your list:\n";
        message += listItems(this.tasks);
        return message;
    }

    private String listItems(List<Task> tasks) {
        String message = "";
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            message += num + "." + tasks.get(i).toString() + "\n";
        }
        return message;
    }
}
