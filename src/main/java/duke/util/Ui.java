package duke.util;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {
    String logo = "   _       _\n"
            + "  /_\\  ___| |__  _   _\n"
            + " //_\\\\/ __| '_ \\| | | |\n"
            + "/  _  \\__ \\ | | | |_| |\n"
            + "\\_/ \\_/___/_| |_|\\__, |\n"
            + "                 |___/\n";

    public void greetingMessage() {
        System.out.println("Hello! I'm\n" + logo + "\nWhat can I do for you?");
    }

    public void exitMessaage() {
        System.out.println("\t ByeBye! Hope to see you again!");
    }

    public void listTasksOutput(ArrayList<Task> commands) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < commands.size(); i++) {
            System.out.println((i + 1) + ". " + commands.get(i));
        }
    }

    public void doneOutput(Task task, int tasksRemaining) {
        System.out.println("Good job! I've marked this task as completed:");
        System.out.println(task);
        System.out.println("You now have " + tasksRemaining + " tasks to complete");
    }

    public void dottedLine() {
        System.out.println("............................................................");
    }

    public void line() {
        System.out.println("------------------------------------------------------------");
    }

    public void removeOutput(Task task, int tasksRemaining) {
        System.out.println("Okay! I have removed this task for you:");
        System.out.println(task);
        System.out.println("You now have " + tasksRemaining + " tasks in your list.");
    }
}
