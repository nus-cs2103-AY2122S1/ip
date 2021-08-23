package jwbot.ui;

import jwbot.data.TaskList;
import jwbot.data.task.Task;

import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    private final static String greeting = "Wassup bro! I'm jwbot.JWBot\n"
            + "How can I help you?\n";
    private final static String byeMessage = "You leaving already? See you soon bro!";

    public void showWelcome() {
        System.out.println(greeting);
    }

    public void showBye() {
        System.out.println(byeMessage);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Oops bro, there's an error with loading saved data");
    }

    public void showDeleteSuccessMessage(Task task, int listSize) {
        System.out.println("OK Bro, I noted you've deleted this task:\n" +
                task);
        System.out.println("So bro, now you have " + listSize + " tasks stored in the list!");
    }

    public void showList(TaskList tasks) {
        System.out.println("OK bro, the tasks in your list are: ");
        for (int i = 1; i < tasks.getSize() + 1; i++) {
            System.out.println(i + ". " + tasks.getTask(i - 1));
        }
        System.out.println("Bro, now you have " + tasks.getSize() + " task(s) stored in the list!");
    }

    public void showDoneSuccessMessage(Task doneTask) {
        System.out.println("OK Bro, I noted you've done this task:\n" +
                doneTask);
    }

    public void showAddTaskSuccessMessage(Task task) {
        System.out.println("OK bro, I just added: " + task);
    }

    public void showError(String error) {
        System.out.println(error);
    }

}
