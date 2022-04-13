package dac.tasklist;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import dac.exception.InvalidInputException;
import dac.task.Deadline;
import dac.task.Event;
import dac.task.Task;
import dac.task.ToDo;


/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Constructor for when there are previously saved tasks.
     * Initializes the task list with previously saved tasks.
     *
     * @param initial The list of previously saved tasks in String form.
     */
    public TaskList(List<String> initial) throws InvalidInputException {
        Scanner scanner;
        for (int i = 0; i < initial.size(); i++) {
            scanner = new Scanner(initial.get(i));

            String task = scanner.next();

            String done = scanner.nextLine().trim();

            String details = initial.get(++i);

            switch (task) {
            case "T":
                taskList.add(new ToDo(details));
                break;
            case "D":
                String deadline = initial.get(++i);
                taskList.add(new Deadline(details, deadline));
                break;
            case "E":
                String timing = initial.get(++i);
                taskList.add(new Event(details, timing));
                break;
            default:
                throw new InvalidInputException("Task " + task + " is unrecognized by Duke.");
            }
            if (Objects.equals(done, "done")) {
                taskList.get(taskList.size() - 1).complete();
            }
        }
    }

    /**
     * Constructor for when there are no previously saved tasks.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Returns the tasks in the list.
     *
     * @return The tasks in the list as a String.
     */
    public String getList() {
        if (taskList.isEmpty()) {
            return "There are no items in the task list.";
        } else {
            String list = "Tasks in task list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                list += "\t" + (i + 1) + ". " + taskList.get(i).toString();
                if (i != taskList.size() - 1) {
                    list += "\n";
                }
            }
            return list;
        }
    }

    /**
     * Completes a task in the list.
     *
     * @param index The index of the task to be completed.
     * @throws IndexOutOfBoundsException If index exceeds the list.
     */
    public void completeTask(int index) throws InvalidInputException {
        try {
            taskList.get(index)
                    .complete();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("Task number does not exist. Complete failed.");
        }
    }

    /**
     * Deletes a task in the list.
     *
     * @param index The index of the task to be deleted.
     * @throws IndexOutOfBoundsException If index exceeds the list.
     */
    public void deleteTask(int index) throws InvalidInputException {
        try {
            taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("Task number does not exist. Delete failed.");
        }
    }

    /**
     * Returns a task in the list.
     *
     * @param index The index of the task to be returned.
     * @return The task at the given index.
     * @throws IndexOutOfBoundsException If index exceeds the list.
     */
    public Task getTask(int index) throws InvalidInputException {
        try {
            return taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("Task number does not exist. Unable to get task.");
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns the tasks that match the given keyword.
     *
     * @param keyword The keyword to match the tasks.
     * @return A list of tasks that match the given keyword.
     */
    public String filterByKeyword(String keyword) {
        String list = "Tasks that contain " + keyword + ":\n";
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDetails().contains(keyword)) {
                list += "\t" + (i + 1) + ". " + taskList.get(i).toString();
                if (i != taskList.size() - 1) {
                    list += "\n";
                }
            }
        }
        return list;
    }

    /**
     * Returns the tasks that occur on the given date.
     *
     * @param date The date to filter by.
     * @return A list of tasks that occur on the given date.
     */
    public String filterByDate(LocalDate date) {
        String list = "Tasks that occur on " + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ":\n";
        for (int i = 0; i < taskList.size(); i++) {
            LocalDateTime taskDateTime = taskList.get(i).getDate();
            LocalDate taskDate = taskDateTime == null
                    ? null
                    : taskDateTime.toLocalDate();
            if (date.equals(taskDate)) {
                list += "\t" + (i + 1) + ". " + taskList.get(i).toString();
                if (i != taskList.size() - 1) {
                    list += "\n";
                }
            }
        }
        return list;
    }

    /**
     * Sorts the task list.
     *
     * @param reverse Reverse the order of the task list if true.
     */
    public void sort(boolean reverse) {
        Comparator<Task> comparator = (Task t1, Task t2) -> {
            return reverse
                    ? (t1.compareTo(t2) * -1)
                    : t1.compareTo(t2);
        };
        taskList.sort(comparator);
    }
}
