package memocat.task;

import java.util.ArrayList;
import java.util.Collections;

/**
 * An array list of tasks.
 */
public class TaskList extends ArrayList<Task> {
    private ArrayList<Task> list = new ArrayList<>();

    /**
     * Creates an empty list of task.
     */
    public TaskList() {
    }

    /**
     * Creates a list of tasks.
     *
     * @param tasks ArrayList of Tasks to create the task list from.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.list = tasks;
    }

    @Override
    public boolean add(Task task) {
        return list.add(task);
    }

    @Override
    public Task remove(int index) {
        return list.remove(index);
    }

    @Override
    public Task get(int index) {
        return list.get(index);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (!list.isEmpty()) {
            result.append("Here are the tasks in your list:\n");
            result.append(1).append(". ").append(list.get(0));
            for (int i = 1; i < list.size(); i++) {
                result.append("\n").append(i + 1).append(". ").append(list.get(i));
            }
        } else {
            result.append("There is not task yet. Try to add a task first.");
        }
        return result.toString();
    }

    /**
     * Converts the task list into array list of string.
     *
     * @return Array list of task strings.
     */
    public ArrayList<String> toDataString() {
        ArrayList<String> dataStrings = new ArrayList<>();
        for (Task task : list) {
            dataStrings.add(taskToDataString(task));
        }
        return dataStrings;
    }

    /**
     * Converts a data string read from memocat.txt into task.
     *
     * @return The task represented by the string.
     */
    private String taskToDataString(Task task) {
        String taskType = task.getClass().getSimpleName();
        String data = "";
        switch (taskType) {
        case "Todo":
            // task is todo
            data += "T | ";
            data += task.isDone ? "1 | " : "0 | ";
            data += task.description;
            break;
        case "Deadline":
            // task is deadline
            Deadline deadline = (Deadline) task;
            data += "D | ";
            data += deadline.isDone ? "1 | " : "0 | ";
            data += deadline.description + " | " + deadline.by;
            break;
        case "Event":
            // task is event
            Event event = (Event) task;
            data += "E | ";
            data += event.isDone ? "1 | " : "0 | ";
            data += event.description + " | " + event.at;
            break;
        default:
            // not of any task type
            throw new IllegalArgumentException("Task type not recognized: " + taskType);
        }
        return data;
    }

    /**
     * Gets a string of tasks matching the pattern.
     *
     * @param pattern The pattern to match.
     * @return A string of tasks matching the patter.
     */
    public String getMatchedTasksString(String pattern) {
        ArrayList<Task> matched = new ArrayList<>();
        for (Task task : list) {
            if (task.description.contains(pattern)) {
                matched.add(task);
            }
        }

        StringBuilder result = new StringBuilder();
        int index;
        if (!matched.isEmpty()) {
            result.append("Here are the matching tasks in your list:\n");

            index = list.indexOf(matched.get(0));

            result.append(index + 1).append(". ").append(matched.get(0));
            for (int i = 1; i < matched.size(); i++) {
                index = list.indexOf(matched.get(i));
                result.append("\n").append(index + 1).append(". ").append(matched.get(i));
            }
        } else {
            result.append("There is not task matched.");
        }
        return result.toString();
    }

    /**
     * Sorts the tasks by time.
     *
     * Todo tasks are not sorted and always put in front.
     */
    public void sortByTime() {
        Collections.<Task>sort(list);
    }
}
