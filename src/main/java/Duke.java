import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        String[] tasks = new String[100];
        int taskIndex = 0;

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        // check userInput
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                for (int i = 0; i < 100; i++) {
                    String task = tasks[i];
                    if (task == null) {
                        break;
                    } else {
                        System.out.println((i + 1) + ". " + task);
                    }
                }
            } else { // userInput is not "bye" and not "list"
                tasks[taskIndex] = userInput;
                System.out.println("added: " + userInput);
                taskIndex++;
            }
            userInput = sc.nextLine(); // get new userInput
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

}
