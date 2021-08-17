import java.util.Scanner;

public class Duke {

    private final static String LOGO =  " ____        _        \n"
                                        + "|  _ \\ _   _| | _____ \n"
                                        + "| | | | | | | |/ / _ \\\n"
                                        + "| |_| | |_| |   <  __/\n"
                                        + "|____/ \\__,_|_|\\_\\___|\n";

    private final static String TERMINATION_COMMAND = "bye";

    public static void main(String[] args) {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("What can I do for you?");
        Scanner inputScanner = new Scanner(System.in);
        String input = inputScanner.nextLine();
        while (!(input.equals(TERMINATION_COMMAND))) {
            System.out.println(input);
            input = inputScanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
