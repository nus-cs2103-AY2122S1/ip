import java.util.Scanner;

public class Gnosis {

    private static final String GREET_MESSAGE = "Welcome, I am Gnosis.\n" +
            "The spark to meet your needs.\nHow can I assist you ?";

    private static final String BYE_MESSAGE = "Good bye.\nI hope your needs have been sparked.\n" +
            "I welcome you back soon.";


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userInput;

        // Display greeting message
        displayDivider();
        System.out.println(GREET_MESSAGE);
        displayDivider();


        // terminates user input only when "bye" is inputted by user
        do {
            userInput = sc.nextLine();

            // display and execute commands
            displayDivider();
            executeCommand(userInput);
            displayDivider();

        } while (!userInput.equalsIgnoreCase("BYE"));


        sc.close(); // close scanner
    }


    /**
     * Executes user commands
     * */
    public static void executeCommand(String userCommand) {

        // convert all commands to lower case to avoid case issues
        switch (userCommand.toLowerCase()) {
            case "bye":
                byeCommand();
                break;
            default:
                echoCommand(userCommand);
        }
    }

    public static void byeCommand() {
        System.out.println(BYE_MESSAGE);
    }

    public static void echoCommand(String echo) {
        System.out.println(echo);
    }

    /**
     * utility method to display divider for display format
     * */
    public static void displayDivider() {
        System.out.println("-- \t============\t --");
    }
}
