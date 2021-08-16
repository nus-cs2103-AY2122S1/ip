import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        List<Task> list = new ArrayList<>();
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
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        while (!input.equals("bye")) {
            System.out.println(divider);
            if (input.equals("list")) {
                int index = 1;
                for (Task task : list) {
                    System.out.println(index + ". " + "[" + task.getStatusIcon() + "] " + task);
                    index++;
                }
            } else if (input.equals("done")) {
                int index = sc.nextInt();
                if (index < 1 || index > list.toArray().length) {
                    // TODO: handle error here
                    System.out.println("OOPS!!! I'm sorry, index is out of range!");
                } else {
                    Task task = list.get(index - 1);
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done: \n  [X] " + task);
                }
            } else {
                list.add(new Task(input));
                System.out.println("added: " + input);
            }
            System.out.println(divider);
            input = sc.next();
        }
        System.out.println(divider + "\n" + "Bye. Hope to see you again soon!" + "\n" + divider);
        sc.close();
    }
}











