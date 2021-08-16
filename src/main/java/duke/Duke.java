package duke;

import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Driver class to simulate the 'Annie' chat bot program.
 *
 * @author limzk126
 * @version Level-5
 */
public class Duke {
    private final String LINE = "_________________________________________________________________"
            + "_______________________________________________________________________\n";
    private final String WELCOME_MSG = "Hi I am Annie!\nHow can I assist you?";
    private final String GOODBYE_MSG = "Bye. See you soon!";
    private final String LIST_MSG = "Here are the tasks in your list:\n";
    private final String ADD_MSG = "Gotcha. I've added this task:\n";
    private final String NUMTASK_MSG = "You have %d tasks in your list now.\n";
    private final String DONE_MSG = "I have marked this task as done: \n";

    // List to store user task inputs.
    private final List<Task> taskList = new ArrayList<>();

    // Flag to indicate if program is ended by user.
    private boolean isEnded = false;

    /*
     * Prints a horizontal line, followed by the text input by user on a newline,
     * then finally a horizontal line on a newline.
     */
    private void printText(String text) {
        System.out.printf("%s", LINE);
        System.out.println(text);

        if (!text.equals(GOODBYE_MSG)) {
            System.out.printf(NUMTASK_MSG, taskList.size());
        }

        System.out.printf("%s\n", LINE);
    }

    // Prints a numbered checklist of the user's task.
    private void printList() {
        int taskNum = 0;

        System.out.printf("%s", LINE);
        System.out.printf("%s", LIST_MSG);

        // Print list.
        for (Task task : taskList) {
            System.out.printf("%d.%s\n", ++taskNum, task.toString());
        }

        System.out.printf("%s\n", LINE);
    }

    /*
     * Marks the specified task as done and notifies the user that it has successfully
     * done so.
     */
    private void completeTask(String text) throws DukeException {
        String stringTaskNum = text.substring(text.indexOf(' ') + 1);

        int num = Integer.parseInt(stringTaskNum);

        // Check if task number is within bounds.
        if (num <= 0 || num > taskList.size()) {
            throw new InvalidIndexException();
        }

        Task task = taskList.get(num - 1);
        task.markDone();

        // Message to notify user.
        String message = DONE_MSG + task;
        printText(message);
    }

    // Adds task without deadline to list and notifies user.
    private void addTodo (String text) throws DukeException {
        // Check format. Throw exception if invalid.
        if (!correctNoDateFormat(text)) {
            throw new InvalidNoDateException();
        }

        String taskName = text.substring(text.indexOf(' ') + 1);

        // Create and add task.
        Todo task = new Todo(taskName);
        taskList.add(task);

        // Message to notify user.
        String message = ADD_MSG + task;
        printText(message);
    }

    // Adds task with deadline to list and notifies user.
    private void addDeadline(String text) throws DukeException {
        // Check format. Throw exception if invalid.
        if (!correctDateFormat(text)) {
            throw new InvalidDateException();
        }

        // Split the command by the first '/'.
        String[] str = new String[2];
        str[0] = text.substring(0, text.indexOf('/'));
        str[1] = text.substring(text.indexOf('/') + 1);

        // Retrieve name of task and its date/time.
        String taskName = str[0].substring(str[0].indexOf(' ') + 1).trim();
        String taskDate = str[1].trim();

        // Create and add task.
        Deadline task = new Deadline(taskName, taskDate);
        taskList.add(task);

        // Message to notify user.
        String message = ADD_MSG + task;
        printText(message);
    }

    // Adds event to list and notifies user.
    private void addEvent(String text) throws DukeException {
        // Check format. Throw exception if invalid.
        if (!correctDateFormat(text)) {
            throw new InvalidDateException();
        }

        // Split the command by the first '/'.
        String[] str = new String[2];
        str[0] = text.substring(0, text.indexOf('/'));
        str[1] = text.substring(text.indexOf('/') + 1);

        // Retrieve name of task and its date/time.
        String taskName = str[0].substring(str[0].indexOf(' ') + 1).trim();
        String taskDate = str[1].trim();

        // Create and add task.
        Event task = new Event(taskName, taskDate);
        taskList.add(task);

        // Message to notify user.
        String message = ADD_MSG + task;
        printText(message);
    }

    // Checks if the command with date is in valid format.
    private boolean correctDateFormat (String text) {
        String regex = "(.*) (.*)/(.*)";
        return text.matches(regex);
    }

    // Checks if the command with no date is in valid format.
    private boolean correctNoDateFormat (String text) {
        String regex = "(.*) (.*)";
        return text.matches(regex);
    }

    // Processes text to find out what command user has issued.
    private void parseText(String text) throws DukeException {
        if (text.equals("bye")) {
            // End program.
            isEnded = true;
        } else if (text.equals("list")) {
            // List all previous text.
            printList();
        } else if (text.matches("done(.*)")) {
            // Complete task.
            completeTask(text);
        } else if (text.matches("todo(.*)")) {
            // Add task without deadline.
            addTodo(text);
        } else if (text.matches("deadline(.*)")) {
            // Add task with deadline.
            addDeadline(text);
        } else if (text.matches("event(.*)")) {
            // Add an event.
            addEvent(text);
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Method to simulate the program.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        String textInput;

        // Program starts. Say hello.
        printText(WELCOME_MSG);

        // While loop to continuously receive user input.
        while (!isEnded) {
            textInput = sc.nextLine().trim();
            try {
                parseText(textInput);
            } catch (DukeException e) {
                printText(e.toString());
            }
        }

        // Program ends. Say goodbye.
        printText(GOODBYE_MSG);

        sc.close();
    }

    /**
     * Driver method to start program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
