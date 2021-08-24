package task;

import duke.Storage;
import duke.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) { this.tasks = tasks; }

    /**
     * Prints the number of tasks in the list
     */
    public void printSize() {
        System.out.println( Ui.OUTPUT_DISPLAY +
                (tasks.size() == 1
                    ? "There is 1 task in your list"
                    : "There are " + tasks.size() + " tasks in your list"));
    }

    /**
     * Adds a task into the task list
     *
     * @param input A matcher object with the groups of information
     *              required to create a task with the corresponding
     *              task type
     * @param type The task.TaskType of task to add
     */
    public void add(Matcher input, TaskType type) {
        Optional.ofNullable(
                TaskType.getNewTask(input, type))
                    .map(task -> {
                        System.out.println("Got it. I've added this task:");
                        System.out.println(Ui.OUTPUT_SPACES + task);
                        tasks.add(task);
                        this.printSize();
                        return null;
                    });
        Storage.saveList(tasks);
    }


    /**
     * Marks the task as complete (or incomplete if it is already complete)
     *
     * @param index Index of the task displayed by the list command
     *              Actual index is (index - 1)
     */
    public void toggleDone(int index) {
        try {
            boolean result = tasks.get(index - 1).toggleDone();
            System.out.println(result
                    ? Ui.OUTPUT_DISPLAY + "sugoi! Duke-san marked this task as done!"
                    : Ui.OUTPUT_DISPLAY + "Duke-san marked this task as not done!");
            System.out.println(Ui.OUTPUT_SPACES + tasks.get(index - 1));
            Storage.saveList(tasks);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There's no task at index " + index + "!!");
        }
    }

    /**
     * Deletes a task from the task list
     *
     * @param index Index of the task displayed by the list command
     *              Actual index is (index - 1)
     */
    public void delete(int index) {
        try {
            Task removed = tasks.remove(index - 1);
            System.out.println(Ui.OUTPUT_DISPLAY + "Noted. Duke-san removed this task:");
            System.out.println(Ui.OUTPUT_SPACES + removed);
            this.printSize();
            Storage.saveList(tasks);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There's no task at index " + index + "!!");
        }
    }

    /**
     * Displays the list of tasks based on predicate filter
     *
     * @param filters Predicates to filter; return true to display task
     */
    public void displayList(ArrayList<Predicate<Task>> filters) {
        if (tasks.size() == 0) {
            System.out.println(Ui.OUTPUT_DISPLAY + "There is nothing to display! :angery:");
        } else {
            // Apply all filters
            List<Task> list = tasks.stream()
                    .filter(task -> filters.stream()
                            .allMatch(predicate -> predicate.test(task)))
                    .collect(Collectors.toList());

            if (list.size() == 0) {
                System.out.println(Ui.OUTPUT_DISPLAY + "There is nothing to display! :angery:");
            } else {
                // Add proper formatting and display list
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(Ui.OUTPUT_SPACES + (i + 1) + "." + list.get(i));
                }
            }
        }
    }
}
