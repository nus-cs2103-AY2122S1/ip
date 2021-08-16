import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    ____________________________________________________________\n"
                + "    Hello! I'm Duke \n" + "    What can I do for you?\n" + "    ____________________________________________________________\n");
        boolean end;
        end = true;
        while (end) {
            String text = scanner.nextLine();
            System.out.println("    ____________________________________________________________\n");
            if (text.equals("bye")) {
                end = false;
                System.out.println("    Bye. Hope to see you again soon!");
            } else {
                System.out.println("    " + text);
            }
            System.out.println("    ____________________________________________________________\n");
        }
    }
}
