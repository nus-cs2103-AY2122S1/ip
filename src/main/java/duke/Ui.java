package duke;

import java.util.ArrayList;

/**
 * Represents a class that deals with interaction with the user.
 */
public class Ui {

    /**
     * Prints the welcome message.
     * @return A string containing a welcome message.
     */
    public String printWelcome() {
        return "Hello! I'm Duke :) \n"
                        + "Type: \n"
                        + "1. A task (todo/deadline/event) followed by description to add tasks \n"
                        + "   e.g \"deadline submit homework /by Sunday 12 pm\" \n"
                        + "2. \"list\" to see the list of tasks \n"
                        + "3. \"find [keyword]\" to find a matching task \n"
                        + "4. \"search [keyword]\" to find a matching keyword contained in a task \n"
                        + "5. \"done [number]\" to mark a particular task as done \n"
                        + "6. \"delete [number]\" to delete a particular task \n"
                        + "7. \"bye\" to exit";
    }

    /**
     * Prints the goodbye message.
     * @return A string containing a goodbye message.
     */
    public String printBye() {
        return "Good Bye. Have a nice day!";
    }

    /**
     * Informs the user that a task has been marked as done.
     * @return A string to show that a task has been marked as done.
     */
    public String printDone() {
        return "Nice! I've marked this task as done: ";
    }

    /**
     * Informs the user that a task has been removed from the list.
     * @return A string to show that a task has been removed.
     */
    public String printRemove() {
        return "Noted. I've removed this task: ";
    }

    /**
     * Informs the user that a task has been added.
     * @param ls The list of tasks.
     * @return A string that shows a task has been added.
     */
    public String printAddTask(TaskList ls) {
        assert ls != null : "List cannot be null";
        String str = "";
        str += "Got it. I've added this task: \n";
        str += ls.getTask(ls.getSize() - 1).toString() + "\n";
        str += "Now you have " + ls.getSize() + " tasks in the list. \n";
        return str;
    }

    /**
     * Displays the list of tasks.
     * @param ls The list of tasks.
     * @return A string that contains the list of tasks.
     */
    public String displayList(TaskList ls) {
        assert ls != null : "List cannot be null";
        if (ls.getSize() == 0) {
            return "You currently do not have any task!";
        } else {
            String str = "";
            str += "Here are the tasks in your list: \n ";
            for (int i = 0; i < ls.getSize(); i++) {
                str += (i + 1) + "." + ls.getTask(i).toString() + "\n";
            }
            return str;
        }
    }

    /**
     * Prints a task at a certain index in the list.
     * @param ls The list of tasks.
     * @param index The index of the tasks in the list.
     * @return A string representation of a task.
     */
    public String printCurrentTask(TaskList ls, int index) {
        assert ls != null : "List cannot be null";
        return ls.getTask(index).toString();
    }

    /**
     * Allows the user to find a task in the list.
     *
     * @param search The search term.
     * @return A string to show if a task is found in the list of not.
     */
    public String findTask(TaskList ls, String search) {
        ArrayList<Task> searchList = new ArrayList<>();
        for (int i = 0; i < ls.getSize(); i++) {
            String[] arr = ls.getTask(i).getDescription().split(" ");
            for (int j = 0; j < arr.length; j++) {
                if (arr[j].equals(search)) {
                    searchList.add(ls.getTask(i));
                    break;
                }
            }
        }
        if (searchList.size() == 0) {
            return "No matching tasks!";
        } else {
            String str = "";
            str += "Here are the matching task(s) in your list: \n";
            for (int i = 0; i < searchList.size(); i++) {
                str += (i + 1) + "." + searchList.get(i).toString() + "\n";
            }
            return str;
        }
    }

    /**
     * Allows the user to search for any keyword in the tasks.
     *
     * @param search The search term.
     * @return A string containing the list of tasks matching the keyword.
     */
    public String searchTask(TaskList ls, String search) {
        ArrayList<Task> searchList = new ArrayList<>();
        for (int i = 0; i < ls.getSize(); i++) {
            String[] arr = ls.getTask(i).getDescription().split(" ");
            for (int j = 0; j < arr.length; j++) {
                if (arr[j].contains(search)) {
                    searchList.add(ls.getTask(i));
                    break;
                }
            }
        }
        if (searchList.size() == 0) {
            return "No matching tasks!";
        } else {
            String str = "";
            str += "Here are the matching keyword(s) in your list: \n";
            for (int i = 0; i < searchList.size(); i++) {
                str += (i + 1) + "." + searchList.get(i).toString() + "\n";
            }
            return str;
        }
    }
}
