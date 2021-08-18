import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/**
 * The class for the Petal bot
 */
public class Petal {

    //The line used to display on the output
    public static final String LINE = "---------------------------------------"
                                      + "-------------------------------------"
                                      + "-------------------------------------";
    //Boolean representing if the user has said bye
    private boolean bye;
    //List of user inputted tasks
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
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String logo = "Welcome to Petal (•◡•)/ ";
        String logo2 = "\nI am the best chat bot you'll meet! Don't be shy, say something! :P";
        printMessage(logo + logo2);
        while (!bye) {
            String message = scanner.nextLine();
            handleInput(message.trim().toLowerCase());
        }
        scanner.close();
    }

    /**
     * Method that formats the message to be displayed
     * @param message User input
     */
    public void handleInput(String message) {
        message += " "; //So blank inputs can be handled
        String command = message.substring(0, message.indexOf(" "));
        String formatted = message.substring(message.indexOf(' ') + 1).trim();
        try {
            switch (command) { //Checks first word in string
                case "list":
                    printList();
                    break;
                case "bye":
                    goodBye();
                    break;
                case "done":
                    markTaskAsDone(formatted);
                    break;
                case "delete":
                    deleteTask(formatted);
                    break;
                case "todo":
                    handleTasks("todo", formatted);
                    break;
                case "deadline":
                    handleTasks("deadline", formatted);
                    break;
                case "event":
                    handleTasks("event", formatted);
                    break;
                default: //All messages here do not meet the required format or are unintelligible
                    throw new InvalidInputException("I do not understand what you mean :(");
            }
        } catch (PetalException e) {
            printMessage(e.getMessage());
            requiredFormat();
        }
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
        String done = "\nUse 'done <insert task number>' to mark task as done!";
        printMessage(todo + deadline + event + delete + done);
    }

    /**
     * Method to handle the tasks
     * @param type The type of task: to.do, deadline, event
     * @param message The desc/time of the task
     * @throws EmptyDescException Thrown when the task lacks a description
     * @throws InvalidInputException Thrown when an invalid format is given or when a time is not given
     */
    public void handleTasks(String type, String message) throws EmptyDescException, InvalidInputException {
        Task task;
        String[] deadlineEvent = type.equals("deadline") ? message.split("/by")
                                                         : message.split("/at");
        if (message.isBlank() || deadlineEvent[0].isBlank()) {
            throw new EmptyDescException("The description cannot be empty! Enter a valid one! :(");
        }
        //No time given or the command /by or /at wasn't given by the user
        if ((type.equals("deadline") || type.equals("event")) && deadlineEvent.length < 2) {
            throw new InvalidInputException("The format used was wrong! Try again :(");
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
        printMessage("Okay. I've added this task:\n"
                          + task
                          +"\nYou now have " + tasks.size() + " task(s)!");
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
            printMessage("Okay. I've deleted this task:\n"
                                + toBeDeleted
                                + "\nYou now have " + tasks.size() + " task(s)!");
        } catch (ArrayIndexOutOfBoundsException e) { //No task number given
            throw new EmptyDescException("No task number given! Please enter a valid index!", e);
        } catch (NumberFormatException | IndexOutOfBoundsException e) { //Invalid task number
            throw new InvalidInputException("Invalid task number given! Please enter another value!", e);
        }
    }

    /**
     * Method for the bot to say goodbye
     */
    public void goodBye() {
        bye = true;
        printMessage("You're leaving :( I hope you return soon!");
    }

    /**
     * Method to print the list
     * @throws InvalidInputException Thrown when the list is empty
     */
    public void printList() throws InvalidInputException {
        if (tasks.size() == 0)
            throw new InvalidInputException("No items in list yet!");
        int count = 1;
        String list = "Here you are :D";
        for (Task m : tasks) {
            list += "\n" + count++ + ". " + m ;
        }
        printMessage(list);
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
            throw new InvalidInputException("That was an invalid index! Please try again!", e);
        }
    }

    /**
     * Method to add the indentation to the message
     * @param message Message to be printed
     */
    public void printMessage(String message) {
        System.out.println(LINE + "\n" + message + "\n" + LINE);
    }

    public static void main(String[] args) {
        Petal petal = new Petal();
        petal.run();
    }
}

