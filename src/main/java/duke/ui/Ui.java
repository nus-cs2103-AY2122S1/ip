package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

public class Ui {

    public static String DIVIDER_LINE = "\t____________________________";

    public void greet() {
        String logo = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void exit() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    public void unknownCommand() {
        System.out.println("\tSorry, I do not know this command!");
    }

    public void list(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
        }
    }

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

    public void addTask(Task task, int size) {
        System.out.println("\tGot it. I\'ve added this task:");
        System.out.println("\t  " + task.toString());
        System.out.println("\tNow you have " + size +
                           " tasks in the list.");
    }
}
