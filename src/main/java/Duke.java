import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Welcome to Ben's. How may I help you?");

        Scanner newScan = new Scanner(System.in);
        String userInput = newScan.nextLine();

        while (!userInput.equals("bye")) {
            System.out.println("    ***\n" + "     " + userInput + "\n    ***");
            newScan = new Scanner(System.in);
            userInput = newScan.nextLine();
        }

        System.out.println("\nGoodbye! Have a nice day. :)");
        System.exit(0);
    }
}
