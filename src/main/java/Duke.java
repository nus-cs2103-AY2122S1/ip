import java.util.Scanner;

public class Duke {

    public static void echo(String input) {
        String echoMessage = "   _____________________________________\n"
                        + "     " + input + "\n"
                        + "   _____________________________________\n";
        System.out.println(echoMessage);
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String WelcomeMessage = "   _____________________________________\n"
                            + "     Hello! I'm Duke\n"
                            + "     What can I do for you?\n"
                            + "   _____________________________________\n";

        String ByeMessage = "   _____________________________________\n"
                        + "     Bye. Hope to see you again soon!\n"
                        + "   _____________________________________\n";

        System.out.println("Hello from\n" + logo + WelcomeMessage);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!(input.equals("bye"))) {
            echo(input);
            input = sc.nextLine();
        }

        System.out.println(ByeMessage);

    }
}
