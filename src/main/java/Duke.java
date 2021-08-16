import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        start();
    }

    private static void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        while (sc.hasNext()) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                exit();
                break;
            }
            echo(s);
        }
        sc.close();
    }

    private static void echo(String s) {
        System.out.println(s);
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
