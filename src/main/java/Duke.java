import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        System.out.println("*******************************************");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("*******************************************");

        Scanner myScanner= new Scanner(System.in);
        boolean hasQuit = false;
        String[] tasks = new String[100];
        int numOfTasks = 1;
        // ArrayList<String> tasks = new ArrayList<>();

        while (!hasQuit && myScanner.hasNextLine()) {
            String userInput = myScanner.nextLine();
            if (userInput.equals("bye")) {
                hasQuit = true;
                System.out.println("Bye. Hope to see you again soon!");
            } else if (userInput.equals("list")) {
                for (int i = 0; i < numOfTasks - 1; i++) {
                    System.out.println(tasks[i]);
                }
            } else {
                tasks[numOfTasks - 1] = numOfTasks + ". " + userInput;
                numOfTasks += 1;
                System.out.println("added: " + userInput);
            }
            System.out.println("*******************************************");
        }
        myScanner.close();
    }
}

