package meow;

import java.util.List;

/**
 * Represents a user interface that interacts with the users
 * by printing the response from the chat bot cat Meow.
 */
public class Ui {
    /**
     * A public constructor to initialize an meow.Ui object.
     */
    public Ui() {

    }

    /**
     * Prints the greeting message from the chat bot cat Meow.
     */
    public void greet() {

        System.out.println(
                "------------------------------------------------------------------------------\n"
                        +
                        "Meow: Hi human, I'm your cat Meow~ What can I do for you?\n"
                        +
                        "------------------------------------------------------------------------------"
        );
    }

    /**
     * Prints the goodbye message from the chat bot cat Meow.
     */
    public void exit() {

        System.out.println(
                "------------------------------------------------------------------------------\n"
                        +
                        "Meow: Bye human, see you soon! Your cat Meow is going to sleep now~\n"
                        +
                        "------------------------------------------------------------------------------"
        );
    }

    /**
     * Displays all the tasks in the task list that the user has entered.
     *
     * @param tasksList The task list that stores the tasks input by the user.
     * @throws NoItemInTheListException If the tasksList is empty.
     */
    public void displayList(List<Task> tasksList) throws NoItemInTheListException {
        int len = tasksList.size();
        if (len > 0) {
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < len; i++) {
                Task task = tasksList.get(i);
                System.out.println(i + 1 + ". " + task.toString());
            }
            System.out.println("------------------------------------------------------------------------------");
        } else {
            throw new NoItemInTheListException();
        }
    }

    /**
     * Displays all the tasks in the task list that the user has entered.
     *
     * @param filteredList The task list that stores the tasks input by the user.
     * @throws NoSearchResultException If there is not matching result.
     */
    public void displaySearchList(List<Task> filteredList) throws NoSearchResultException {
        int len = filteredList.size();
        if (len > 0) {
            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < len; i++) {
                Task task = filteredList.get(i);
                System.out.println(i + 1 + ". " + task.toString());
            }
            System.out.println("------------------------------------------------------------------------------");
        } else {
            throw new NoSearchResultException();
        }
    }

    /**
     * Displays the new task that has been added to the list and the number
     * of tasks stored in the list.
     *
     * @param tasksList The task list that stores the tasks input by the user.
     * @param taskAdded The new task that has been added to the list.
     */
    public void printTaskList(List<Task> tasksList, Task taskAdded) {
        int taskListLength = tasksList.size();
        String task = taskListLength <= 1 ? " task " : " tasks ";
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println(taskAdded.toString());
        System.out.println("Now you have " + taskListLength + task + "in the list.");
        System.out.println("------------------------------------------------------------------------------");
    }

}
