import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A chatbot based on Project Duke
 *
 * @author KelvinSoo
 * @version Level-3
 *
 */
public class Duke {

    private String chatbotName;
    private Scanner sc = new Scanner(System.in);
    private List<Task> taskList = new ArrayList<>();

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
    }

    /**
     * Print a list of text.
     * @param list List of text.
     */
    private void printList(List<Task> list) {
        if (list.isEmpty()) {
            printReply("It seems that your list is empty. Try adding something first.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here is your list:\n");
            for (Task task : list) {
                sb.append(String.format("%s. %s %s \n", task.getID(), task.getStatusIcon(), task.getDescription()));
            }
            printReply(sb.toString());
        }
    }

    /**
     * Process a given input and generate a reply
     * @param text The user input.
     */
    private void processReply(String text) {
        if (text.equals("bye")) {
            terminateUser();
        } else if (text.equals("list")) {
            printList(taskList);
            processReply(sc.nextLine());
        } else if (text.startsWith("done ") || (text.equals("done"))) {
            String[] splitText = text.split(" ");

            // check if the character after done is a valid number
            if (splitText.length > 1 && splitText[1].matches("\\d")) {
                int taskID = Integer.parseInt(splitText[1]);

                // check if the task exist
                if (taskID <= taskList.size() && taskID > 0) {
                    Task task = taskList.get(taskID - 1);
                    task.markASDone();
                    printReply(String.format("Nice! I've marked this task as done: \n  %s %s",
                            task.getStatusIcon(), task.getDescription()));
                } else {
                    printReply(String.format("Task %d does not exist", taskID));
                }
            } else {
                printReply("Please enter a valid number.");
            }
            processReply(sc.nextLine());
        } else {
            taskList.add(new Task(text));
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
