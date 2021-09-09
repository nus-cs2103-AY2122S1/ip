package duke.util;

import java.util.List;

import duke.task.Task;

public class Ui {

    /**
     * Final value for the border
     */
    private static final String LINES = "===============================================";

    /**
     * Basic constructor
     */
    public Ui() {
    }

    /**
     * Greeting print statement that runs on the launch of Duke.
     *
     * @param numberOfTasks The number of Tasks in the list
     */
    public void greet(int numberOfTasks) {
        String tasks = numberOfTasks == 1 ? "1 task" : numberOfTasks + " tasks";
        String greeting =
                String.format("%s\nHello! I'm Duke\nWhat can I do for you?\nI have also loaded %s "
                                + "from the database!\n%s",
                        LINES,
                        tasks,
                        LINES);
        System.out.println(greeting);
    }

    /**
     * Overloaded greet call when no tasks are in the list.
     */
    public void greet() {
        this.greet(0);
    }

    /**
     * Prints out all the tasks in the list
     *
     * @param list The tasklist
     */
    public String printTaskList(DukeTaskList list) {
        return defaultPrint(list.printList());
    }


    /**
     * Prints the string version of the message when a task is successfully
     * added.
     *
     * @param task     The task that is added.
     * @param numTasks The number of tasks that are now in the list.
     */
    public String addTaskUpdate(Task task, int numTasks) {
        String addTask = String.format("I have added the following task:\n" + "  %s\n" + "Now you have %s in the list.",
                task.toString(),
                numTasks == 1 ? String.format("%d task",
                        1) : String.format("%d tasks",
                        numTasks));
        return defaultPrint(addTask);
    }

    /**
     * Prints an update to the console when a task has been marked as complete.
     *
     * @param task     The completed task.
     * @param numTasks The remaining tasks in the list.
     */
    public String completeTaskUpdate(Task task, int numTasks) {
        String addTask = String.format("I have added the following task:\n" + "  %s\n" + "Now you have %s in the list.",
                task.toString(),
                numTasks == 1 ? String.format("%d task",
                        1) : String.format("%d tasks",
                        numTasks));
        return this.defaultPrint(addTask);
    }


    /**
     * Prints the string version of the message when a task is successfully
     * removed.
     *
     * @param task     The task that is removed.
     * @param numTasks The number of tasks remaining in the list.
     */
    public String removeTaskUpdate(Task task, int numTasks) {
        String remove =
                String.format("I have removed the following task:\n" + "  %s\n" + "Now you have %s in the list.",
                        task.toString(),
                        numTasks == 1 ? String.format("%d task",
                                1) : String.format("%d tasks",
                                numTasks));
        return this.defaultPrint(remove);
    }

    /**
     * Prints out the tasklist given a list for a given list.
     *
     * @param tasklist List of tasks
     */
    public String findUpdate(List<Task> tasklist) {
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Task i : tasklist) {
            output.append(String.format("%d. %s\n",
                    tasklist.indexOf(i) + 1,
                    i));
        }
        return defaultPrint(output.toString());
    }

    /**
     * Default printing method with = signs as borders.
     *
     * @param string String to be printed to the console.
     */
    public String defaultPrint(String string) {
        String output = string;
        return output;
    }
}
