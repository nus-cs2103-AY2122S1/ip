import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        String format = "\t%s\n";
        String horizontalLine = "______________________________________________________";

        System.out.print(logo);
        System.out.printf(format, horizontalLine);
        System.out.printf(format, "Hello there, I'm Duke!");
        System.out.printf(format, "What can I do for you today?");
        System.out.printf(format, horizontalLine);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.printf(format, horizontalLine);
                System.out.printf(format, "Goodbye. Have a nice day!");
                System.out.printf(format, horizontalLine);
                break;
            } else {
                System.out.printf(format, horizontalLine);
                System.out.printf(format, input);
                System.out.printf(format, horizontalLine);
            }
        }
    }
}
