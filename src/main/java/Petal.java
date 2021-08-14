import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Scanner;

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
        System.out.println(indentation + logo + logo2 + indentation);
    }

    /**
     * Method that formats the message to be displayed
     * @param message Message initially written by user
     */
    public void formatMessage(String message) {
        if (message.equals("")) {
            emptyMessage();
        } else if (message.equals("list")) {
            printList();
        } else if (message.equals("bye")) {
            goodBye();
        } else if (message.contains("done")) {
            markTaskAsDone(message);
        } else if (message.contains("todo") || message.contains("deadline")
                                            || message.contains("event")) {
            try {
                handleTask(message);
            } catch (EmptyDescException | InvalidInputException e1) {
                System.out.println(e1.getMessage());
            } catch (PetalException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(indentation);
            System.out.println("I didn't understand that :( Could you please type it in again?");
            System.out.println("\nUse 'todo <insert activity>' to create a to-do!");
            System.out.println("\nUse 'deadline <insert activity> /by <insert deadline>' " +
                               "to create an activity with a deadline!");
            System.out.println("\nUse 'event <insert activity> /at <insert start/end time>' " +
                               "to create an activity with a start/end time!");
            System.out.println(indentation);
        }
    }

    public void handleTask(String message) throws PetalException {
        String[] checkType = message.split(" ");
        String desc = message.substring(message.indexOf(" ") + 1);
        boolean isValidInput = checkType[0].equals("todo") || checkType[0].equals("deadline")
                                                            || checkType[0].equals("event");
        boolean descAvail = !message.contains(" ") ||
                            desc.isBlank();
        if (descAvail) {
            throw new EmptyDescException("(っ- ‸ – ς)! It seems like there was no description"
                                                      + "! Please enter a description");
        }
        if (!isValidInput) {
            throw new InvalidInputException("っ- ‸ – ς)! I did not understand what you said :(");
        }
        Task task;
        String taskWithTime = message.substring(message.indexOf(" ") + 1);
        switch (checkType[0]) {
            case "todo":
                task = new Todo(desc);
                break;
            case "deadline":
                String[] desc1 = taskWithTime.split("/by");
                task = new Deadline(desc1[0], desc1[1]);
                break;
            default:
                String[] desc2 = taskWithTime.split("/at");
                task = new Event(desc2[0], desc2[1]);
        }
        System.out.println(indentation + "\nGot it. I've added this task:" + task);
        history.add(task);
        System.out.println(task);
        System.out.println("There are now " + history.size() + " task(s) in your list!\n"
                                                             + indentation);
    }

    /**
     * Method that handles empty message
     */
    public void emptyMessage() {
        System.out.println(indentation + "\nThat was an empty message! Say something."
                + "\n" + indentation);
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
     * Method to mark the task as done
     * @param message The -ith task to be marked as done
     */
    public void markTaskAsDone(String message) {
        String[] newMessage = message.split(" ");
        Task taskToBeCompleted = history.get(Integer.parseInt(newMessage[1]) - 1);
        taskToBeCompleted.taskDone();
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

