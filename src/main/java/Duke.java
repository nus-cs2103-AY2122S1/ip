import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("What do you need to do today?");
        System.out.println("_______________________");

        Scanner commands = new Scanner(System.in);
        String task = commands.nextLine();
        int i = 0;

        while (!task.equals("bye")) {
            if (task.equals("list")) {
                System.out.println("_______________________");
                for (int j = 0; j < i; j++) {
                    System.out.printf("%d. %s%n", j + 1, tasks[j]);
                }
                System.out.println("_______________________");
            } else if (task.matches("done \\d{1}")){
                int taskNo = Integer.parseInt(task.substring(5));
                int taskIndex = taskNo - 1;
                if (taskIndex < 0 || taskIndex >= i) {
                    System.out.println("_______________________");
                    System.out.println("Oops! PLease enter a valid task number.");
                    System.out.println("_______________________");
                } else {
                    tasks[taskIndex].markDone();
                    System.out.println("_______________________");
                    System.out.println("Good job! I've marked this task as done:");
                    System.out.println(tasks[taskIndex]);
                    System.out.println("_______________________");
                }
            } else {
                tasks[i] = new Task(task);
                System.out.println("_______________________");
                System.out.printf("added: %s%n", task);
                System.out.println("_______________________");
                i += 1;
            }
            task = commands.nextLine();

        }
        System.out.println("_______________________");
        System.out.println("See you! Have a nice day!");
        System.out.println("_______________________");

    }
}
