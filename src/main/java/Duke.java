import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String divider = "____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(divider);
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(divider);
        // System.in: take user's input
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        while (!input.equals("bye")) {
            // prompt for next input only when invoke next()
            System.out.println(divider + "\n" + input + "\n" + divider);
            input = sc.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}





