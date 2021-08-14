import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        /* Initialization */
        Scanner sc = new Scanner(System.in);
        InputHandler inputH = new InputHandler(60);
        String input;
        while (true) {
            input = sc.next();
            if (!inputH.query(input)) break;
        }

    }
}
