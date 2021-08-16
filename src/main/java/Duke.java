import java.util.Scanner;

public class Duke {

    public static void greet() {
        System.out.println("Hello! I'm Duke" + '\n' + "What can I do for you?");
    }

    public static void start() {

        String exit = "bye";
        String input;
        String lineBreak = "========================================================================";

        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        System.out.println(lineBreak);

        if (input.equals(exit)) {
            System.out.println("Bye. Hope to see you again soon!" + '\n' + lineBreak);
        } else {
            System.out.println(input + '\n' + lineBreak);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        start();
    }
}
