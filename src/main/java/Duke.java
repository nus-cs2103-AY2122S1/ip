import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Duke: Hello from\n" + logo);
        String divider = "____________________________________________________________";
        System.out.println(divider);
        System.out.println("Duke: Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(divider);

        boolean exit = false;
        ArrayList<String> request = new ArrayList<String>();

        while (!exit) {
            System.out.print("You: ");
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            if (str.equals("bye")) {
                exit = true;
                break;
            } else if (str.equals("list")) {
                int count = 1;
                System.out.println(divider);
                for (String s : request) {
                    System.out.println(count + ". " + s);
                    count += 1;
                }
                System.out.println(divider);
            } else {
                request.add(str);
                System.out.println(divider);
                System.out.println("added: " + str);
                System.out.println(divider);
            }
        }

        System.out.println(divider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(divider);
    }
}
