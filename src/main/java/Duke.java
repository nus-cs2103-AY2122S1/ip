import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A chatbot based on Project Duke
 *
 * @author KelvinSoo
 * @version Level-2
 *
 */
public class Duke {

    private String chatbotName;
    private Scanner sc = new Scanner(System.in);
    private List<String> list = new ArrayList<String>();

    /**
     * A private constructor to initialize the name of the chatbot.
     *
     * @param chatbotName The name of the chatbot.
     */
    private Duke(String chatbotName) {
        this.chatbotName = chatbotName;
    }

    /**
     * Print a given text in a box.
     * @param text The text to be formatted.
     */
    private void printReply(String text) {
        int maxLength = 0;
        String[] line = text.split("\n");

        for (String s : line) {
            if (maxLength < s.length())
                maxLength = s.length();
        }

        String lineStart =  "    ╔" + "═".repeat(maxLength + 2) + "╗";
        String lineEnd =    "    ╚" + "═".repeat(maxLength + 2) + "╝";

        System.out.println(lineStart);
        for (String s : line) {
            System.out.println("    ║ " + s + " ".repeat(maxLength - s.length()) + " ║");
        }
        System.out.println(lineEnd);
    }

    /**
     * Prints the greeting text to user
     */
    private void greetUser() {
        printReply(String.format("Hello! I'm %s \nWhat can I do for you?", this.chatbotName));
    }

    /**
     * Terminate user session
     */
    private void terminateUser() {
        printReply("Bye. Hope to see you again soon!");
        sc.close();
    }

    /**
     * Print a list of text.
     * @param list List of text.
     */
    private void printList(List<String> list) {
        if (list.isEmpty()) {
            printReply("It seems that your list is empty. Try adding something first.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here is your list:\n");
            for (int i = 0; i < list.size(); i++) {
                sb.append(i + 1).append(". ").append(list.get(i)).append("\n");
            }
            printReply(sb.toString());
        }
    }

    /**
     * Process a given input and generate a reply
     * @param text The user input.
     */
    private void processReply(String text) {
        switch (text) {
            case "bye":
                terminateUser();
                break;
            case "list":
                printList(list);
                processReply(sc.nextLine());
                break;
            default:
                list.add(text);
                printReply(String.format("\"%s\" has been added to your list", text));
                processReply(sc.nextLine());
        }
    }

    /**
     * Start a new chatbot session
     */
    private void run() {
        greetUser();
        processReply(sc.nextLine());
    }

    public static void main(String[] args) {
        String logo = " ____          _____  _______     __\n"
            + "|  _ \\   /\\   |  __ \\|  __ \\ \\   / /\n"
            + "| |_) | /  \\  | |__) | |__) \\ \\_/ / \n"
            + "|  _ < / /\\ \\ |  _  /|  _  / \\   /  \n"
            + "| |_) / ____ \\| | \\ \\| | \\ \\  | |   \n"
            + "|____/_/    \\_\\_|  \\_\\_|  \\_\\ |_|   ";
        System.out.println(logo);
        Duke barry = new Duke("Barry");
        barry.run();
    }
}
