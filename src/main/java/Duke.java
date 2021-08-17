import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String s;
        ArrayList<Task> list = new ArrayList<Task>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        System.out.println(logo);
        System.out.println("___________________________________________");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("___________________________________________");

        while(true) {
            Scanner input = new Scanner(System.in);
            s = input.nextLine();

            if (s.equals("bye")) {
                System.out.println("___________________________________________");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("___________________________________________");
                break;
            }

            if (s.equals("list")) {
                System.out.println("___________________________________________");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i+1 + ". " + list.get(i).getName());
                }
                System.out.println("___________________________________________");
                continue;
            }

            Task t = new Task(s);

            System.out.println("___________________________________________");
            list.add(t);
            System.out.println("\tadded: " + t.getName());
            System.out.println("___________________________________________");
        }
    }
}
