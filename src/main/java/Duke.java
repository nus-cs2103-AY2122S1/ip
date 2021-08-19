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
        String line = "❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤";
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
            }else {
                System.out.println(line + "\n" + "added: " + input + "\n" + line);
                task.add(new Task(input));
            }
            input = s.nextLine();
        }

        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }
}
