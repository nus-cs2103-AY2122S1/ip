import java.util.Scanner;

public class Duke {
    private final static String welcome_default = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final static String line = "\t----------------------------------------------------\n";
    private final static String welcome_changed = line + "\t" + "Hewwo fweind, I am fuwwy, your personal assitant,\n" +
            "\t" + "How can I help you?\n" + line;

    private static void fuwwyEcho(String echo) {
        System.out.println(line + "\t" + echo + "\n" + line);
    }

    public static void main(String[] args) {
        System.out.println(welcome_changed);
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            fuwwyEcho(command);
            command = sc.nextLine();
        }

        fuwwyEcho("Bye. Hope to see you again soon!\n");
        sc.close();
    }
}
