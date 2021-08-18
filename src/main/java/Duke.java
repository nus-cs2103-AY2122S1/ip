import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String userCommand = manager.getUserCommand(userInput);
            manager.processUserInput(userInput);

            if (userCommand.equals("bye")) {
                break;
            }
        }
        sc.close();
    }
}