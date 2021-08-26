import com.sun.source.tree.DoWhileLoopTree;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "____________________________________________________________";
        ArrayList<Task> task = new ArrayList<>();
        System.out.println("Hello from\n" + logo + line + "\nWhat can I do for you?\n" + line);

        String input;
        Scanner s = new Scanner(System.in);
        input = s.nextLine();

        while(!input.equals("bye")) {
            String[] parts = input.split(" ");
            if(input.equals("list")) {
                int i = 1;
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for(Task t: task) {
                    System.out.println(i + "." + t.toString());
                    i++;
                }
                System.out.println(line);
            } else if (parts[0].equals("done")) {
                int index = Integer.valueOf(parts[1]);
                Task thisTask = task.get(index - 1);
                thisTask.markDone();
                System.out.println(line);
                System.out.println("Nice! I've marked this task as done:\n" + thisTask.toString());
                System.out.println(line);
            } else if (parts[0].equals("delete")) {
                int index = Integer.valueOf(parts[1]);
                Task thisTask = task.remove(index - 1);
                System.out.println(line);
                System.out.println("Noted I've removed this task:\n" + thisTask.toString());
                System.out.println("Now you have " + task.size() + " tasks in your list");
                System.out.println(line);
            } else if (parts.length == 1) {
                System.out.println(line);
                System.out.println("OOPS!!! The description of a " + parts[0] + " cannot be empty.");
                System.out.println(line);
            } else if (parts[0].equals("deadline")){
                String[] part2 = input.split("/by ");
                String description = part2[0].split("deadline ")[1];
                Task deadline = new Deadline(description, part2[1]);
                System.out.println(line + "\n" + "added: " + deadline.toString());
                task.add(deadline);
                System.out.println("Now you have " + task.size() + " tasks in your list");
                System.out.println(line);
            } else if (parts[0].equals("event")){
                String[] part2 = input.split("/at ");
                String description = part2[0].split("event ")[1];
                Task event = new Event(description, part2[1]);
                System.out.println(line + "\n" + "added: " + event.toString());
                task.add(event);
                System.out.println("Now you have " + task.size() + " tasks in your list");
                System.out.println(line);
            } else if (parts[0].equals("todo")){
                String[] part2 = input.split("/");
                String description = part2[0].split("todo ")[1];
                Task todo = new Todo(description);
                System.out.println(line + "\n" + "added: " + todo.toString());
                task.add(todo);
                System.out.println("Now you have " + task.size() + " tasks in your list");
                System.out.println(line);
            } else {
                System.out.println(line);
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(line);
            }
            input = s.nextLine();
        }

        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }
}
