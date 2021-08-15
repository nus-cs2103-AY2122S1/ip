import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        List<String> list = new ArrayList();
        String divider = "____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(divider);
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(divider);
        // System.in: take user's input
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        while (!input.equals("bye")) {
            // System.out.println(divider + "\n" + input + "\n" + divider);
            if (input.equals("list")) {
                System.out.println(divider);
                int index = 1;
                for (String item : list) {
                    System.out.println(index + ". " + item);
                    index++;
                }
                System.out.println(divider);
            } else {
                list.add(input);
                System.out.println(divider + "\n" + "added: " + input + "\n" + divider);
                // prompt for next input only when invoke next()
            }
            input = sc.next();
        }
        System.out.println(divider + "\n" + "Bye. Hope to see you again soon!" + "\n" + divider);
        sc.close();
    }
}





