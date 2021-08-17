import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.ListIterator;

public class Duke {
    private static final String END_OF_CONVERSATION = "bye";
    private static List<Task> listOfItems = new ArrayList<Task>();
    private static final String SEPARATOR = "########################";

    /**
     * Beautifies text output by wrapping it around a border.
     *
     * @param input
     */
    private static void outputWrapper(String input) {
        System.out.println(SEPARATOR);
        System.out.println(input);
        System.out.println(SEPARATOR);
    }

    /**
     * Beautifies list of items by wrappiing it around a border.
     *
     * @param elements
     */
    private static void outputWrapper(List elements) {
        ListIterator<Task> it = elements.listIterator();
        System.out.println(SEPARATOR);
        while (it.hasNext()) {
            Integer number = it.nextIndex() + 1;
            System.out.println(number + ". " + it.next());
        }
        System.out.println(SEPARATOR);
    }

    /**
     *
     * @param input
     */
    private static void markAsDone(String input) {
        String[] keywords = input.split(" ");
        String command = keywords[0];
        Integer idx = keywords.length > 1 ? Integer.parseInt(keywords[1]) - 1 : -1;
        Task task = listOfItems.get(idx);
        task.setDone();
        String confirmationMessage = "You have successfully completed task " + keywords[1] + ":" + "\n";
        outputWrapper(confirmationMessage + task);

    }
    /**
     * Handles the user's input and determines which command should be run.
     *
     * @param input
     */
    private static void handleInput(String input) {
        String keywords = input.split(" ")[0];
        switch(keywords) {
            case "items":
                outputWrapper(listOfItems);
                break;
            case "completed":
                markAsDone(input);
                break;
            default:
                listOfItems.add(new Task(input));
                outputWrapper("added \"" + input + "\" to list");
                break;
        }
    }

    /**
     * Starts the current session for the bot.
     */
    private static void startBot() {
        Scanner i = new Scanner(System.in);
        String input = i.nextLine();

        while (!input.equals(END_OF_CONVERSATION)) {
            handleInput(input);
            input = i.nextLine();
        }
        outputWrapper("Thanks for using me. See you again soon!");
        i.close();
    }

    /**
     * Echos input of the user
     */
    private static void echoInput() {
        Scanner i = new Scanner(System.in);
        String input = i.nextLine();
        while (!input.equals(END_OF_CONVERSATION)) {
            outputWrapper(input);
            input = i.nextLine();
        }
        outputWrapper("Thanks for using me. See you again soon!");
        i.close();

    }
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello this is Jeeves, your personal chatbot. What can i do you for today?");

        startBot();
    }
}
