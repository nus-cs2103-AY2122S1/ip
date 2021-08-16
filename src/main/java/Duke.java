import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static final String NAME = "Tze Henn";
    static final ArrayList<Task> list = new ArrayList<>();

    public static void lineGenerator() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        lineGenerator();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
        lineGenerator();

        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter command: ");
        String input = sc.nextLine();


        while (!input.equals("bye")) {
            lineGenerator();
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < list.size(); i++) {
                    System.out.print((i + 1) + ".");
                    list.get(i).printTaskStatus();
                }
            } else if (input.substring(0, 4).equals("done")) {
                System.out.println("Nice! I've marked this task as done: ");
                String[] splitStr = input.split("\\s+");
                list.get(Integer.parseInt(splitStr[1]) - 1).markTaskDone();
            } else {
                System.out.println("added: " + input);
                Task t = new Task(input);
                list.add(t);
            }
            lineGenerator();
            System.out.print("\nEnter command: ");
            input = sc.nextLine();
        }
        lineGenerator();
        System.out.println("Bye. Hope to see you again soon!");
        lineGenerator();
    }
}
