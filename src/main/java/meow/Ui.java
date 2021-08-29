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
    public String greet() {
        return "Meow: Hi human, I'm your cat Meow~ What can I do for you?";
    }

    /**
     * Prints the goodbye message from the chat bot cat Meow.
     */
    public String exit() {
        return "Meow: Bye human, see you soon! Your cat Meow is going to sleep now~";
    }

    /**
     * Displays all the tasks in the task list that the user has entered.
     *
     * @param tasksList The task list that stores the tasks input by the user.
     * @throws NoItemInTheListException If the tasksList is empty.
     */
    public String displayList(List<Task> tasksList) throws NoItemInTheListException {
        int len = tasksList.size();
        if (len > 0) {
            String list = "";
            for (int i = 0; i < len; i++) {
                Task task = tasksList.get(i);
                list = list + (i + 1 + ". " + task.toString() + "\n");
            }

            return "Here are the tasks in your list:\n" + list;
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
    public String displaySearchList(List<Task> filteredList) throws NoSearchResultException {
        int len = filteredList.size();
        if (len > 0) {
            String list = "";
            for (int i = 0; i < len; i++) {
                Task task = filteredList.get(i);
                list = list + (i + 1 + ". " + task.toString() + "\n");
            }
            return "Here are the matching tasks in your list:\n" + list;
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
    public String printTaskList(List<Task> tasksList, Task taskAdded) {
        int taskListLength = tasksList.size();
        String task = taskListLength <= 1 ? " task " : " tasks ";
        return "Got it. I've added this task:\n"
                + taskAdded.toString() + "\n"
                + "Now you have "
                + taskListLength
                + task
                + "in the list.";

    }

}
