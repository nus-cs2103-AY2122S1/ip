import java.util.ArrayList;
import java.util.Scanner;

public class Gnosis {

    private static final String GREET_MESSAGE = "Welcome, I am Gnosis.\n" +
            "The spark to meet your needs.\nHow can I assist you ?";

    private static final String BYE_MESSAGE = "Good bye.\nI hope your needs have been sparked.\n" +
            "I welcome you back soon.";

    private static ArrayList<String> storedTexts;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userInput;
        storedTexts = new ArrayList<>();

        // Display greeting message
        displayTopDivider();
        System.out.println(GREET_MESSAGE);
        displayBottomDivider();


        // terminates user input only when "bye" is inputted by user
        do {
            userInput = sc.nextLine();

            // display and execute commands
            displayTopDivider();
            executeCommand(userInput);
            displayBottomDivider();

        } while (!userInput.equalsIgnoreCase("BYE"));


        sc.close(); // close scanner
    }


    /**
     * Executes user commands
     * */
    public static void executeCommand(String input) {

        // convert all commands to lower case to avoid case issues
        switch (input.toLowerCase()) {
            case "bye":
                byeCommand();
                break;
            case "list":
                listCommand();
                break;
            default:
                // Save text to ArrayList String
                storedTexts.add(input);
                addCommand(input);
        }
    }

    //Corresponding user command methods

    public static void listCommand() {
        int len = storedTexts.size();
        for (int i = 0; i < len; i++) {
            System.out.println((i+1) + ". " + storedTexts.get(i));
        }
    }

    public static void byeCommand() {
        System.out.println(BYE_MESSAGE);
    }

    public static void addCommand(String text) {
        System.out.println("item saved: " + text);
    }


    //Utility methods to display divider for display format

    public static void displayTopDivider() {
        System.out.println("-- \t============\t --");
    }
    public static void displayBottomDivider() {
        System.out.println("-- \t============\t --" + '\n');
    }
}
