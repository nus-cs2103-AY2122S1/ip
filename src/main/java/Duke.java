import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private boolean bye;
    private final String indentation = "-----------------------------------------------\n";
    private int count;
    private final List<Task> history;

    /**
     * Constructor for the Duke class
     */
    public Duke() {
        bye = false;
        count = 1;
        history = new ArrayList<>();
        startMessage();
    }

    /**
     * Method to give the start message
     */
    public void startMessage() {
        String logo = "Welcome to Petal (•◡•)/ ";
        String logo2 = "\nI am the best chat bot you'll meet! Don't be shy, say something! :P";
        System.out.println(logo + logo2);
    }

    /**
     * Method that formats the message to be displayed
     * @param message Message initially written by user
     */
    public void formatMessage(String message) {
        if (message.equals("")) {
            System.out.println(indentation + "That was an empty message! Say something."
                    + "\n" + indentation);
        } else if (message.equals("list")) {
            printMessage();
        } else if (message.equals("bye")) {
            goodBye();
        } else if (message.contains("done")) {
            String[] newMessage = message.split(" ");
            Task taskToBeCompleted = history.get(Integer.parseInt(newMessage[1]) - 1);
            taskToBeCompleted.taskDone();
        } else {
            addMessage(message);
            System.out.println(indentation + "added: " + message + "\n" + indentation);
        }
    }

    /**
     * Method for the bot to say goodbye
     */
    public void goodBye() {
        bye = true;
        System.out.println(indentation + "You're leaving :( I hope you return soon!" + "\n" + indentation);
    }

    public void printMessage() {
        int count = 1;
        for (Task m : history) {
            System.out.println(count++ + ". " + m);
        }
    }

    /**
     * Method to add the message to list
     * @param message Message to be added
     */
    public void addMessage(String message) {
        history.add(new Task(message));
    }

    /**
     * Method to check if the user has said bye
     * @return True if yes, false if no
     */
    public boolean isBye() {
        return bye;
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        Scanner scanner = new Scanner(System.in);
        while(!bot.isBye()) {
            String message = scanner.nextLine();
            bot.formatMessage(message);
        }
        scanner.close();
    }
}

