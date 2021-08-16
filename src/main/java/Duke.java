import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static boolean isRunning = false;
    private static ArrayList<Task> record = new ArrayList<>();
    private static Integer totalNumber = 0;

    public static void main(String[] args) {
        Duke.isRunning = true;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
        Scanner input = new Scanner(System.in);
        while (isRunning) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                exit();
            } else if (command.equals("list")) {
                displayList();
            } else if (command.startsWith("done")) {
                String substring = command.substring(5);
                int index = Integer.parseInt(substring);
                markAsDone(index);
            } else if (command.startsWith("todo")) {
                String substring = command.substring(5);
                addToDo(substring);
            } else if (command.startsWith("event")) {
                String substring = command.substring(6);
                String item = substring.substring(0, substring.indexOf("/"));
                String duration = substring.substring(substring.indexOf("/") + 1).substring(2);
                addEvent(item, duration);
            } else if (command.startsWith("deadline")) {
                String substring = command.substring(9);
                String item = substring.substring(0, substring.indexOf("/"));
                String deadline = substring.substring(substring.indexOf("/") + 1).substring(2);
                addDeadline(item, deadline);
            } else {
                populate(command);
            }
        }

    }

    private static void greeting() {
        System.out.println("------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("------------------\n");
    }

    private static void echo(String statement) {
        System.out.println("------------------");
        System.out.println(statement + "\n");
        System.out.println("------------------\n");
    }

    private static void exit() {
        Duke.isRunning = false;
        System.out.println("------------------");
        System.out.println("Bye. Hope to see you soon!\n");
        System.out.println("------------------\n");
    }

    private static void populate(String item) {
        Task task = new Task(item);
        record.add(task);
        Duke.totalNumber++;
        System.out.println("------------------");
        System.out.println("added: " + item + "\n");
        System.out.println("------------------\n");
    }

    private static void displayList() {
        Integer number = 1;
        System.out.println("------------------");
        for (Task a : record) {
            if (a.isCompleted()) {
                System.out.println(number.toString() + "." + a.logo() + "[X] " + a.toString());
            } else {
                System.out.println(number.toString() + "." + a.logo() + "[ ]" + a.toString());
            }
            number++;
        }
        System.out.println("------------------\n");
    }

    private static void markAsDone(int num) {
        record.get(num-1).setCompleted();
        System.out.println("------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + "[X] " + record.get(num-1).toString());
        System.out.println("------------------\n");
    }

    private static void addToDo(String item) {
        ToDo todo = new ToDo(item);
        record.add(todo);
        totalNumber++;
        System.out.println("------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println("  [T][ ] " + item);
        System.out.println("Now you have " + Duke.totalNumber.toString() + " in the list");
        System.out.println("------------------");
    }

    private static void addEvent(String item, String duration) {
        Event event = new Event(item, duration);
        record.add(event);
        totalNumber++;
        System.out.println("------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println("  [E][ ] " + item + "(at: " + duration + ")");
        System.out.println("Now you have " + Duke.totalNumber.toString() + " in the list");
        System.out.println("------------------");
    }

    private static void addDeadline(String item, String deadline) {
        Deadline dl = new Deadline(item, deadline);
        record.add(dl);
        totalNumber++;
        System.out.println("------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println("  [D][ ] " + item + "(by: " + deadline + ")");
        System.out.println("Now you have " + Duke.totalNumber.toString() + " in the list");
        System.out.println("------------------");
    }

}