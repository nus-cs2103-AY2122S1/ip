import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/**
 * The class for the Duke bot
 */
public class Petal {

    //True if user has said bye, false if otherwise
    private boolean bye;
    //The protected constant for displaying the lines between messages
    protected static final String indentation = "---------------------------------------"
                                                + "-------------------------------------"
                                                + "-------------------------------------";
    //The list which stores the user's message
    private final List<Task> tasks;

    /**
     * Constructor for the Duke class
     */
    public Petal() {
        bye = false;
        tasks = new ArrayList<>();
    }

    /**
     * Method to give the start message
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        String logo = "\nWelcome to Petal (•◡•)/ ";
        String logo2 = "\nI am the best chat bot you'll meet! Don't be shy, say something! :P\n";
        System.out.println(indentation + logo + logo2 + indentation);
        while (!bye) {
            String message = scanner.nextLine();
            formatMessage(message);
        }
        scanner.close();
    }

    /**
     * Method that formats the message to be displayed
     * @param message User input
     */
    public void formatMessage(String message) {
        //Allows user to type in upper case and removes leading/trailing whitespaces
        message = message.trim().toLowerCase();
        String[] msg = message.split(" ");
        try {
            switch (msg[0]) { //Checks first word in string
                case "list":
                    printList();
                    break;
                case "bye":
                    goodBye();
                    break;
                case "done":
                    markTaskAsDone(message.substring(message.indexOf(' ') + 1).trim());
                    break;
                case "delete":
                    deleteTask(message.substring(message.indexOf(' ') + 1).trim());
                    break;
                case "todo":
                    message += " "; //To allow empty desc to be taken as substring
                    handleTasks("todo", message.substring(message.indexOf(' ') + 1).trim());
                    break;
                case "deadline":
                    handleTasks("deadline", message.substring(message.indexOf(' ') + 1).trim());
                    break;
                case "event":
                    handleTasks("event", message.substring(message.indexOf(' ') + 1).trim());
                    break;
                default: //All messages here do not meet the required format or are unintelligible
                    wrongFormat();
                    break;
            }
        } catch (EmptyDescException | InvalidInputException e) {
            System.out.println(indentation + "\n" + e.getMessage());
            requiredFormat();
        }
    }

    public void wrongFormat() throws InvalidInputException {
        throw new InvalidInputException("I do not understand what you mean :(\n");
    }

    /**
     * Method which helps to display the required format
     */
    public void requiredFormat() {
        String todo = "Use 'todo <insert activity>' to create a to-do!";
        String deadline = "\nUse 'deadline <insert activity> /by <insert deadline>' "
                + "to create an activity with a deadline!";
        String event = "\nUse 'event <insert activity> /at <insert start/end time>' "
                + "to create an activity with a start/end time!";
        String delete = "\nUse 'delete <insert task number> to delete a task!";
        String done = "\nUse 'done <insert task number>' to mark task as done!\n";
        System.out.println(todo + deadline + event + delete + done + indentation);
    }

    /**
     * Method to handle the tasks
     * @param type The type of task: to.do, deadline, event
     * @param message The desc/time of the task
     * @throws EmptyDescException Thrown when the task lacks a description
     * @throws InvalidInputException Thrown when an invalid format is given or when a time is not given
     */
    public void handleTasks(String type, String message) throws EmptyDescException, InvalidInputException {
        Task task ;
        String[] deadlineEvent = type.equals("deadline") ? message.split("/by")
                                                         : message.split("/at");
        if (message.isBlank() || deadlineEvent[0].isBlank()) {
            throw new EmptyDescException("The description cannot be empty! Enter a valid one! :(\n");
        }
        //No time given or the command /by or /at wasn't given by the user
        if ((type.equals("deadline") || type.equals("event")) && deadlineEvent.length < 2) {
            throw new InvalidInputException("The format used was wrong! Try again :(\n");
        }
        switch (type) {
            case "todo":
                task = new ToDo(message);
                break;
            case "deadline":
                task = new Deadline(deadlineEvent[0], deadlineEvent[1]);
                break;
            default: //Represents the Event task
                task = new Event(deadlineEvent[0], deadlineEvent[1]);
                break;
        }
        addTask(task);
    }

    /**
     * Method to add a task to history
     * @param task The task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println(indentation + "\nOkay. I've added this task:\n"
                                       + task
                                       + "\nYou now have " + tasks.size() + " task(s)!\n"
                                       + indentation);
    }

    /**
     * Method to delete a task from the list
     * @param index The message given by the user input
     * @throws InvalidInputException Thrown if no index inputted by the user or
     *                               when index is out-of-bounds/not valid int or when
     *                               desc is empty
     */
    public void deleteTask(String index) throws InvalidInputException, EmptyDescException {
        try {
            int indexOfTask = Integer.parseInt(index) - 1;
            Task toBeDeleted = tasks.get(indexOfTask);
            tasks.remove(indexOfTask);
            System.out.println(indentation + "\nOkay. I've deleted this task:\n"
                                           + toBeDeleted
                                           + "\nYou now have " + tasks.size() + " task(s)!\n"
                                           + indentation);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDescException("No task number given! Please enter a valid index!\n");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidInputException("Invalid task number given! Please enter another value!\n");
        }
    }

    /**
     * Method for the bot to say goodbye
     */
    public void goodBye() {
        bye = true;
        System.out.println(indentation + "\nYou're leaving :( I hope you return soon!\n"
                + indentation);
    }

    /**
     * Method to print the list
     * @throws InvalidInputException Thrown when the list is empty
     */
    public void printList() throws InvalidInputException {
        if (tasks.size() == 0)
            throw new InvalidInputException("No items in list yet!\n");
        int count = 1;
        System.out.println(indentation);
        for (Task m : tasks) {
            System.out.println(count++ + ". " + m);
        }
        System.out.println(indentation);
    }

    /**
     * Method to mark a particular task as done
     * @param indexOfTask String representation of the index of the task
     * @throws IndexOutOfBoundsException Thrown if string is not within size of list
     * @throws NumberFormatException Thrown if string cannot be converted into valid int
     */
    public void markTaskAsDone(String indexOfTask) throws InvalidInputException {
        try {
            Task taskToBeCompleted = tasks.get(Integer.parseInt(indexOfTask) - 1);
            taskToBeCompleted.taskDone();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            //Parsed string is not within size of history or no index given
            throw new InvalidInputException("That was an invalid index! Please try again!\n");
        }
    }

    public static void main(String[] args) {
        Petal petal = new Petal();
        petal.start();
    }
}

