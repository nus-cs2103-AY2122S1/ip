import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Hello! I'm Duke\n" +   // Welcome Message
                "What can I do for you?");

        String input = scanner.nextLine();  // Read user input
        while (!input.equals("bye")){
            System.out.println("Username is: " + input);  // Output user input
            input = scanner.nextLine();  // Read user input
        }

    }
}
