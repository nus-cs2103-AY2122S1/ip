package duke.tasklist;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Constructor.
     * Initializes the task list with previously saved tasks.
     *
     * @param initial The list of previously saved tasks in String form.
     */
    public TaskList(List<String> initial) {
        Scanner scanner;
        for (int i = 0; i < initial.size(); i++) {
            scanner = new Scanner(initial.get(i));

            String task = scanner.next();

            String done = scanner.next();

            String details = scanner.next();

            switch (task) {
            case "T":
                taskList.add(new ToDo(details));
                break;
            case "D":

                String by =  scanner.next();

                if (by != null) {

                    String date = scanner.next();

                    int time = Integer.parseInt(scanner.next());

                    taskList.add(new Deadline(details, null, date, time));
                } else {
                    taskList.add(new Deadline(details, by, null, -1));
                }
                break;
            case "E":

                String at = scanner.next();

                if (at != null) {

                    String date = scanner.next();

                    int start = Integer.parseInt(scanner.next());
                    int end = Integer.parseInt(scanner.next());

                    taskList.add(new Event(details, null, date, start, end));
                } else {
                    taskList.add(new Event(details, at, null, -1, -1));
                }
                break;
            }
            if (Objects.equals(done, "done")) {
                taskList.get(taskList.size() - 1).isComplete();
            }
        }
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
        }
        else {
            String list = "Tasks in task list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                list += "\t\t" + (i + 1) + ". " + taskList.get(i).toString();
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
    public void completeTask(int index) throws IndexOutOfBoundsException {
        taskList.get(index)
                .isComplete();
    }

    /**
     * Deletes a task in the list.
     *
     * @param index The index of the task to be deleted.
     * @throws IndexOutOfBoundsException If index exceeds the list.
     */
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        taskList.remove(index);
    }

    /**
     * Returns a task in the list.
     *
     * @param index The index of the task to be returned.
     * @return The task at the given index.
     * @throws IndexOutOfBoundsException If index exceeds the list.
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        return taskList.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the list.
     */
    public int getSize() {
        return taskList.size();
    }
}
