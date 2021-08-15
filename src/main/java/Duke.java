import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Driver class to simulate the 'Annie' chat bot program.
 *
 * @author limzk126
 * @version Level-2
 */
public class Duke {
    private final String LINE = "______________________________________________________________\n";
    private final String WELCOME_MSG = "Hi I am Annie!\nHow can I assist you?";
    private final String GOODBYE_MSG = "Bye. See you soon!";

    // List to store previous user text inputs.
    private List<String> textList = new ArrayList<>();

    // Flag to indicate if program is ended by user.
    private boolean isEnded = false;

    /*
     * Prints a horizontal line, followed by the text input by user on a newline,
     * then finally a horizontal line on a newline.
     */
    private void printText(String text) {
        System.out.printf("%s", LINE);
        System.out.println(text);
        System.out.printf("%s\n", LINE);
    }

    // Adds text to the list and notifies the user that it has successfully done so.
    private void addText(String text) {
        String message = "added: " + text;
        printText(message);
        textList.add(text);
    }

    /*
     * Prints a horizontal line, followed by a numbered list of previous user text inputs,
     * then finally a horizontal line on a newline.
     */
    private void printList() {
        int taskNum = 0;

        System.out.printf("%s", LINE);

        // Prints a numbered list of previous text inputs.
        for (String text : textList) {
            System.out.printf("%d. %s\n", ++taskNum, text);
        }

        System.out.printf("%s\n", LINE);
    }

    // Processes text to find out what command user has issued to the program.
    private void parseText(String text) {
        if (text.equals("bye")) {
            // End program.
            isEnded = true;
        } else if (text.equals("list")) {
            // List all previous text.
            printList();
        } else {
            // Add text to list.
            addText(text);
        }
    }

    /**
     * Method to simulate the program.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        String textInput = "";

        // Program starts. Say hello.
        printText(WELCOME_MSG);

        // While loop to continuously receive user input.
        while (!isEnded) {
            textInput = sc.nextLine().trim();
            parseText(textInput);
        }

        // Program ends. Say goodbye.
        printText(GOODBYE_MSG);

        sc.close();
    }

    /**
     * Driver method to start program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}