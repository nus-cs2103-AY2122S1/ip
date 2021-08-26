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
import java.util.Scanner;

public class Ui {
    private static final String LINE = "____________________________________________________________";


    /**
     * Prints a line for users.
     */
    public void printAline() {
        System.out.println(LINE);
    }

    /**
     * Prints Hello Message to users.
     */
    public void HelloMessage() {
        String helloMessage = "Hello! I'm Duke\n" +
                "What can I do for you?\n";

        System.out.println(LINE + "\n" + helloMessage + LINE);
    }

    /**
     * Prints Goodbye Message to users.
     */
    public void goodbyeMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
    }

    /**
     * Prints all the tasks in a given list.
     *
     * @param tasks TaskList contains all the tasks to be printed.
     */
    public void printList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i).getTaskInfo());
        }
    }

    /**
     * Prints the Ui info for the MarkDone method in Tasklists.
     *
     * @param parsedTask Detailed info for a task that is parsed into a specific format.
     */
    public void markDone(String parsedTask) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + parsedTask);
    }

    /**
     * Prints the Ui info for the Delete method in TaskList.
     *
     * @param parsedTask Detailed info for a task that is parsed into a specific format
     * @param size Size of the TaskList.
     */
    public void delete(String parsedTask, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + parsedTask);
        System.out.println("Now you have " + size + " tasks in the list.");
    }


    /**
     * Prints the Ui info for the add method in Tasklists.
     *
     * @param parsedTask Detailed info for a task that is parsed into a specific format
     * @param size Size of the TaskList.
     */
    public void add(String parsedTask, int size) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(" " + parsedTask);
        System.out.println("Now you have " + size + "" +
                " tasks in the list.");
    }



    /**
     * Prints the Ui info for the getSpecificDateEvent method in Tasklists.
     */
    public void getSpecificDateEvent() {
        System.out.println("Here are all the tasks taking place on the date you give me:");
    }

    /**
     * Prints the Ui info for the FindTask method in Tasklists.
     */
    public void findTasks() {
        System.out.println("Here are the matching tasks in your list:");
    }


    /**
     * Returns the line of input from users.
     *
     * @return Line of command input by users.
     */
    public String getInput() {
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }

    /**
     * Returns the key 4 information from users' input encapsulated in a ArrayList<String>.
     * They are: operationType, task, time, index. They will be useful when executing in Duke programme.
     *
     * @return Size of 4 ArrayList contains Message of operationType, task, time and index.
     * @throws DukeException Throws when the input cannot be parsed.

     */
    public ArrayList<String> getInputForARound() throws DukeException {
        System.out.println();
        Parser parser;
        ArrayList<String> parsedMessageList = new ArrayList<>();
        String message;

        message = getInput();
        parser = new Parser(message);

        parsedMessageList.add(parser.getOperationType());
        parsedMessageList.add(parser.getTask());
        parsedMessageList.add(parser.getTime());
        parsedMessageList.add(parser.getIndex().toString());

        return parsedMessageList;
    }

    /**
     * Shows the Loading Error Text.
     */
    public void showLoadingError() {
        System.out.println("Cannot Load From Data.");
    }

    /**
     * Shows the Saving Error Text.
     */
    public void showSavingError() {
        System.out.println("Cannot Save the Data.");
    }
}
