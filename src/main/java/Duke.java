import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke :) \nWhat can I do for you?");
        for (int i = 0; i < 60; i++) {
            System.out.print("_");
        }
        System.out.println();

        Task[] listOfTasks = new Task[100];
        int count = 0;

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < count; i++) {
                    Task t = listOfTasks[i];
                    System.out.println((i + 1) + ".[" + t.getStatusIcon() + "] " + t.name);
                }
            } else if (input.contains("done")) {
                String[] x = input.split(" ");
                int len = x.length;
                if (len == 1) {
                    System.out.println("Please input a number after a space, eg. done 5");
                    continue;
                }
                int index = Integer.parseInt(x[len - 1]) - 1;
                if (index >= count) {
                    System.out.println("No task of this number. Add new task or input a different number.");
                    continue;
                } else if (index < 0) {
                    System.out.println("Input a task number from 1 - " + count);
                    continue;
                }
                Task t = listOfTasks[index];
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done: \n[X] " + t.name);
            } else {
                System.out.println("added: " + input);
                listOfTasks[count] = new Task(input);
                count++;
            }
        }
        in.close();
    }
}
