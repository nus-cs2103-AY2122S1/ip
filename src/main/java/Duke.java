import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
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
            System.out.println("    ____________________________________________________________\n");
            System.out.println("    " + task);
            System.out.println("    ____________________________________________________________\n");
            task = sc.nextLine();
        }
        System.out.println("    ____________________________________________________________\n");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________\n");
    }
}
