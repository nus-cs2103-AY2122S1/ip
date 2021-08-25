package duke;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    public UI(){};

    private static String logo = " ____        _ _\n"
            + "|  _ \\ _   _(_|_)\n"
            + "| | | | | | | | |\n"
            + "| |_| | |_| | | |\n"
            + "|____/ \\__,_|_|_|\n";


    public void greet(User user) {
        System.out.println(logo);
        System.out.println(String.format("Hello %s! I'm Duii, your personal assistant!", user.getUsername()));
        System.out.println("What do you need help with?");
    }

    public void showLoadingError() {
        System.out.println("Error starting up Duii.");
    }

    public String readCommand(Scanner sc) {
        return sc.nextLine().toLowerCase();
    }

    public void showLine() {
        System.out.println("___________________");
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void notifyDone(Task doneTask) {
        System.out.println("You've finished the task? Good job!");
        System.out.println("This task has been marked as done:");
        System.out.println(doneTask.displayInfo());
    }

    public void notifyDelete(Task removedTask) {
        System.out.println("Okay! Removing the task:");
        System.out.println(removedTask.displayInfo());
    }

    public void notifyList(ArrayList<Task> taskArrList) {
        int listLength = taskArrList.size();
        System.out.println("Here's your current list:");
        for (int i = 0; i < listLength; i++) {
            System.out.println(String.format("%d. %s", i + 1, taskArrList.get(i).displayInfo()));
        }
    }

    /**
     * Updates the user that there are no matches in the list.
     */
    public void notifyNoMatching() {
        System.out.println("There were no keyword matches!");
    }

    /**
     * Updates the user of the tasks with the matching keywords in the list.
     *
     * @param taskArrList The ArrayList of tasks with matching keywords.
     */
    public void notifyMatchingList(ArrayList<Task> taskArrList) {
        int listLength = taskArrList.size();
        System.out.println("Here's are the matching results:");
        for (int i = 0; i < listLength; i++) {
            System.out.println(String.format("%d. %s", i + 1, taskArrList.get(i).displayInfo()));
        }
    }

    public void notifyAdd(ArrayList<Task> taskArrList) {
        int listLength = taskArrList.size();
        Task newTask = taskArrList.get(listLength - 1);
        System.out.println("New Task? I've added it to the list:");
        System.out.println(newTask.displayInfo());
        System.out.println(String.format("Now you have %d task(s) in the list.", listLength));
    }

    public void printPrevSession(TaskList tasks, User user) {
        if (!user.isNewUser()) {
            ArrayList<Task> taskArrList = tasks.getAllTasks();
            int listLength = taskArrList.size();
            if (listLength == 0) {
                System.out.println("You have no outstanding tasks from the previous session." +
                        " What a productivity master!");
            } else {
                System.out.println("These tasks are from the previous session:");
                for (int i = 0; i < taskArrList.size(); i++) {
                    System.out.println(String.format("%d. %s", i + 1, taskArrList.get(i).displayInfo()));
                }
            }
        } else {
            user.hasLoginBefore();
        }
    }

    public void exit() {
        System.out.println("You're going already? Hope to see you again soon!");
    }
}
