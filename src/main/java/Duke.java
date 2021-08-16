import java.util.Scanner;
import java.util.ArrayList;

import exception.DukeException;
import exception.IncorrectFormatException;
import exception.InvalidIndexException;
import exception.EmptyCommandException;
import exception.InvalidCommandException;
import exception.MessageEmptyException;
import exception.EmptyListException;

/**
 * Duke class that initialises the Duke chat bot.
 * The Duke class supports operators including
 * (i) run: runs the chat bot
 * (ii) greet: prints out a greeting when the chat bot runs
 * (iii) exit: ends the execution of the chat bot
 * (iv) addToList: adds a Task to the list of Tasks
 * (v) displayList: prints out the current list of Tasks
 * (vi) markDone: marks a Task as done
 * (vii) addDeadline: adds a Deadline to the list of Tasks
 * (viii) addTodo: adds a Todo to the list of Tasks
 * (ix) addEvent: adds an Event to the list of Tasks
 * (x) handleCommands: main logic for processing and executing various commands
 * like "list", "done", "deadline", "todo", "event" and other invalid commands
 */

public class Duke {

    /**
     * taskList is the list of Tasks
     */

    private final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Runs the Duke chat bot.
     * It takes in user inputs and responds accordingly.
     * If a command is issued, the bot will execute the command if the appropriate message
     * follows the command.
     * Any invalid inputs are caught via custom Exceptions, thrown and printed for the user to see.
     * Invalid inputs include empty inputs, incorrect formats, invalid index, empty messages and
     * attempting to amend the list of Tasks when it is currently empty.
     */

    public void run() {

        greet();

        String input;
        Scanner sc = new Scanner(System.in);

        // user input trimmed to remove unwanted spaces at the front and back of user input
        // allows for greater margin of error when typing in commands
        while(!(input = sc.nextLine().trim()).equals("bye")) {
            // continuously runs the bot as long as the "bye" command is not issued
            handleCommands(input);
        }

        sc.close(); // closes the Scanner

        exit();
    }

    /**
     * Prints out a greeting for the user when the bot is first ran.
     */

