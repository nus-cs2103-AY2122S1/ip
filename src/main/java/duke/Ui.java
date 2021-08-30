package duke;

import java.util.ArrayList;

public class Ui {

    public void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void end() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public void addTaskMessage() {
        System.out.println("     Got it. I've added this task:");
    }

    public void  printNumberOfTasks(int count) {
        System.out.println("     Now you have " + count + " task" + ((count > 1) ? "s" : "") + " in the list.");

    }

    public void printCurrentTask(Task currTask) {
         System.out.println("       " + currTask.toString());
    }

    public void listTaskMessage() {
        System.out.println("     Here are the tasks in your list:");
    }

    public void listEachTask(ArrayList<Task> xs, int a) {
        System.out.println("     " + ( a + 1 ) + ". " + xs.get(a).toString() );
    }

    public void printInvalidInput() {
        System.out.println("    Invalid Input. Please try again.");
    }

    public void printInvalidTaskNumber() {
        System.out.println("     Invalid task number. Please try again.");
    }

    public void printDoneMessage() {
        System.out.println("     Nice! I've marked this task as done!");
    }

    public void printDeletedMessage() {
         System.out.println("     Noted. I've removed this task:");
    }

}