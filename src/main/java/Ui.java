package duke;

import java.util.Scanner;
import duke.task.Task;

public class Ui {

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    private String logo = " ____        _        \n"
    + "|  _ \\ _   _| | _____ \n"
    + "| | | | | | | |/ / _ \\\n"
    + "| |_| | |_| |   <  __/\n"
    + "|____/ \\__,_|_|\\_\\___|\n";

    public void showWelcome() {
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public void showLoadingError() {
        System.out.println("File Not Found");
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void printAdd(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(String.format("  %s", task.toString()));
        System.out.println(String.format("Now you have %d tasks in the list", tasks.size()));
    }

    public void printDone(TaskList tasks, int toComplete) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(String.format("  %s", tasks.get(toComplete)));
    }

    public void printDelete(TaskList tasks, int toDelete){
        System.out.println("Noted. I've removed this task: ");
        System.out.println(String.format("  %s", tasks.get(toDelete)));
        System.out.println(String.format("Now you have %d tasks in the list", tasks.size() - 1));
    }

    public void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }

    public void showLine() {
        System.out.println("_______");
    }

    public void printList(TaskList tasks) {
        System.out.println("Here are the tasks in your list: ");
        for(int i = 0; i < tasks.size(); i++){
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public String readCommand() {
        String input = sc.nextLine();
        return input;
    }
}