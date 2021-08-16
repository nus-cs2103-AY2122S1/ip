import java.util.Scanner;

public class Duke {
    static final String NAME = "Tze Henn";

    public static void lineGenerator() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        lineGenerator();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
        lineGenerator();

        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter command: ");
        String input = sc.next();


        while (!input.equals("bye")) {
            lineGenerator();
            System.out.println(input);
            lineGenerator();
            System.out.print("\nEnter command: ");
            input = sc.next();
        }
        lineGenerator();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
