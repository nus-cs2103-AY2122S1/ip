import java.util.Scanner;

public class Duke {
    /* Runs Scanner object, and echoes what is typed by user. If user says bye, then end the function. */
    public static void reply() {
        Scanner myObj = new Scanner(System.in);
        String command;
        while (true) {
            command = myObj.nextLine();
            if (command.equals("bye")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println(command);
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from \n" + logo);
        System.out.println("What can I do for you?");
        reply();
    }
}
