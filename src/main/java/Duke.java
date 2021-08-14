import java.util.Scanner;

public class Duke {
    private final String welcomeMessage = "Hello! I'm Duke\n" + "What can I do for you?";
    private final String borderLine = "\n____________________________________________________________\n";
    private final String goodbyeMessage = "Bye. Hope to see you again soon!";

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
            }

            //echos the text input by the user
            this.displayText(input);
        }

        //terminates scanner
        sc.close();

        //displays goodbye message
        this.displayText(goodbyeMessage);
    }

    public void displayText(String input) {
        System.out.println(borderLine + input + borderLine);
    }
}
