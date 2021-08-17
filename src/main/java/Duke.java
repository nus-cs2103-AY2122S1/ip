import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        boolean exit = false;
        String[] tasks = new String[100];
        int current = 0;
        while (!exit) {
            String userInput = myObj.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                exit = true;
            } else if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < 100; i++) {
                    if (tasks[i] != null) {
                        System.out.println(i + 1 + ". " + tasks[i]);
                    }
                }
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________");
                tasks[current] = userInput;
                current++;
            }
        }
    }
}
