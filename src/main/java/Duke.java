import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String divider = "____________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        ArrayList<String> todoList = new ArrayList<>();

        System.out.println("Hello from\n" + logo +"\n");
        System.out.println("What can I do for you?");
        System.out.println(divider);

        Scanner in = new Scanner(System.in);
        String s = in.nextLine();

        while (!s.contentEquals("bye")) {

            System.out.println(divider);

            if (s.contentEquals("list")) {
                for (int i = 0; i < todoList.size(); i++) {
                    System.out.println((i + 1) + ". " + todoList.get(i));
                }
            } else {
                todoList.add(s);
                System.out.println("added: " + s);
            }

            System.out.println(divider);
            s = in.nextLine();
        }

        System.out.println(divider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(divider);
    }
}
