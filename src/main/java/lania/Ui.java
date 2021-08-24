package lania;

import lania.LaniaException;
import lania.Task;
import lania.TaskList;

public class Ui {

    public void updateMessage(TaskList taskList, Task t) {
        System.out.println("lania.Lania has added: ");
        System.out.println(t);
        System.out.println("Great! Now you have " + taskList.size() + (taskList.size() == 1 ? " task" : " tasks") + " in your list.");
    }

    public void greetingMessage() {
        System.out.println("Hello I am lania.Lania! How may lania.Lania be of assistance?");
        System.out.println("Enter 'bye' to exit");
    }

    public void goodbyeMessage() {
        System.out.println("Bye. lania.Lania looks forward to seeing you again!");
    }

    public void listMessage(TaskList taskList) {
        System.out.println("You have the following task(s):");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i));
        }
    }

    public void taskCompleteMessage(TaskList taskList, int i) {
        System.out.println("Good job! lania.Lania has marked this task as done:");
        System.out.println(taskList.get(i));
    }

    public void removeTaskMessage(TaskList taskList, Task t) {
        System.out.println("Ok, lania.Lania has removed this task:");
        System.out.println(t);
        System.out.println("Great! Now you have " + taskList.size() + (taskList.size() == 1 ? " task" : " tasks") + " in your list.");
    }

    public void dateTimeExceptionMessage() {
        System.out.println("lania.Lania does not understand this data/time format. Please try again");
    }

    public void laniaExceptionMessage(LaniaException e) {
        System.out.println(e);
    }

    public void loadingErrorMessage() {
        System.out.println("lania.Lania encountered an error while loading the file.");
    }
}
