/**
 * @author Hang Zelin
 *
 * Ui Part of Duke Programme. This programme mainly deals with all the user interactions.
 * It will print out the information for each type of operation execution. And it will also accept users'
 * input for each round of task execution.
 */
package duke.uimanager;

import duke.command.Parser;
import duke.excpetions.DukeException;
import duke.task.TaskList;

import java.util.ArrayList;

public class Ui {

    /**
     * Prints Hello Message to users.
     */
    public String helloMessage() {
        String helloMessage = "Hello! I'm Duke\n"
                + "What can I do for you?\n";

        return helloMessage;
    }

    /**
     * Prints Goodbye Message to users.
     */
    public String goodbyeMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";

        return goodbyeMessage;
    }

    /**
     * Prints all the tasks in a given list.
     *
     * @param tasks TaskList contains all the tasks to be printed.
     */
    public String printList(TaskList tasks) {
        String text = "";
        text += "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            text += i + 1 + "." + tasks.get(i).getTaskInfo() + "\n";
        }
        return text;
    }

    /**
     * Prints the Ui info for the MarkDone method in Tasklists.
     *
     * @param parsedTask Detailed info for a task that is parsed into a specific format.
     */
    public String markDone(String parsedTask) {
        String text = "";
        text += "Nice! I've marked this task as done:\n" + " " + parsedTask +"\n";

        return text;
    }

    /**
     * Prints the Ui info for the Delete method in TaskList.
     *
     * @param parsedTask Detailed info for a task that is parsed into a specific format
     * @param size Size of the TaskList.
     */
    public String delete(String parsedTask, int size) {
        String text = "";
        text += "Noted. I've removed this task:\n" + " " + parsedTask + "\nNow you have " + size + " tasks in the list.\n";

        return text;
    }

    /**
     * Prints the Ui info for the add method in Tasklists.
     *
     * @param parsedTask Detailed info for a task that is parsed into a specific format
     * @param size Size of the TaskList.
     */
    public String add(String parsedTask, int size) {
        String text = "";
        text += "Got it. I've added this task: \n" + "\n " + parsedTask
                + "\nNow you have " + size + ""
                + " tasks in the list.\n";

        return text;
    }

    /**
     * Prints the Ui info for the getSpecificDateEvent method in Tasklists.
     */
    public String getSpecificDateEvent() {
        return "Here are all the tasks taking place on the date you give me:";
    }

    /**
     * Prints the Ui info for the FindTask method in Tasklists.
     */
    public String findTasks() {
        return "Here are the matching tasks in your list:\n";
    }

    /**
     * Returns the key 4 information from users' input encapsulated in a ArrayList of String.
     * They are: operationType, task, time, index. They will be useful when executing in Duke programme.
     *
     * @return Size of 4 ArrayList contains Message of operationType, task, time and index.
     * @throws DukeException Throws when the input cannot be parsed.

     */
    public ArrayList<String> getInputForARound(String input) throws DukeException {
        System.out.println();
        Parser parser;
        ArrayList<String> parsedMessageList = new ArrayList<>();

        parser = new Parser(input);

        parsedMessageList.add(parser.getOperationType());
        parsedMessageList.add(parser.getTask());
        parsedMessageList.add(parser.getTime());
        parsedMessageList.add(parser.getIndex().toString());

        return parsedMessageList;
    }

    /**
     * Shows the Loading Error Text.
     */
    public String showLoadingError() {
        return "Cannot Load From Data.\n";
    }

    /**
     * Shows the Saving Error Text.
     */
    public String showSavingError() {
        return "Cannot Save the Data.\n";
    }
}
