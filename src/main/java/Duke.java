import java.util.Scanner;

public class Duke {

//    private static String logo = " ____        _        \n"
//            + "|  _ \\ _   _| | _____ \n"
//            + "| | | | | | | |/ / _ \\\n"
//            + "| |_| | |_| |   <  __/\n"
//            + "|____/ \\__,_|_|\\_\\___|\n";

    private static String line = "____________________________________________________________";

    /**
     * Wraps a given message above and below with lines and prints it
     *
     * @param msg
     */
    private static void wrap(String msg) {
        System.out.println(line);
        System.out.println(msg);
        System.out.println(line);
    }

    public static void main(String[] args) {
        // initialise scanner
        Scanner sc = new Scanner(System.in);

        // Greet
        wrap("Hello! I'm Bob\nWhat can I do for you?");

        // Echo
        String input = sc.nextLine();
        while (!input.equals("bye")){
            wrap(input);
            input = sc.nextLine();
        }

        // Exit
        wrap("Bye. Hope to see you again soon!");
    }
}