    private void greet() {
        System.out.println("__________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("__________________________________");
    }

    /**
     * Prints out a goodbye message when the bot is exited.
     */

    private void exit() {
        System.out.println("__________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("__________________________________");
    }

    /**
     * Adds a task to the list of Tasks with a confirmation message printed out after.
     * @param task The Task to be added to the list of Tasks
     */

    private void addToList(Task task) {
        this.taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("added: " + task);
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
    }

    /**
     * Prints out the full contents of the list of Tasks.
     * @throws EmptyListException If the list of Tasks is empty and there is nothing to be printed.
     */

    private void displayList() throws EmptyListException {
        if (this.taskList.size() == 0) {
            throw new EmptyListException();
        }
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println(i + 1 + ". " + task);
        }
    }

    /**
     * Marks a current Task in the list of Tasks as Done.
     * @param taskIndex The index of the Task in the list of Tasks to be marked as Done.
     * @throws EmptyListException If the list of Tasks is empty and there is nothing to be marked as Done.
     * @throws InvalidIndexException If the index of the Task provided is out of range of the current list of Tasks.
     */

    private void markDone(String taskIndex) throws EmptyListException, InvalidIndexException {

        int intTaskIndex = Integer.parseInt(taskIndex) - 1; // -1 because user inputs start from 1 not 0
        int taskListSize = this.taskList.size();
        if (taskListSize == 0) {
            throw new EmptyListException();
        }
        else if (intTaskIndex < 0 || intTaskIndex >= taskListSize) {
            throw new InvalidIndexException(1, taskListSize, intTaskIndex + 1);
        }
        Task task = this.taskList.get(intTaskIndex);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Adds a Deadline to the list of Tasks.
     * @param deadline The Deadline to be added to the list of Tasks which is the whole input barring the command.
     * @throws IncorrectFormatException If the deadline command is used but a "/by" is not present in the message.
     */

    private void addDeadline(String deadline) throws IncorrectFormatException {
        String[] result = deadline.split("/by");
        if (result.length == 1) { // throws an error if "/by" is not present in the message
            throw new IncorrectFormatException("deadline", "/by");
        }
        String description = result[0].trim(); // trims the additional spaces to the left and right of "by"
        String by = result[1].trim(); // trims the additional spaces to the left and right of "by"
        Deadline d = new Deadline(description, by);
        addToList(d);
    }

    /**
     * Adds a Todo to the list of Tasks.
     * @param todo The Todo to be added to the list of Tasks.
     */

    private void addTodo(String todo) {
        Todo tempTask = new Todo(todo);
        addToList(tempTask);
    }

    /**
     * Adds an Event to the list of Tasks.
     * @param event The Event to be added to the list of Tasks, which is the entire user input barring the command.
     * @throws IncorrectFormatException If the event command is used but a "/at" is not present in the message.
     */

    private void addEvent(String event) throws IncorrectFormatException {
        String[] result = event.split("/at");
        if (result.length == 1) { // throws an error if "/at" is not present in the message
            throw new IncorrectFormatException("event", "/at");
        }
        String description = result[0].trim(); // trims the additional spaces to the left and right of "at"
        String at = result[1].trim(); // trims the additional spaces to the left and right of "at"
        Event e = new Event(description, at);
        addToList(e);
    }

    /**
     * Deletes a Task from the list of Tasks.
     * @param taskIndex Index of the Task to be deleted.
     * @throws EmptyListException If the list of Tasks is empty and there is nothing to be deleted.
     * @throws InvalidIndexException If the index of the Task provided is out of range of the current list of Tasks.
     */

    private void deleteTask(String taskIndex) throws EmptyListException, InvalidIndexException {

        int intTaskIndex = Integer.parseInt(taskIndex) - 1; // -1 because user inputs start from 1 not 0
        int taskListSize = this.taskList.size();
        if (taskListSize == 0) {
            throw new EmptyListException();
        }
        else if (intTaskIndex < 0 || intTaskIndex >= taskListSize) {
            throw new InvalidIndexException(1, taskListSize, intTaskIndex + 1);
        }
        Task task = this.taskList.remove(intTaskIndex);
        System.out.println("Noted. I've removed this task:\n" + task);
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
    }

    /**
     * Logic for handling different commands and executing the appropriate methods for the inputted command.
     * @param input The entire user input.
     */

    private void handleCommands(String input) {
        String[] words = input.split(" "); // isolates the command word
        String command = words[0];

        try {
            System.out.println("__________________________________");
            switch (command) {
                case "list":
                    displayList();
                    break;
                case "done":
                    if (words.length == 1) { // throws an error if there is no message input after the command word
                        throw new MessageEmptyException();
                    }
                    String doneTaskIndex = words[words.length - 1];
                    markDone(doneTaskIndex);
                    break;
                case "deadline":
                    if (words.length == 1) { // throws an error if there is no message input after the command word
                        throw new MessageEmptyException();
                    }
                    // excludes command "deadline " from the string
                    addDeadline(input.substring(9));
                    break;
                case "todo":
                    if (words.length == 1) { // throws an error if there is no message input after the command word
                        throw new MessageEmptyException();
                    }
                    // excludes command "todo" from the string
                    addTodo(input.substring(5));
                    break;
                case "event":
                    if (words.length == 1) { // throws an error if there is no message input after the command word
                        throw new MessageEmptyException();
                    }
                    // excludes command "event" from the string
                    addEvent(input.substring(6));
                    break;
                case "delete":
                    if (words.length == 1) { // throws an error if there is no message input after the command word
                        throw new MessageEmptyException();
                    }
                    String deleteTaskIndex = words[words.length - 1];
                    deleteTask(deleteTaskIndex);
                    break;
                case "": // empty user input
                    throw new EmptyCommandException();
                default: // all other inputs that are not supported
                    throw new InvalidCommandException();
            }
            System.out.println("__________________________________");
        } catch (DukeException e) {
            System.out.println(e.getMessage()); // prints only error message out for user
            System.out.println("__________________________________");
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
