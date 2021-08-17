import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String s;
        ArrayList<Task> list = new ArrayList<Task>();
        int taskNumber;
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
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i+1 + ". [" + list.get(i).getStatusIcon() + "] " + list.get(i).getdescription());
                }
                System.out.println("___________________________________________");
                continue;
            }

            if (s.substring(0,4).equals("done")) {
                System.out.println("___________________________________________");
                System.out.println("\tNice! I've marked this task as done:");
                taskNumber = Integer.parseInt(s.substring(5)) - 1;
                list.get(taskNumber).markAsDone();
                System.out.println("\t\t[" + list.get(taskNumber).getStatusIcon() + "] "
                        + list.get(taskNumber).getdescription());
                System.out.println("___________________________________________");
                continue;
            }

            Task t = new Task(s);

            System.out.println("___________________________________________");
            list.add(t);
            System.out.println("\tadded: " + s);
            System.out.println("___________________________________________");
        }
    }
}
