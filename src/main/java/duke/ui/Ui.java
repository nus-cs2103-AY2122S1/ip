package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

public class Ui {

    public static String DIVIDER_LINE = "\t____________________________";

    /**
     * Method to generate greet string when Duke is first run
     */
    public void greet() {
        String logo = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Method to generate string corresponding to Command.EXIT
     */
    public void exit() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     * Method to generate string corresponding to Command.UNKNOWN
     */
    public void unknownCommand() {
        System.out.println("\tSorry, I do not know this command!");
    }

    /**
     * Method for listing tasks given a tasklist
     * Loops through the task prepending it with a tab space and its index number
     * before calling that task's toString method
     *
     * @param tasks TaskList object containing the tasks
     */
    public void list(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Method for generate the string corresponging to Command.INDEXCOMMAND
     *
     * @param size length of the tasklist
     * @param task task operated on
     * @param taskArray String[] of the user input split once by " "
     */
    public void indexCommand(int size, Task task, String[] taskArray) {
        if (taskArray[0].equals("done")) {
            System.out.println("\tNice! I\'ve marked this task as done:");
            System.out.println(" \t" + task.toString());       
        } else {
            System.out.println("\tNoted. I've removed this task: ");
            System.out.println("\t" + task.toString());
            System.out.println("\tNow you have " + size + " tasks in the list");
        }
    }

    /**
     * Method for generating string corresponding to Command.ADDCOMMAND
     *
     * @param task Task added
     * @param size length of tasklist
     */
    public void addTask(Task task, int size) {
        System.out.println("\tGot it. I\'ve added this task:");
        System.out.println("\t  " + task.toString());
        System.out.println("\tNow you have " + size +
                           " tasks in the list.");
    }
}
