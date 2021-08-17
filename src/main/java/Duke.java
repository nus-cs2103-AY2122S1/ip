import java.util.Scanner;

/**
 * A chatbot based on Project Duke
 *
 * @author KelvinSoo
 * @version Level-1
 *
 */
public class Duke {

    private String chatbotName;
    private Scanner sc = new Scanner(System.in);

    /**
     * A private constructor to initialize the name of the chatbot.
     *
     * @param chatbotName The name of the chatbot.
     */
    private Duke(String chatbotName) {
        this.chatbotName = chatbotName;
    }

    /**
     * Print a given text in the given format.
     * @param text The text to be formatted.
     */
    private void printReply(String text) {
        String lineStart =  "    ╔" + "═".repeat(50);
        String lineEnd =    "    ╚" + "═".repeat(50);
        String lineIndent = "    ║ ";

        System.out.println(lineStart);
        String[] line = text.split("\n");
        for (String s : line) {
            System.out.println(lineIndent + s);
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
     * Process a given input and generate a reply
     * @param text The user input.
     */
    private void processReply(String text) {
        if (text.equals("bye")) {
            terminateUser();
        } else {
            printReply(text);
            processReply(sc.next());
        }
    }

    /**
     * Start a new chatbot session
     */
    private void run() {
        greetUser();
        processReply(sc.next());
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
