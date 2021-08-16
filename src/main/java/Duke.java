import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        final String lineBreak = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo + "What can I do for you?");
        System.out.println(lineBreak);

        while (true) {
            String input = sc.next();
            System.out.println(lineBreak);

            if (input.equals("bye")) {
                System.out.println("Duke says: Bye. Hope to see you again soon!");
                System.out.println(lineBreak);
                break;
            }

            System.out.println("Duke says: " + input);
            System.out.println(lineBreak);

        }
    }
}
