import java.util.ArrayList;
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
        ArrayList<String> todos = new ArrayList<>(100);

        Scanner sc = new Scanner(System.in);
        System.out.println(indent + line + "\n" +
                            indent + "Hello, I'm Duke!\n" +
                            indent + "How can I help you?\n" +
                            indent + line);
        String in = sc.nextLine();
        while(!in.equals("bye")) {
            if(in.equals("list")) {
                System.out.println(indent + line);
                for (int i = 0; i < todos.size(); i++) {
                    System.out.println(indent + (i + 1) + ": " + todos.get(i));
                }
                System.out.println(indent + line);
            } else {
                System.out.println(indent + line + "\n" +
                                    indent + "added: " + in + "\n" +
                                    indent + line);
                todos.add(in);
            }
            in = sc.nextLine();
        }
        System.out.println(indent + line + "\n" +
                            indent + "Bye. Hope to see you again soon!\n" +
                            indent + line);
    }
}
