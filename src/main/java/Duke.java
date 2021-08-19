import java.util.Scanner;

public class Duke {
    private final static String BORDERS = "\t____________________________________________________________";
    private static String formatReply(String input) {
        return BORDERS + "\n\t " + input + '\n' + BORDERS;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        /* Initialization */
        Scanner sc = new Scanner(System.in);
        InputHandler inputH = new InputHandler();
        System.out.println(formatReply(inputH.greet("").msg()));
        String input;
        while (true) {
            try {
                input = sc.nextLine();
                Record r = inputH.query(input);
                System.out.println(formatReply(r.msg()));
                if (r.bye()) break;
            } catch (DukeException e) {
                System.out.println(formatReply(e.getMessage()));
            }
        }

    }
}
