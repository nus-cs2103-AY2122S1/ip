package duke;

import static java.lang.Integer.parseInt;

/**
 * Represents a class that deals with interaction with the user.
 */
public class Ui {

    /**
     * Prints the welcome message.
     */
    public void printWelcome() {
        System.out.println(
                "Hello! I'm Amped :) \n"
                        + "Type: \n"
                        + "1. A task (todo/deadline/event) followed by description to add tasks \n"
                        + "   e.g \"deadline submit homework /by Sunday 12 pm\" \n"
                        + "2. \"list\" to see the list of tasks \n"
                        + "3. \"find [keyword]\" to find a matching task \n"
                        + "4. \"done [number]\" to mark a particular task as done \n"
                        + "5. \"delete [number]\" to delete a particular task \n"
                        + "6. \"bye\" to exit"
        );
    }

    /**
     * Prints the goodbye message.
     */
    public void printBye() {
        System.out.println("Good Bye. Have a nice day!");
    }

    /**
     * Informs the user that a task has been marked as done.
     */
    public void printDone() {
        System.out.println("Nice! I've marked this task as done: ");
    }

    /**
     * Informs the user that a task has been removed from the list.
     */
    public void printRemove() {
        System.out.println("Noted. I've removed this task: ");
    }

    /**
     * Informs the user that a task has been added.
     * @param ls The list of tasks.
     */
    public void printAddTask(TaskList ls) {
        ls.printAddTask();
    }

    /**
     * Displays the list of tasks.
     * @param ls The list of tasks.
     */
    public void displayList(TaskList ls) {
        ls.displayList();
    }

    /**
     * Prints a task at a certain index in the list.
     * @param ls The list of tasks.
     * @param index The index of the tasks in the list.
     */
    public void printCurrentTask(TaskList ls, int index) {
        System.out.println(ls.getTask(index).toString());
    }
}
