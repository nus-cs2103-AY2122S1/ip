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
    private final List<Task> history;

    /**
     * Constructor for the Duke class
     */
    public Petal() {
        bye = false;
        history = new ArrayList<>();
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
     * @param message Message initially written by user
     */
    public void formatMessage(String message) {
        String[] msg = message.split(" ");
        try {
            switch (msg[0]) {
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
                    deleteTask(msg[1]);
                    break;
                default:
                    handleTask(message);
                    break;
            }
        } catch (EmptyDescException | InvalidInputException | IndexOutOfBoundsException | NumberFormatException e1)  {
            System.out.println(indentation + "\n" + e1.getMessage());
            requiredFormat();
        } catch (PetalException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method which help to displayed the required format
     */
    public void requiredFormat() {
        String todo = "Use 'todo <insert activity>' to create a to-do!";
        String deadline = "\nUse 'deadline <insert activity> /by <insert deadline>' "
                            + "to create an activity with a deadline!";
        String event = "\nUse 'event <insert activity> /at <insert start/end time>' "
                            + "to create an activity with a start/end time!";
        String delete = "\n Use 'delete <insert task number> to delete a task!";
        String done = "\nUse 'done <insert task number>' to mark task as done!\n";
        System.out.println(todo + deadline + event + done + indentation);
    }

    public ArrayList<String> arrayWithoutWhiteSpaces(String string) {
        String[] partition = string.split(" ");
        ArrayList<String> result = new ArrayList<>();
        for (String s : partition) {
            if (!s.equals(" ")) {
                result.add(s);
            }
        }
        return result;
    }

    /**
     * Method which handles the remaining messages (assumed to be tasks)
     * @param message The messaged to be handled
     * @throws PetalException Thrown if input is incorrect
     */
    public void handleTask(String message) throws PetalException {
        Task task;
        String[] typeOfTask = message.split(" ");
        String desc = message.substring(message.indexOf(" ") + 1);
        boolean isValidInput = typeOfTask[0].equals("todo") || typeOfTask[0].equals("deadline")
                                                            || typeOfTask[0].equals("event");
        if (!isValidInput) {
            throw new InvalidInputException("I did not understand what you said :(\n");
        }
        boolean descNotAvail = typeOfTask.length < 2 || typeOfTask[1].equals("/at")
                                                     || typeOfTask[1].equals("/by");
        if (descNotAvail) {
            throw new EmptyDescException("It seems like there was no description"
                                          + "! Please enter a description.\n");
        }
        switch (typeOfTask[0]) {
            case "todo":
                task = new ToDo(desc);
                break;
            case "deadline":
                String[] desc1 = desc.split("/by");
                if (desc1.length == 1) {
                    throw new InvalidInputException("Wrong format was used! Please try again.\n");
                }
                task = new Deadline(desc1[0], desc1[1]);
                break;
            default:
                String[] desc2 = desc.split("/at");
                if (desc2.length == 1) {
                    throw new InvalidInputException("Wrong format was used! Please try again.\n");
                }
                task = new Event(desc2[0], desc2[1]);
        }
        System.out.println(indentation + "\nGot it. I've added this task.");
        history.add(task);
        System.out.println(task);
        System.out.println("There are now " + history.size() + " task(s) in your list!\n"
                                                             + indentation);
    }

    /**
     * Method to delete a task
     * @param index The index of the task to be deleted
     */
    public void deleteTask(String index) {
        int indexOfTask;
        try {
            indexOfTask = Integer.parseInt(index) - 1;
            Task toBeDeleted = history.get(indexOfTask);
            history.remove(indexOfTask);
            System.out.println(indentation +"\nOkay. I've deleted this task:\n"
                                           + toBeDeleted
                                           + "\nYou now have " + history.size() + " task(s)!\n"
                                           + indentation);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new NumberFormatException("Invalid task number given! Please enter another value!");
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
     * Method that prints list
     */
    public void printList() {
        int count = 1;
        System.out.println(indentation);
        for (Task m : history) {
            System.out.println(count++ + ". " + m);
        }
        System.out.println(indentation);
    }

    /**
     * Method to mark the task as done //TODO: what if blank string
     * @param message The -ith task to be marked as done
     */
    public void markTaskAsDone(String message) throws IndexOutOfBoundsException,
                                                      NumberFormatException {
        try {
            String indexOfTask = message.split(" ")[1];
            Task taskToBeCompleted = history.get(Integer.parseInt(indexOfTask) - 1);
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
        String s = "apple";
        String[] example = s.split(" ");
        System.out.println(example.length);
        Petal petal = new Petal();
        Scanner scanner = new Scanner(System.in);
        while(!petal.isBye()) {
            String message = scanner.nextLine();
            petal.formatMessage(message);
        }
        scanner.close();
    }
}

