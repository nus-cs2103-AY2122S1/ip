import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Welcome to Ben's. How may I help you?");

        Scanner newScan = new Scanner(System.in);
        String userInput = newScan.nextLine();
        ArrayList<Task> contents = new ArrayList<>();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                int counter = 1;
                System.out.println("    ***\n" + "    These are your tasks in the list:");
                for (Task x: contents) {
                    System.out.println("      " + counter + ". " + x.getStatusIcon() + " " +
                            x.getDescription());
                    counter++;
                }
                System.out.println("    ***\n");
            } else if (userInput.contains("done")) {
                String index = userInput.substring(5, userInput.length());
                int x = Integer.parseInt(index);
                Task temp = contents.get(x - 1);
                temp.markedDone();
                System.out.println("    ***\n" + "    You have successfully done this task:\n" +
                        "      " + temp.getStatusIcon() + " " + temp.getDescription() + "\n    ***\n");
            } else {
                Task newTask = new Task(userInput);
                contents.add(newTask);
            }
            newScan = new Scanner(System.in);
            userInput = newScan.nextLine();
        }

        System.out.println("\nGoodbye! Have a nice day. :)");
        System.exit(0);
    }
}