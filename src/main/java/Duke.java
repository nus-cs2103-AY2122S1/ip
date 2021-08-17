import java.util.*;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<>(100);
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

        list.add(listIndex, newTask);
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
        Task currentTask = list.get(completedTask - 1);
        if (!currentTask.isDone) {
            currentTask.markDone();
        }

        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("[%s][X] %s\n", currentTask.getType(), currentTask.getDescription());
    }

    public static void delete(int deleteTask) {
        Task delTask = list.remove(deleteTask - 1);
        listIndex--;
        System.out.println("Noted. I've removed this task:");
        System.out.printf("[%s][%s] %s\n", delTask.getType(), delTask.getStatusIcon(), delTask.getDescription());

        String word;
        if (listIndex == 1) {
            word = "task";
        } else {
            word = "tasks";
        }

        System.out.printf("Now you have %d %s in the list.%n", listIndex, word);
    }

    public static void getList() {
        int i = 0;
        System.out.println("Here are the tasks in your list:");
        while (i < listIndex) {
            int num = i+1;
            Task curr = list.get(i);
            System.out.printf("%d. [%s][%s] %s\n", num, curr.getType(), curr.getStatusIcon(), curr.getDescription());
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
                } else if (str.contains("delete")) {
                    Duke.delete(Character.getNumericValue(str.charAt(7)));
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
