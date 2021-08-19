import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        ArrayList<String> history = new ArrayList<String>();

        while (!a.equals("bye")) {
            if (a.equals("list")) {
                int length = history.size();
                for (int i = 0; i < length; i++) {
                    System.out.println(String.valueOf(i + 1) + ". " + history.get(i));
                }
                a = sc.nextLine();
            } else {
                System.out.println(a);
                history.add(a);
                a = sc.nextLine();
            }
        }
        //if (a.equals("bye")) {
            System.out.println("Bye! Hope to see you again soon!");
            sc.close();
        //}

    }
}
