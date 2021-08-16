import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        String greet = "____________________________________________________________\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(greet);

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> library = new ArrayList<>(100);

        while (true) {
            String input = scanner.nextLine();
            String output = "";

            if (input.equals("list")) {
                output += "Here are the tasks in your list:\n";

                int count = 1;
                for (Task task: library) {
                    output += String.format("%d.[%s] %s\n", count++, task.getStatus(), task);
                }
            }
            else if (input.equals("blah")) {
                output = "blah";
            }
            else if (input.equals("bye")) {
                break;
            }
            else if (input.contains("done")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;

                Task target = library.get(index);
                target.setDone();

                output += "Nice! I've marked this task as done:\n";
                output += String.format("[%s] %s\n", target.getStatus(), target);
            }
            else {
                Task newTask = new Task(input);
                library.add(newTask);

                output = String.format("added: %s\n", input);
            }

            System.out.println(output);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
