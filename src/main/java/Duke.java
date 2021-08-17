import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Welcome to Ben's. How may I help you?");

        Scanner newScan = new Scanner(System.in);
        String userInput = newScan.nextLine();
        ArrayList<String> contents = new ArrayList<>();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                int counter = 1;
                for (String x:contents) {
                    System.out.println(counter + ". " + contents.get(counter - 1) + "\n");
                    counter++;
                }
            } else {
                System.out.println("    ***\n" + "    Added: " + userInput + "\n    ***");
                contents.add(userInput);
            }
            newScan = new Scanner(System.in);
            userInput = newScan.nextLine();
        }

        System.out.println("\nGoodbye! Have a nice day. :)");
        System.exit(0);
    }
}
