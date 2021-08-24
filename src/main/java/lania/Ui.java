package lania;

import lania.exception.LaniaException;
import lania.task.Task;
import lania.task.TaskList;

public class Ui {

    public void showUpdateMessage(TaskList taskList, Task t) {
        System.out.println("Lania has added: ");
        System.out.println(t);
        System.out.println("Great! Now you have "
                + taskList.size() + (taskList.size() == 1 ? " task" : " tasks") + " in your list.");
    }

    public void showGreetingMessage() {
        System.out.println("Hello I am Lania! How may Lania be of assistance?");
        System.out.println("Enter 'bye' to exit");
    }

    public void showExitMessage() {
        System.out.println("Bye. Lania looks forward to seeing you again!");
    }

    public void showListMessage(TaskList taskList) {
        System.out.println("You have the following task(s):");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i));
        }
    }

    public void showCompleteMessage(TaskList taskList, int i) {
        System.out.println("Good job! Lania has marked this task as done:");
        System.out.println(taskList.get(i));
    }

    public void showRemoveMessage(TaskList taskList, Task t) {
        System.out.println("Ok, Lania has removed this task:");
        System.out.println(t);
        System.out.println("Great! Now you have "
                + taskList.size() + (taskList.size() == 1 ? " task" : " tasks") + " in your list.");
    }

    public void showDateTimeException() {
        System.out.println("Lania does not understand this date/time format. Please try again");
    }

    public void showLaniaException(LaniaException e) {
        System.out.println(e);
    }

    public void showError() {
        System.out.println("Lania encountered an error while loading the file.");
    }
}
