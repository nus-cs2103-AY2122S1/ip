import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        boolean end = false;

        while(!end) {
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();

            if (command.equalsIgnoreCase("bye")) {
                end = !end;
                System.out.println("Bye. Hope to see you again soon!");
            } else {
                System.out.println(command);
            }
        }

    }
}
