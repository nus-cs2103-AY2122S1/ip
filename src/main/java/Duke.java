import java.util.Scanner;

/**
 * Driver class to simulate the 'Annie' chat bot program.
 *
 * @author limzk126
 * @version Level-1
 */
public class Duke {
    private final String LINE = "______________________________________________________________\n";
    private final String WELCOME_MSG = "Hi I am Annie!\nHow can I assist you?";
    private final String GOODBYE_MSG = "Bye. See you soon!";

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

            if (textInput.equals("bye")) {
                isEnded = true;
            } else {
                printText(textInput);
            }
        }

        // Program ends. Say Goodbye.
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