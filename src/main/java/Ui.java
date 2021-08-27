import java.util.Scanner;
import java.util.regex.Pattern;

// Deals with interactions with the user
public class Ui {
    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke\n What can I do for you?");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
        System.out.println("");
    }


    public void showError(String errorMsg) throws DukeException {
//        System.out.println(errorMsg);
        throw new DukeException(errorMsg);
    }
    /**
     * DONE
     * Method to call when user wishes to see a list of all events
     */
    public void listTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            Task currTask = taskList.get(i);
            System.out.println((i+1) + "." + currTask.toString());
        }
    }

    /**
     * DONE
     * Method to call when user wishes to stop the bot
     */
    public void stopMethod() {
        System.out.print("Bye. Hope to see you again soon!");
    }

    /**
     * Method to call when user wishes to mark an item as done
     */
    public void doneTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    /**
     * Method to call when user wishes to add a new task
     */
    public void addTask(Task task) {
        System.out.println("Got it. I have added this task:");
        System.out.println(task);
        System.out.println("Now you have " + TaskList.getSize() + " tasks in the list.");
    }

    /**
     * Method to call when user wishes to see a list of all events
     */
    public void deleteTask(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + TaskList.getSize() + " tasks in the list.");
    }
    public void showLoadingError() {
    }
}
