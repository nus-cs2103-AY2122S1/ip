import java.util.*;

public class Duke {
    private static Task[] list = new Task[100];
    private static int listIndex = 0;

    public static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void echo(String str) {
        System.out.println(str);
    }

    public static void add(String str) {
        Task newTask = new Task(str);
        list[listIndex] = newTask;
        System.out.println("added: " + str);
        listIndex++;
    }

    public static void complete(int completedTask) {
        if (!list[completedTask - 1].isDone) {
            list[completedTask - 1].markDone();
        }

        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("   [X] " + list[completedTask - 1].getDescription());
    }

    public static void getList() {
        int i = 0;
        while (i < listIndex) {
            int num = i+1;
            System.out.println(num + ". [" + list[i].getStatusIcon() + "] " + list[i].getDescription());
            i++;
        }
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        Duke.greet();

        Scanner sc = new Scanner(System.in);
        String str;


        while (!(str = sc.nextLine()).equals("bye")) {
            if (str.equals("list")) {
                Duke.getList();
            } else if (str.contains("done")) {
                Duke.complete(Character.getNumericValue(str.charAt(5)));
            } else {
                Duke.add(str);
            }
        }

        Duke.exit();

    }
}
