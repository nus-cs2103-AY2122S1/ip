import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /* // in case we might need the logo later
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
        // greet the user
        String greeting = "\t Hello! I'm Duke\n" +
                "\t What can I do for you?\n" ;
        System.out.println(reply(greeting));
        // listen to user input
        Scanner scan = new Scanner(System.in);
        String command = scan.nextLine();
        while (!command.equals("bye")) {
            System.out.println(reply(echo(command)));
            command = scan.nextLine();
        }
        // farewell the user
        String bye = "\t Bye. Hope to see you again soon!\n";
        System.out.println(reply(bye));
    }

    private static String reply(String content) {
        // wrap the reply in a divider
        String divider = "\t____________________________________________________________";
        return divider + "\n" + content + divider;
    }

    private static String echo(String input) {
        // echo back user commands
        return String.format("\t %s\n", input);
    }
}


