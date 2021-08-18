import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private boolean active;
    private final List<Task> list;

    public Duke() {
        this.active = true;
        this.list = new ArrayList<Task>();
    }

    public void executeCommand(String input) {
        String[] parsedInput = input.split(" ");
        switch (parsedInput[0]) {
            case "bye":
                this.terminate();
                break;
            case "list":
                this.listTasks();
                break;
            case "done":
                this.done(Integer.parseInt(parsedInput[1]) - 1);
                break;
            case "todo":
                this.addTodo(input);
                break;
            case "deadline":
                this.addDeadline(input);
                break;
            case "event":
                this.addEvent(input);
                break;
            default:
                this.addTask(input);
        }
    }

    public void terminate() {
        this.active = false;
        String message =
                "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(message);
    }

    public void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            Task task = this.list.get(i);
            System.out.println(String.format(" %d.%s", i + 1, task.toString()));
        }
        System.out.println("____________________________________________________________\n");
    }

    public void addTask(String input) {
        this.list.add(new Task(input));
        String message =
                "____________________________________________________________\n" +
                " added: " + input + "\n" +
                "____________________________________________________________\n";
        System.out.println(message);
    }

    public void addTodo(String input) {
        String toDo = input.substring(5);
        ToDo newToDo = new ToDo(toDo);
        this.list.add(newToDo);
        String message =
                "____________________________________________________________\n" +
                " Got it. I've added this task:\n   " +
                newToDo.toString() +
                String.format("\n Now you have %d tasks in the list.\n", this.list.size()) +
                "____________________________________________________________\n";
        System.out.println(message);
    }

    public void addDeadline(String input) {
        int delimiter = input.indexOf("/by");
        String task = input.substring(9, delimiter-1);
        String date = input.substring(delimiter + 4);
        Deadline newDeadline = new Deadline(task, date);
        this.list.add(newDeadline);
        String message =
                "____________________________________________________________\n" +
                " Got it. I've added this task:\n   " +
                newDeadline.toString() +
                String.format("\n Now you have %d tasks in the list.\n", this.list.size()) +
                "____________________________________________________________\n";
        System.out.println(message);
    }

    public void addEvent(String input) {
        int delimiter = input.indexOf("/at");
        String task = input.substring(6, delimiter-1);
        String time = input.substring(delimiter + 4);
        Event newEvent = new Event(task, time);
        this.list.add(newEvent);
        String message =
                "____________________________________________________________\n" +
                " Got it. I've added this task:\n   " +
                newEvent.toString() +
                String.format("\n Now you have %d tasks in the list.\n", this.list.size()) +
                "____________________________________________________________\n";
        System.out.println(message);
    }

    public void done(int index) {
        Task task = this.list.get(index);
        task.setCompleted();
        String message =
                "____________________________________________________________\n" +
                " Nice! I've marked this task as done: \n" +
                String.format("   [%s] %s\n", task.getCompletedMarker(), task.getTask()) +
                "____________________________________________________________\n";
        System.out.println(message);
    }

    public void echo(String input) {
        String message =
                "____________________________________________________________\n" +
                " " + input + "\n" +
                "____________________________________________________________\n";
        System.out.println(message);
    }

    public void run(Scanner sc) {
        String message =
                "____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        System.out.println(message);
        while (this.active) {
            String input = sc.nextLine();
            this.executeCommand(input);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        duke.run(sc);
    }
}

