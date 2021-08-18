import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] tasks = new String[100];
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
                    System.out.printf("%d. %s%n", j+1, tasks[j]);
                }
                System.out.println("_______________________");
            } else {
                tasks[i] = task;
                System.out.println("_______________________");
                System.out.printf("added: %s%n", tasks[i]);
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
