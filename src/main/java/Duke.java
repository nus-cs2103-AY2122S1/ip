import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private final ArrayList<Task> taskList = new ArrayList<>();

    public void run() {

        greet();

        String input;
        Scanner sc = new Scanner(System.in);

        while(!(input = sc.nextLine()).equals("bye")) {
            String[] words = input.split(" ");
            String command = words[0];
            if (command.equals("list")) {
                displayList();
            } else if (command.equals("done")) {
                String taskIndex = words[words.length - 1];
                markDone(taskIndex);
            } else if (command.equals("deadline")) {
                // excludes command "deadline " from the string
                addDeadline(input.substring(9));
            } else if (command.equals("todo")) {
                // excludes command "todo " from the string
                addTodo(input.substring(5));
            } else if (command.equals("event")) {
                // excludes command "event " from the string
                addEvent(input.substring(6));
            }
        }

        sc.close();

        exit();
    }

    private void greet() {
        System.out.println("__________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("__________________________________");
    }

    private void exit() {
        System.out.println("__________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("__________________________________");
    }

    private void addToList(Task task) {
        this.taskList.add(task);
        System.out.println("__________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("added: " + task);
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
        System.out.println("__________________________________");
    }

    private void displayList() {
        System.out.println("__________________________________");
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println(i + 1 + ". " + task);
        }
        System.out.println("__________________________________");
    }

    private void markDone(String taskIndex) {
        int intTaskIndex = Integer.parseInt(taskIndex) - 1;
        Task task = this.taskList.get(intTaskIndex);
        task.markAsDone();
        System.out.println("__________________________________");
        System.out.println("Nice! I've marked this task as done:\n" + task);
        System.out.println("__________________________________");
    }

    private void addDeadline(String deadline) {
        String[] result = deadline.split("/by");
        String description = result[0].trim();
        String by = result[1].trim();
        Deadline d = new Deadline(description, by);
        addToList(d);
    }

    private void addTodo(String todo) {
        Todo tempTask = new Todo(todo);
        addToList(tempTask);
    }

    private void addEvent(String event) {
        String[] result = event.split("/at");
        String description = result[0].trim();
        String at = result[1].trim();
        Event e = new Event(description, at);
        addToList(e);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
