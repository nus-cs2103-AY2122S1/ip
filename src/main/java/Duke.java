import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String Hello_message = "Hello! I'm Duke\n" +
                "What can I do for you?\n";
        String line = "____________________________________________________________\n";
        String Echo = "";
        String Goodbye_message = "Bye. Hope to see you again soon!\n";

        System.out.println(line+ Hello_message + line);

        //Use loop to determine if a user enters "bye" or not.
        while (true) {
            Echo = scanner.nextLine();
            if (!Echo.equals("bye")) {
                System.out.println(line + Echo + "\n" + line);
            } else {
                System.out.println(line + Goodbye_message + line);
                break;
            }
        }
    }
}
