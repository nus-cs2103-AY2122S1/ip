import java.util.Scanner;
import java.lang.String;

public class Ui {

    private final static String UNDERLINE = "_________________________________";
    private final static String INDENTATION ="  ";


    public void showLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    public static void showWelcome(){
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Hello! I'm Duke\n" +
                INDENTATION + "What can I do for you?");
        System.out.println(INDENTATION + UNDERLINE);

    }

    public static void exit() {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        System.out.println(INDENTATION + UNDERLINE);

    }

    public void showLoadingError() {
        System.out.println("Loading error! please try again");
    }

    public String getCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showDoneMessgae(TaskList task, int numRemoved) {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(INDENTATION + " " + task.get(numRemoved));
        System.out.println(INDENTATION + UNDERLINE);

    }

    public void showDeleteMessgae(TaskList task, Task taskDeleted) {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Noted. I've removed this task:");
        System.out.println(INDENTATION + " " + taskDeleted);
        System.out.println(INDENTATION + "Now you have " + task.size()  + " tasks in the list.");
        System.out.println(INDENTATION + UNDERLINE);

    }

    public void showAddOnTask(TaskList task, int numAdded) {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Got it. I've added this task:");
        System.out.println(INDENTATION + INDENTATION + task.get(numAdded)); //toString in Deadline or Event
        System.out.println(INDENTATION + "Now you have " + (numAdded + 1) + " tasks in the list.");
        System.out.println(INDENTATION + UNDERLINE);
    }

    public void  showListDetails(TaskList task) {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Here are the tasks in your list:");
        for (int i = 0; i < task.size(); i ++) {
            System.out.println(INDENTATION + (i + 1) + "." + INDENTATION + task.get(i));
        }
        System.out.println(INDENTATION + UNDERLINE);
    }

}
