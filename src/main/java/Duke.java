import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = " __          __  _                            _______    \n" +
                " \\ \\        / / | |                          |__   __|   \n" +
                "  \\ \\  /\\  / /__| | ___ ___  _ __ ___   ___     | | ___  \n" +
                "   \\ \\/  \\/ / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\    | |/ _ \\ \n" +
                "    \\  /\\  /  __/ | (_| (_) | | | | | |  __/    | | (_) |\n" +
                "     \\/  \\/ \\___|_|\\___\\___/|_|_|_| |_|\\___|    |_|\\___/ \n" +
                "               | \\ | |/ __ \\|  __ \\|  \\/  |              \n" +
                "               |  \\| | |  | | |__) | \\  / |              \n" +
                "               | . ` | |  | |  _  /| |\\/| |              \n" +
                "               | |\\  | |__| | | \\ \\| |  | |              \n" +
                "               |_| \\_|\\____/|_|  \\_\\_|  |_|\n";
        String divider = "─────────────────────────────────────────────────────────\n";
        String doubleDivider = "═════════════════════════════════════════════════════════\n";

        Scanner scanner = new Scanner(System.in);

        System.out.println(doubleDivider + greeting + doubleDivider);
        System.out.println("Please enter the text to be echoed.\n" + divider);

        while (true) {
            String next = scanner.nextLine();
            if (next.equals("bye") || next.equals("Bye")) {
                System.out.println(divider + "Bye. Hope to see you again soon!\n" + divider);
                break;
            }
            System.out.println(divider + next + "\n" + divider);
        }
    }
}
