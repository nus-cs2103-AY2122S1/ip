import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String duke = "\nDuke: ";
        String user = "\nUser: ";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(duke + "Hi, what do you want from me?\n");
        System.out.print(user);

        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();
        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.println(duke + userInput);
            System.out.print(user);
            userInput = scanner.nextLine();
        }

        System.out.println(duke + "Bye. Have a nice day.");
    }
}
