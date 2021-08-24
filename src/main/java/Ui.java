import java.util.ArrayList;

public class Ui {

    public void updateMessage(ArrayList<Task> taskArrayList, Task t) {
        System.out.println("Lania has added: ");
        System.out.println(t);
        System.out.println("Great! Now you have " + taskArrayList.size() + (taskArrayList.size() == 1 ? " task" : " tasks") + " in your list.");
    }

    public void greetingMessage() {
        System.out.println("Hello I am Lania! How may Lania be of assistance?");
        System.out.println("Enter 'bye' to exit");
    }

    public void goodbyeMessage() {
        System.out.println("Bye. Lania looks forward to seeing you again!");
    }

    public void listMessage(ArrayList<Task> taskArrayList) {
        System.out.println("You have the following task(s):");
        for (int i = 0; i < taskArrayList.size(); i++) {
            System.out.println(i + 1 + "." + taskArrayList.get(i));
        }
    }

    public void taskCompleteMessage(ArrayList<Task> taskArrayList, int i) {
        i--;
        System.out.println("Good job! Lania has marked this task as done:");
        System.out.println(taskArrayList.get(i));
    }

    public void removeTaskMessage(ArrayList<Task> taskArrayList, int i) {
        i--;
        System.out.println("Ok, Lania has removed this task:");
        System.out.println(taskArrayList.get(i));
        System.out.println("Great! Now you have " + taskArrayList.size() + (taskArrayList.size() == 1 ? " task" : " tasks") + " in your list.");
    }

    public void dateTimeExceptionMessage() {
        System.out.println("Lania does not understand this data/time format. Please try again");
    }

    public void laniaExceptionMessage(LaniaException e) {
        System.out.println(e);
    }

    public void loadingErrorMessage() {
        System.out.println("Lania encountered an error while loading the file.");
    }
}
