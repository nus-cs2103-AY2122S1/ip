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

    public static void add(String str) throws DukeException {
        Task newTask = null;
        String[] arr1 = str.split(" ", 2);
        String type = arr1[0];
        String desc;
        String date;
        String[] arr2;

        switch (type) {
            case "todo":
                if (Todo.isValid(arr1)) {
                    desc = arr1[1];
                    newTask = new Todo(desc);
                }
                break;
            case "deadline":
                if (Deadline.isValid(arr1)) {
                    arr2 = arr1[1].split(" /", 2);
                    desc = arr2[0];
                    date = arr2[1].substring(3);
                    newTask = new Deadline(desc, date);
                }
                break;
            case "event":
                if (Event.isValid(arr1)) {
                    arr2 = arr1[1].split(" /", 2);
                    desc = arr2[0];
                    date = arr2[1].substring(3);
                    newTask = new Event(desc, date);
                }
                break;
            default:
                throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        list[listIndex] = newTask;
        String word;
        if (listIndex == 0) {
            word = "task";
        } else {
            word = "tasks";
        }

        System.out.println("Got it. I've added this task:");
        System.out.printf("   [%s][%s] %s\n", newTask.getType(), newTask.getStatusIcon(), newTask.getDescription());
        System.out.printf("Now you have %d %s in the list.%n", listIndex + 1, word);

        listIndex++;
    }

    public static void complete(int completedTask) {
        Task currentTask = list[completedTask - 1];
        if (!currentTask.isDone) {
            currentTask.markDone();
        }

        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("[%s][X] %s", currentTask.getType(), currentTask.getDescription());
    }

    public static void getList() {
        int i = 0;
        while (i < listIndex) {
            int num = i+1;
            System.out.printf("%d. [%s][%s] %s\n", num, list[i].getType(), list[i].getStatusIcon(), list[i].getDescription());
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
            try {
                if (str.equals("list")) {
                    Duke.getList();
                } else if (str.contains("done")) {
                    Duke.complete(Character.getNumericValue(str.charAt(5)));
                } else {
                    Duke.add(str);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        Duke.exit();

    }
}
