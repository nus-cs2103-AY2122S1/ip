import java.util.ArrayList;
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
        ArrayList<String> list = new ArrayList<>();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        while (sc.hasNext()) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                exit();
                break;
            }
            if (s.equals("list")) {
                list(list);
                continue;
            }
            list.add(s);
            echo(s);
        }
        sc.close();
    }

    private static void echo(String s) {
        System.out.println("added: " + s);
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void list(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            System.out.println(String.format("%d. %s", i + 1, s));
        }
    }
}
