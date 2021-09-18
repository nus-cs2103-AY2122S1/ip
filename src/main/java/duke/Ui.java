package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private static final String GREET = "Hi there! My name's Duke, how can I help you today?";
    private static final String EXIT = "Bye! See you next time!";

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String greet () {
        return GREET;
    }

    public String exit () {
        return EXIT;
    }

    public String echoCommand () {
        return sc.nextLine();
    }

    public String retrieveList() {
        ArrayList<Task> list = TaskList.getTaskList();
        if (list.size() == 0) {
            return "You currently have no tasks!";
        } else {
            String userList = "";
            for (Task task : list) {
                userList =  userList + (list.indexOf(task) + 1) + ". " + task.toString() + "\n";
            }
            return "Here are the tasks in your list:\n" + userList;
        }
    }

    public String addTask(Task task) {
        return String.format("Got it. I've added this task:\n %s\nNow you have %d tasks in the list.",
                task.toString(), TaskList.getTaskList().size());
    }

    /**
     * Returns a message to the user indicating the task has been marked as done.
     *
     * @param task
     * @return
     */
    public String doneTask (Task task) {
        return String.format("Nice! I've marked this task as done:\n %s", task.toString());
    }

    /**
     * Returns a message to the user indicating the task has been deleted.
     *
     * @param task
     * @return
     */
    public String deleteTask(Task task) {
        return String.format("Noted. I've removed this task:\n %s\nNow you have %d tasks in the list.",
                task.toString(), TaskList.getTaskList().size());
    }

    public String getError(String e) {
        return e;
    }

    /**
     * UI message for the find command.
     *
     * @param keyword
     * @return the list of tasks containing the keyword specified by the user
     */
    public String findTask(String keyword) {
        ArrayList<Task> list = TaskList.getTaskList();
        if (list.size() == 0) {
            return "You currently have no tasks!";
        } else {
            String tasksWithKeywordList = "";
            int index = 0;
            for (Task task : list) {
                if (task.getDescription().contains(keyword)) {
                    index++;
                    tasksWithKeywordList = tasksWithKeywordList + (index)
                            + ". " + task.toString() + "\n";
                }
            }
            return "Here are the matching tasks in your list:\n" + tasksWithKeywordList;
        }

    }

    public void showLoadingError() {
        System.out.println("Sorry! There was an error loading your tasks! Please try again!");
    }
}
