import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> todoList;

    public static void main(String[] args) {
        String separator = "------------------------------------------------------------------";

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello I'm Duke :)");
        System.out.println("What can I do for you?");
        System.out.println(separator);

        todoList = new ArrayList<>();

        String endCmd = "bye";
        String listCmd = "list";
        String doneCmd = "done";
        String todoCmd = "todo";
        String deadlineCmd = "deadline";
        String eventCmd = "event";

        Scanner sc = new Scanner(System.in);
        boolean end = false;
        while (!end) {
            String input = sc.nextLine();
            String[] inputs = input.split(" ", 2);
            String cmd = inputs[0];
            String description = inputs.length > 1 ? inputs[1] : "";
            System.out.println(separator);

            if (cmd.equals(endCmd)) {
                System.out.println("Bye bye! See you again soon!");
                end = true;
            } else if (cmd.equals(listCmd)) {
                displayList();
            } else if (cmd.equals(doneCmd)) {
                markTaskDone(Integer.parseInt(description));
            } else if (cmd.equals(todoCmd)) {
                addTask(todoCmd, description);
            } else if (cmd.equals(deadlineCmd)) {
                addTask(deadlineCmd, description);
            } else if (cmd.equals(eventCmd)) {
                addTask(eventCmd, description);
            }
            System.out.println(separator);
        }
    }

    private static void displayList() {
        System.out.println("Your task list:");
        for (int i = 0; i < todoList.size(); i++) {
            Task task = todoList.get(i);
            int num = i+1;
            System.out.println(num + "." + task.toString());
        }
    }

    private static void addTask(String taskType, String description) {
        Task task;
        if (taskType == "todo") {
            task = new ToDo(description);
        } else if (taskType == "deadline") {
            String[] inputs = description.split(" /by ", 2);
            task = new Deadline(inputs[0], inputs[1]);
        } else if (taskType == "event") {
            String[] inputs = description.split(" /at ", 2);
            task = new Event(inputs[0], inputs[1]);
        } else {
            return;
        }
        todoList.add(task);
        System.out.println("I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + todoList.size() + " tasks in the list.");
    }

    private static void markTaskDone(Integer taskNum) {
        Task task = todoList.get(taskNum-1);
        task.markAsDone();
        System.out.println("Good work! I've marked this task as done:");
        System.out.println(task.toString());
    }
}
