import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<String> lst = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________\n");
        System.out.println("    Hello! I'm Irving.\n");
        System.out.println("    What can I do for you?\n");
        System.out.println("    ____________________________________________________________\n");
        String task = sc.nextLine();
        while (!task.equals("bye")) {
            if (task.equals("list")) {
                int num = 1;
                System.out.println("    ____________________________________________________________\n");
                for (String taskItem : lst) {
                    System.out.println("    " + num + ". " + taskItem);
                    num++;
                }
                System.out.println("    ____________________________________________________________\n");
                task = sc.nextLine();
                continue;
            }
            lst.add(task);
            System.out.println("    ____________________________________________________________\n");
            System.out.println("    added: " + task);
            System.out.println("    ____________________________________________________________\n");
            task = sc.nextLine();
        }
        System.out.println("    ____________________________________________________________\n");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________\n");
    }
}
