package duke.command;

import duke.task.Task;

import java.util.List;

public class Ui {
    private TaskList taskList;
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Ui (TaskList taskList) {
        this.taskList = taskList;
    }

    private static void printSeparateLine() {
        System.out.println("________________________________________________________________");
    }

    public void showLogo() {
        System.out.println("Hello from\n" + logo);
        printSeparateLine();
        System.out.println("    Hello! I'm Irving.");
        System.out.println("    What can I do for you?");
        printSeparateLine();
    }

    public void showAddNewTask() {
        printSeparateLine();
        System.out.println("     Got it. I've added this task:");
        System.out.println("      " + taskList.get(taskList.size() - 1).toString());
        System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
        printSeparateLine();
    }

    public void showMarkTaskDone(int itemDone) {
        printSeparateLine();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + taskList.get(itemDone - 1).toString());
        printSeparateLine();
    }

    public void showGoodBye() {
        printSeparateLine();
        System.out.println("    Bye. Hope to see you again soon!");
        printSeparateLine();
    }

    public void showList() {
        printSeparateLine();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println("    " + i + "." + taskList.get(i - 1).toString());
        }
        printSeparateLine();
    }

    public void showDeleteMessage(Task deletedTask) {
        printSeparateLine();
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + deletedTask.toString());
        System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
        printSeparateLine();
    }

    public void showFindingTasks(List<Integer> nums) {
        printSeparateLine();
        if (nums.size() > 0) {
            System.out.println("    Here are the matching tasks in your list:");
        } else {
            System.out.println("    Sorry, I cannot find any matching task from the list :(");
        }
        for (int i = 1; i <= nums.size(); i++) {
            int num = nums.get(i - 1);
            System.out.println("    " + i + "." + taskList.get(num).toString());
        }
        printSeparateLine();
    }
}
