import java.util.Scanner;

public class Duke {

    private boolean bye;
    private final String indentation = "-----------------------------------------------\n";

    /**
     * Constructor for the Duke class
     */
    public Duke() {
        bye = false;
        startMessage();
    }

    /**
     * Method to give the start message
     */
    public void startMessage() {
        String logo = "Welcome to Petal (•◡•)/ ";
        String logo2 = "\nI am the best chatbot you'll meet! Don't be shy, say something! :P";
        System.out.println(logo + logo2);
    }

    /**
     * Method that formats the message to be displayed
     * @param message Message initially written by user
     */
    public void formatMessage(String message) {
        switch(message) {
            case "":
                System.out.println(indentation + "That was an empty message! Say something."
                                               + "\n" + indentation);
                break;
            case "bye":
                bye = true;
                System.out.println(indentation + "Hope to see you again soon!" + "\n" + indentation);
                break;
            default:
                System.out.println(indentation + message + "\n" + indentation);
        }
    }

    /**
     * Method for the bot to say goodbye
     */
    public void goodBye() {
        System.out.println(indentation + "Please come back soon" + indentation);
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

