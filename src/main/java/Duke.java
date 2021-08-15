import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // list used to store text entered by user
    private ArrayList<Task> items = new ArrayList<>();

    // Lines used to indicate a block of message
    private final String HORIZONTAL_LINE_HEAD = "\t____________________________________________________________";
    private final String HORIZONTAL_LINE_TAIL = String.format("\n%s\n", HORIZONTAL_LINE_HEAD);

    // Default messages sent by the chat bot
    private final String WELCOME_MSG = "Hello! I am Matthew!\n\t What can I do for you?";
    private final String EXIT_MSG = "Bye. Don't have a good day... Have a great day!!!";

    // Command Tags for the chat bot
    private final String EXIT_TAG = "bye";
    private final String LIST_TAG = "list";
    private final String DONE_TAG = "done";


    public static void main(String[] args) {
        Duke chatBotMatthew = new Duke();
        chatBotMatthew.start();
    }

    /**
     * Starts the chat bot.
     * Chat bot starts receiving commands from user and echo back the command until terminated.
     */
    public void start() {
        greet();

        // scanner to take in user's input(s)
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        while(!input.equalsIgnoreCase(EXIT_TAG)) {
            checkTag(input);
            input = scanner.nextLine().trim();
        }

        // close the scanner as the bot is terminated.
        scanner.close();
        exit();
    }

    /**
     * Greets the users when chat bot is started.
     */
    private void greet() {
        printFormattedMsg(WELCOME_MSG);
    }

    /**
     * Greets the users when chat bot is terminated.
     */
    private void exit() {
        printFormattedMsg(EXIT_MSG);
    }

    /**
     * Check if the inputted string match the 'list' tag.
     * Prints the list of items if inputted msg is 'list.
     * Otherwise, adds the item to the list.
     *
     * @param msg User's input.
     */
    private void checkTag(String msg) {
        // user inputs list, print the list of items added by user.
        if (msg.equalsIgnoreCase(LIST_TAG)) {
            System.out.println(HORIZONTAL_LINE_HEAD);
            System.out.println("\t Here are the tasks in your list:");

            for (int i = 0; i < this.items.size(); i++) {
                Task item = this.items.get(i);
                String formattedMsg = String.format("\t %s.%s", (i + 1), item);

                System.out.println(formattedMsg);
            }

            System.out.println(HORIZONTAL_LINE_TAIL);
        }

        // user completes a task
        else if (msg.toLowerCase().contains(DONE_TAG)) {
            String content;

            try {
                int index = getTaskId(msg);
                Task item = this.items.get(index);
                item.taskCompleted();

                content = String.format("Nice! I've marked this task as done:\n\t  %s", item);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                content = String.format(" Invalid input: %s", e.getMessage());
            }

            printFormattedMsg(content);
        }

        // add items to the list.
        else {
            this.items.add(new Task(msg));
            printFormattedMsg("added: " + msg);
        }
    }

    /**
     * Returns the id of the task that has been completed.
     *
     * @param msg Input of user
     * @return An integer indicating the id of the task that has been completed.
     * @throws IndexOutOfBoundsException Invalid id enter.
     * @throws NumberFormatException Invalid id enter.
     */
    private int getTaskId(String msg) throws IndexOutOfBoundsException, NumberFormatException {
        int position = msg.indexOf(" ");
        int index = Integer.parseInt(msg.substring(position + 1)) - 1;

        return index;
    }

    /**
     * Formats the message; puts the message in a block.
     * Horizontal lines - message - Horizontal lines.
     *
     * @param msg The message to be printed by the chat bot.
     */
    private void printFormattedMsg(String msg) {
        String formattedMsg = String.format("%s\n\t %s%s", HORIZONTAL_LINE_HEAD, msg, HORIZONTAL_LINE_TAIL);
        System.out.println(formattedMsg);
    }
}
