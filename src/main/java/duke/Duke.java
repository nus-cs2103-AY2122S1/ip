package duke;

import duke.exception.DukeException;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidIndexException;
import duke.exception.InvalidDateException;
import duke.exception.InvalidCommandException;

import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Driver class to simulate the 'Annie' chat bot program.
 *
 * @author limzk126
 * @version Level-7
 */
public class Duke {
    private final String LINE = "_________________________________________________________________"
            + "_______________________________________________________________________\n";
    private final String WELCOME_MSG = "Hi I am Annie!\nHow can I assist you?";
    private final String GOODBYE_MSG = "Bye. See you soon!";
    private final String LIST_MSG = "Here are the tasks in your list:\n";
    private final String ADD_MSG = "Gotcha. I've added this task:\n";
    private final String NUMTASK_MSG = "Your current task count: ";
    private final String DONE_MSG = "I have marked this task as done:\n";
    private final String DELETE_MSG = "I have deleted this task:\n";
    private final String FILE_PATH = "C:\\Users\\Chu Heng 2\\Desktop\\cs2103T\\ip\\data\\duke.txt";

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

        int num;
        // handles non integer value input for task number.
        try {
            num = Integer.parseInt(stringTaskNum);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }

        // Check if task number is within bounds.
        if (num <= 0 || num > taskList.size()) {
            throw new InvalidIndexException();
        }

        Task task = taskList.get(num - 1);
        task.markDone();

        // Message to notify user.
        printText(DONE_MSG + task + NUMTASK_MSG + taskList.size());
    }

    // Deletes task from list.
    private void deleteTask(String text) throws DukeException {
        String stringTaskNum = text.substring(text.indexOf(' ') + 1);

        int num;
        // handles non integer value input for task number.
        try {
            num = Integer.parseInt(stringTaskNum);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }

        // Check if task number is within bounds.
        if (num <= 0 || num > taskList.size()) {
            throw new InvalidIndexException();
        }

        Task task = taskList.get(num);
        taskList.remove(num);

        // Message to notify user.
        printText(DELETE_MSG + task + NUMTASK_MSG + taskList.size());
    }

    // Adds task without deadline to list and notifies user.
    private void addTodo (String text) throws DukeException {
        // Check format. Throw exception if invalid.
        if (!text.matches("(.*) (.*)")) {
            throw new InvalidFormatException();
        }

        String taskName = text.substring(text.indexOf(' ') + 1);

        // Create and add task.
        Todo task = new Todo(taskName);
        taskList.add(task);

        // Message to notify user.
        printText(ADD_MSG + task + NUMTASK_MSG + taskList.size());
    }

    // Adds task with deadline to list and notifies user.
    private void addDeadline(String text) throws DukeException {
        // Check format. Throw exception if invalid.
        if (!text.matches("(.*) (.*)/(.*)")) {
            throw new InvalidFormatException();
        }

        // Split the command by the first '/'.
        String[] str = new String[2];
        str[0] = text.substring(0, text.indexOf('/'));
        str[1] = text.substring(text.indexOf('/') + 1);

        // Retrieve name of task and its date/time.
        String taskName = str[0].substring(str[0].indexOf(' ') + 1).trim();
        String taskDate = str[1].trim();

        // Check date validity.
        if (!DateTime.isValidDate(taskDate)) {
            throw new InvalidDateException();
        }

        // Create and add task.
        Deadline task = new Deadline(taskName, taskDate);
        taskList.add(task);
        // Message to notify user.
        printText(ADD_MSG + task + NUMTASK_MSG + taskList.size());
    }

    // Adds event to list and notifies user.
    private void addEvent(String text) throws DukeException {
        // Check format. Throw exception if invalid.
        if (!text.matches("(.*) (.*)/(.*)")) {
            throw new InvalidFormatException();
        }

        // Split the command by the first '/'.
        String[] str = new String[2];
        str[0] = text.substring(0, text.indexOf('/'));
        str[1] = text.substring(text.indexOf('/') + 1);

        // Retrieve name of task and its date/time.
        String taskName = str[0].substring(str[0].indexOf(' ') + 1).trim();
        String taskDate = str[1].trim();

        // Check date validity.
        if (!DateTime.isValidDate(taskDate)) {
            throw new InvalidDateException();
        }

        // Create and add task.
        Event task = new Event(taskName, taskDate);
        taskList.add(task);

        // Message to notify user.
        printText(ADD_MSG + task + NUMTASK_MSG + taskList.size());
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
        } else if (text.matches("delete(.*)")) {
            // Delete a task.
            deleteTask(text);
        } else {
            // Command not valid.
            throw new InvalidCommandException();
        }
    }

    // Retrieves previously saved tasks into "duke.txt".
    private void retrieveData() throws IOException {
        File f = new File(FILE_PATH);
        f.createNewFile();
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String data  = sc.nextLine();
            String[] arguments = data.split(" // ");

            boolean isDone;
            if (Integer.parseInt(arguments[1]) == 0) {
                isDone = false;
            } else {
                isDone = true;
            }

            if (arguments.length == 3) {
                Todo task = new Todo(arguments[2], isDone);
                taskList.add(task);
            } else {
                if (arguments[0].equals("Event")) {
                    Event task = new Event(arguments[2], arguments[3], isDone);
                    taskList.add(task);
                } else {
                    Deadline task = new Deadline(arguments[2], arguments[3], isDone);
                    taskList.add(task);
                }
            }
        }
    }

    // Saves current task list into "duke.txt".
    private void storeData() throws IOException {
        File f = new File(FILE_PATH);
        f.createNewFile();
        FileWriter fw = new FileWriter(FILE_PATH);

        for (int i = 1; i <= taskList.size(); i++) {
            fw.write(taskList.get(i - 1).getData() + "\n");
        }

        fw.close();
    }

    /**
     * Method to simulate the program.
     */
    public void run() {
        try {
            retrieveData();

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

            storeData();

            sc.close();
        } catch (IOException e) {
            printText("File does not exist!");
        }
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
