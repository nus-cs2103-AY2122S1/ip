package duke.util;

import duke.task.Task;

public class Ui {

    final private static String LINES = "===============================================";

    public Ui (){}

    public void greet(int numberOfTasks) {
        String tasks = numberOfTasks == 1 ? "1 task" : numberOfTasks + " tasks";
        String greeting = String.format("%s\nHello! I'm Duke\nWhat can I do for you?\nI have also loaded %s " +
                        "from the database!\n%s", LINES, tasks, LINES);
        System.out.println(greeting);
    }

    public void greet() {
        this.greet(0);
    }

    public void printTaskList(DukeTaskList list) {
        defaultPrint(list.printList());
    }




    /**
     * Prints the string version of the message when a task is successfully
     * added.
     * @param task The task that is added.
     * @param numTasks The number of tasks that are now in the list.
     */
    public void addTaskUpdate(Task task, int numTasks) {
        String addTask = String.format("I have added the following task:\n" + "  %s\n" +
                        "Now you have %s in the list.",
                task.toString(),
                numTasks == 1 ? String.format("%d task",
                        1) : String.format("%d tasks",
                        numTasks));
        this.defaultPrint(addTask);
    }

    public void completeTaskUpdate(Task task, int numTasks) {
        String addTask = String.format("I have added the following task:\n" + "  %s\n" +
                        "Now you have %s in the list.",
                task.toString(),
                numTasks == 1 ? String.format("%d task",
                        1) : String.format("%d tasks",
                        numTasks));
        this.defaultPrint(addTask);
    }


    /**
     * Prints the string version of the message when a task is successfully
     * removed.
     * @param task The task that is removed.
     * @param numTasks The number of tasks remaining in the list.
     */
    public void removeTaskUpdate(Task task, int numTasks) {
        String remove = String.format("I have removed the following task:\n" + "  %s\n" +
                        "Now you have %s in the list.",
                task.toString(),
                numTasks == 1 ? String.format("%d task",
                        1) : String.format("%d tasks",
                        numTasks));
        this.defaultPrint(remove);
    }

    public void defaultPrint(String string) {
        System.out.printf("%s\n%s\n%s\n%n", LINES, string, LINES);
    }
}
