package duke;

import java.util.ArrayList;

/**
 * Represents the user interface and helps prints long messages for the user.
 */
public class Ui {

    /**
     * Prints the welcoming message for the user and the current tasks in the user's todo list.
     *
     * @param taskList The user's current todo list.
     */
    public void startMessage(ArrayList<Task> taskList) {
        System.out.println("Hello I am Duke.\nWhat can I do for you?");
        System.out.println();
        if (taskList.size() > 0) {
            System.out.println("Current number of tasks: " + taskList.size());
            iterateTaskList(taskList);
            System.out.println();
        }
    }

    /**
     * Prints the current tasks in the user's todo list.
     *
     * @param taskList The user's current todo list.
     */
    public void iterateTaskList(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println("List is empty!");
            System.out.println();
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task temp = taskList.get(i);
            System.out.printf("%s. %s\n", i + 1, temp);
        }
        System.out.println();
    }

    /**
     * Prints the goodbye message for the user.
     */
    public void endMessage() {
        System.out.println("Bye! See you next time!");
    }


}
