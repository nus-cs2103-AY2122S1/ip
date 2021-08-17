import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "What can I do for you?\n");
        while(true) {
            System.out.print("You: ");
            command = sc.nextLine();
            System.out.print("Duke: ");
            if (command.equals("bye")) {
                System.out.println("Nice talking to you, goodbye!");
                break;
            } else {
                System.out.println(command + "\n");
            }
        }
    }
}
