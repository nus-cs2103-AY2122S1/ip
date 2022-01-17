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

    public String retrieveList(String listOfTasks) {
        if (listOfTasks.length() == 0) {
            return "You currently have no tasks!";
        } else {
            return "Here are the tasks in your list:\n" + listOfTasks;
        }
    }

    public String addTask(Task task) {
        int taskListSize = TaskList.getTaskList().size();
        if (taskListSize == 1) {
            return String.format("Got it. I've added this task:\n %s\nNow you have 1 task in the list.",
                    task.toString());
        } else {
            return String.format("Got it. I've added this task:\n %s\nNow you have %d tasks in the list.",
                    task.toString(), taskListSize);
        }
    }

    /**
     * Returns a message to the user indicating the task has been marked as done.
     *
     * @param task The task to be marked as done.
     * @return A message to the user indicating the task has been marked as done.
     */
    public String doneTask (Task task) {
        return String.format("Nice! I've marked this task as done:\n %s", task.toString());
    }

    /**
     * Returns a message to the user indicating the task has been deleted.
     *
     * @param task The task to be deleted.
     * @return A message to the user indicating the task has been deleted.
     */
    public String deleteTask(Task task) {
        return String.format("Noted. I've removed this task:\n %s\nNow you have %d tasks in the list.",
                task.toString(), TaskList.getTaskList().size());
    }

    /**
     * UI message for the find command.
     *
     * @param tasksWithKeywordList The list of tasks with the keyword specified.
     * @return The UI message for the list of tasks containing the keyword specified by the user.
     */
    public String findTask(String tasksWithKeywordList) {
        if (tasksWithKeywordList.length() == 0) {
            return "You currently have no tasks!";
        } else {
            return "Here are the matching tasks in your list:\n" + tasksWithKeywordList;
        }
    }

    /**
     * Returns a message to the user for when a task has been tagged.
     *
     * @param taskToBeTagged The task to be tagged.
     * @return A message to the user indicating the task has tagged with the specified tag.
     */
    public String taggedTask(Task taskToBeTagged) {
        return String.format("I've tagged this task as:\n %s\n",
                taskToBeTagged.toString());
    }

    public String getError(String e) {
        return e;
    }

    public void showLoadingError() {
        System.out.println("Sorry! There was an error loading your tasks! Please try again!");
    }

    /**
     * Returns the error message for incorrect date and time input.
     *
     * @param message The DateTimeParseException error.
     * @return The error message to the user indicating incorrect date and time input.
     */
    public String getDateTimeErrorMessage(String message) {
        return "There was an error with the format of your command!\n" +
                "Please enter the date as dd-MM-yyy along with the time as HHmm.";
    }


    public String help() {
        return "Here are the commands you can enter to Duke:\n1. list: list all your tasks\n" +
                "2. todo: add a todo task\n" +
                "3. deadline: add a deadline task\n" +
                "4. event: add an event task\n" +
                "5. done: mark a task as done\n" +
                "6. delete: delete a task\n" +
                "7. find: find tasks containing a keyword\n" +
                "8. tag: tag a task\n" +
                "9. bye: exit Duke";
    }
}
