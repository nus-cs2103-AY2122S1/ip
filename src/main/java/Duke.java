import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "\nHello I'm Duke!\n"
                + "What can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!";
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello from\n" + logo + greeting);
        String input = scan.nextLine();

        while(!input.equals("bye")) {
            System.out.println(input);
            input = scan.nextLine();
        }

        System.out.println(exit);
    }
}
