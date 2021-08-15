import java.util.Scanner;
public class Duke {
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static String line = "____________________________________________________________";
    static String indent = "    ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(indent + line + "\n" +
                            indent + "Hello, I'm Duke!\n" +
                            indent + "How can I help you?\n" +
                            indent + line);
        String in = sc.nextLine();
        while(!in.equals("bye")) {
            System.out.println(indent + line + "\n" +
                                indent + in + "\n" +
                                indent + line);
            in = sc.nextLine();
        }
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }
}
