import java.util.Scanner;

public class Duke {
    private final static String BORDERS = "\t____________________________________________________________";
    private static void reply(String input) {
        System.out.print(BORDERS);
        System.out.print("\n\t ");
        System.out.println(input);
        System.out.println(BORDERS);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        /* Initialization */
        try {
            Scanner sc = new Scanner(System.in);
            InputHandler inputH = new InputHandler();
            reply(inputH.greet("").msg());
            String input;
            while (true) {
                try {
                    input = sc.nextLine();
                    Record r = inputH.query(input);
                    reply(r.msg());
                    if (r.bye()) break;
                } catch (DukeException e) {
                    reply(e.getMessage());
                }
            }
        } catch (DukeException e) {
            reply(e.getMessage());
        }

    }
}
