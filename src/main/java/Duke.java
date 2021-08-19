import java.util.Scanner;  // Import the Scanner class

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String command = myObj.nextLine();  // Read user input
        while (!command.equals("bye")) {
            System.out.println(command);
            command = myObj.nextLine();
        }
        if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}