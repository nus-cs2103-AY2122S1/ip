package duke;

import static java.lang.Integer.parseInt;

public class Ui {

    /**
     * A method to print the welcome message
     */
    public void printWelcome() {
        System.out.println(
                "Hello! I'm Amped :) \n"
                        + "Type: \n"
                        + "1. A task (todo/deadline/event) followed by description to add tasks \n"
                        + "   e.g \"deadline submit homework /by Sunday 12 pm\" \n"
                        + "2. \"list\" to see the list of tasks \n"
                        + "3. \"done [number]\" to mark a particular task as done \n"
                        + "4. \"delete [number]\" to delete a particular task \n"
                        + "5. \"bye\" to exit"
        );
    }

    /**
     * A method to print the goodbye message
     */
    public void printBye() {
        System.out.println("Good Bye. Have a nice day!");
    }

    /**
     * A method to inform the user that a task has been marked as done
     */
    public void printDone() {
        System.out.println("Nice! I've marked this task as done: ");
    }

    /**
     * A method to inform the user that a task has been removed from the list
     */
    public void printRemove() {
        System.out.println("Noted. I've removed this task: ");
    }

    /**
     * A method to inform the user that a task has been added
     * @param ls The list of tasks
     */
    public void printAddTask(TaskList ls) {
        ls.printAddTask();
    }

    /**
     * A method to display the list of tasks
     * @param ls The list of tasks
     */
    public void displayList(TaskList ls) {
        ls.displayList();
    }

    /**
     * A method to print a task at a certain index in the list
     * @param ls The list of tasks
     * @param index The index of the tasks in the list
     */
    public void printCurrentTask(TaskList ls, int index) {
        System.out.println(ls.getTask(index).toString());
    }
}
