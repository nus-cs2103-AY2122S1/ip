import java.util.Scanner;

public class Duke {
    // constants for special commands
    private static final String CMD_BYE = "bye";

    private static void displayGreetings() {
        System.out.println("\tHello this is Yiyang-bot :D");
        System.out.println("\tWhat can I do for you?");
    }

    private static void displayBye() {
        System.out.println("\tBye. Hope to see you again.");
    }

    public static void main(String[] args) {
        displayGreetings();

        Scanner sc = new Scanner(System.in);
        String currCommand = sc.nextLine();

        while (!currCommand.equals(CMD_BYE)) {
            System.out.println("\t" + currCommand);

            currCommand = sc.nextLine();
        }

        displayBye();
    }
}