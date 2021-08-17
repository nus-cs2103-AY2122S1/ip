import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void greet(){
        print("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public static void print(String message) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }

    public static void print(ArrayList<String> ls) {
        String line = "____________________________________________________________";
        System.out.println(line);
        for (int i = 0; i < ls.size() ; i++) {
            System.out.println((i + 1) + ". " + ls.get(i));
        }
        System.out.println(line);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        ArrayList<String> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        greet();
        String next = sc.nextLine();
        while (!next.equals("bye")) {
            if (next.equals("list")) {
                print(tasks);
            } else {
                tasks.add(next);
                print("added: " + next);
            }
            next = sc.nextLine();

            //            print(next);
        //            next = sc.nextLine();
        }
        print("Bye. Hope to see you again soon!");
    }
}
