import java.io.OptionalDataException;
import java.util.Scanner;

public class Duke {

    private static final String[] taskList = new String[100];
    private static int tasks = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you today? ");

        String userInput = "";

        while (!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            if (userInput.equals("list")) {
                for (int i = 0; i < tasks; i++) {
                    System.out.printf("%d. %s%n", i+1, taskList[i]);
                }
            } else if (userInput.equals("bye")) {
                System.out.println("Good bye.");
            } else {
                taskList[tasks] = userInput;
                tasks += 1;
                System.out.printf("added: %s\n", userInput);
            }
        }



    }
}
