import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private final static String welcome_default = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final static String line = "\t----------------------------------------------------\n";
    private final static String welcome_changed = line + "\t" + "Hewwo fweind, I am fuwwy, your personal assitant,\n" +
            "\t" + "How can I help you?\n" + line;
    private static ArrayList<String> tasklist = new ArrayList<>();

    private static void fuwwyEcho(String echo) {
        System.out.println(line + "\t" + echo + "\n" + line);
    }

    private static void addTask(String task) {
        tasklist.add(task);
        fuwwyEcho("Uwu added:\t"
                + task);
    }

    public static void main(String[] args) {
        System.out.println(welcome_changed);
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {

            if (command.equals("list")) {
                String output = "\n";
                for (int i = 0; i < tasklist.size(); i++) {
                    int n = i + 1;
                    String list = "\t" + n + ". " + tasklist.get(i);
                    output += list + "\n";
                }
                fuwwyEcho(output);
                command = sc.nextLine();
            } else {
                addTask(command);
                command = sc.nextLine();
            }
        }

        fuwwyEcho("Bye. Hope to see you again soon!\n");
        sc.close();
    }
}
