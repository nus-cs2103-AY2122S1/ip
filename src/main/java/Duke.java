import java.util.Scanner;

public class Duke {
    private enum Prompt {

    }

    /*
    Reads the input from the user and triggers the logic for the command.
     */
    private static void NextCommand() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        if (command.equals("bye")) {
            EndDuke();
        } else {
            System.out.println(command);
            NextCommand();
        }
    }

    /*
    Closes and exits Duke.
     */
    private static void EndDuke() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke!\n" + "What can I do for you?");

        NextCommand();
    }
}
