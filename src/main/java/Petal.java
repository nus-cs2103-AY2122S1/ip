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
        startMessage();
    }

    /**
     * Method to give the start message
     */
    public void startMessage() {
        String logo = "\nWelcome to Petal (•◡•)/ ";
        String logo2 = "\nI am the best chat bot you'll meet! Don't be shy, say something! :P\n";
        System.out.println(indentation + logo + logo2);
        requiredFormat();
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
                    markTaskAsDone(message);
                    break;
                case "delete":
                    deleteTask(message);
                    break;
                default: //tasks end up here, as well as unintelligible messages
                    handleRemaining(message);
                    break;
            }
        } catch (EmptyDescException | InvalidInputException | IndexOutOfBoundsException | NumberFormatException e1)  {
            System.out.println(indentation + "\n" + e1.getMessage());
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
        String done = "\nUse 'done <insert task number>' to mark task as done!\n";
        System.out.println(todo + deadline + event + delete + done + indentation);
    }

    /**
     * Method to handle the last case of user input
     * @param message User input
     * @throws EmptyDescException Thrown if user enters a command without a description
     * @throws InvalidInputException Thrown if the input does not match the required format
     *                               or if the message is unintelligible
     */
    public void handleRemaining(String message) throws InvalidInputException, EmptyDescException {
        Task task;
        String[] typeOfTask = message.split(" ");
        String desc = message.substring(message.indexOf(" ") + 1);
        //Checks if the phrase contains a valid command
        boolean isValidInput = typeOfTask[0].equals("todo") || typeOfTask[0].equals("deadline")
                || typeOfTask[0].equals("event");
        if (!isValidInput) {
            throw new InvalidInputException("I did not understand what you said :(\n");
        }
        //Checks if there is a valid desc
        boolean descNotAvail = typeOfTask.length < 2 || typeOfTask[1].equals("/at")
                || typeOfTask[1].equals("/by");
        if (descNotAvail) {
            throw new EmptyDescException("It seems like there was no description"
                    + "! Please enter a description.\n");
        }
        switch (typeOfTask[0]) { //The only possible types are tod.o, deadline, and event
            case "todo":
                task = new ToDo(desc);
                break;
            case "deadline":
                String[] desc1 = desc.split("/by");
                if (desc1.length == 1) { //Checking if time/desc is given
                    throw new InvalidInputException("Wrong format was used! Please try again.\n");
                }
                task = new Deadline(desc1[0], desc1[1]);
                break;
            default: //for "event"
                String[] desc2 = desc.split("/at");
                if (desc2.length == 1) { //Checking if time/desc is given
                    throw new InvalidInputException("Wrong format was used! Please try again.\n");
                }
                task = new Event(desc2[0], desc2[1]);
        }
        addTask(task);
    }

    /**
     * Method to add a task to history
     * @param task The task to be added
     */
    public void addTask(Task task) {
        System.out.println(indentation + "\nGot it. I've added this task.");
        tasks.add(task);
        System.out.println(task);
        System.out.println("There are now " + tasks.size() + " task(s) in your list!\n"
                + indentation);
    }

    /**
     * Method to delete a task from the list
     * @param message The message given by the user input
     * @throws InvalidInputException Thrown if no index inputted by the user or
     *                               when index is out-of-bounds/not valid int or when
     *                               desc is empty
     */
    public void deleteTask(String message) throws InvalidInputException, EmptyDescException {
        try {
            String index = message.split("delete")[0].trim();
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
        //If the list is empty
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
     * @param message Message that represents the user input
     * @throws IndexOutOfBoundsException Thrown if string is not within size of list
     * @throws NumberFormatException Thrown if string cannot be converted into valid int
     */
    public void markTaskAsDone(String message) throws IndexOutOfBoundsException,
            NumberFormatException {
        try {
            String indexOfTask = message.split(" ")[1];
            Task taskToBeCompleted = tasks.get(Integer.parseInt(indexOfTask) - 1);
            taskToBeCompleted.taskDone();
        } catch (IndexOutOfBoundsException e1) { //Parsed string is not within size of history
            throw new IndexOutOfBoundsException("That was an invalid index! Please try again!\n");
        } catch (NumberFormatException e2) { //If parsed string is not an integer
            throw new NumberFormatException("Index was not a number! Please try again!\n");
        }
    }

    /**
     * Method to check if the user has said bye
     * @return True if yes, false if no
     */
    public boolean isBye() {
        return bye;
    }

    public static void main(String[] args) {
        Petal petal = new Petal();
        Scanner scanner = new Scanner(System.in);
        while(!petal.isBye()) {
            String message = scanner.nextLine();
            petal.formatMessage(message);
        }
        scanner.close();
    }
}

