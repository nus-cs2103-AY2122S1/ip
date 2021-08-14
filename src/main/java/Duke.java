import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Duke {
    private final String welcomeMessage = "Hello! I'm Duke\n" + "What can I do for you?";
    private final String borderLine = "\n____________________________________________________________\n";
    private final String goodbyeMessage = "Bye. Hope to see you again soon!";

    private List<String> inputList = new ArrayList<String>();

    public static void main(String[] args) {
        new Duke().runProgram();
    }

    public void runProgram() {

        //displays the welcome message
        this.displayText(welcomeMessage);

        //initialises the scanner
        Scanner sc = new Scanner(System.in);
        String input;

        while (true) {
            input = sc.nextLine().trim();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                this.displayListItems();
            } else {
                //echos the text input by the user
                this.displayText(input);

                //adds item input by the user into the inputList
                this.addItem(input);
            }
        }

        //terminates scanner
        sc.close();

        //displays goodbye message
        this.displayText(goodbyeMessage);
    }

    /**
     * Displays the message input given by the user with horizontal borderlines on both the top and bottom.
     * @param input String message to be displayed to user
     */
    public void displayText(String input) {
        System.out.println(borderLine + input + borderLine);
    }

    /**
     *
     */
    public void displayListItems() {
        System.out.println(borderLine);

        for (int i = 0; i < this.inputList.size(); i++) {
            String inputMessage = String.format("%d. %s", i+1, this.inputList.get(i));
            System.out.println(inputMessage);
        }

        System.out.println(borderLine);
    }

    /**
     *
     * @param input String message input by the user to be kept in the inputList
     */
    public void addItem(String input) {
        this.inputList.add(input);
    }
}
