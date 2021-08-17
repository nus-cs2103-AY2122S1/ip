import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String command;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        while(true) {
            System.out.print(">> ");
            command = in.next();
            if (command.equals("bye")) break;
            else System.out.println(command);
        }
        System.out.println("Bye, hope to see you again!");
    }
}
