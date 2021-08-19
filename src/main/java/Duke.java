import java.util.Scanner;

public class Duke {
    private void echo() {
        Scanner echo = new Scanner(System.in);
        while (echo.hasNextLine()) {
            String next = echo.nextLine();
            if (next.equalsIgnoreCase("bye")) {
                System.out.println("    ------------------------------\n"
                        + "    Bye. Hope to see you again soon!\n"
                        + "    ------------------------------");
                echo.close();
                break;
            } else {
                System.out.println("    ------------------------------\n"
                        + "    " + next + "\n"
                        + "    ------------------------------");
            }
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String hello = "    ------------------------------\n"
                + "    Hello! I'm Duke\n"
                + "    What can I do for you?\n"
                + "    ------------------------------\n";

        System.out.println("Hello from\n" + logo + hello);
        Duke duke = new Duke();
        duke.echo();
    }
}
