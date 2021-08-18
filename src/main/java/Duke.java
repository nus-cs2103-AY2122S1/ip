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

        Scanner sc = new Scanner(System.in);
        boolean end = false;
        while (!end) {
            String input = sc.nextLine();
            String[] inputs = input.split(" ", 2);
            String cmd = inputs[0];
            System.out.println(separator);

            if (cmd.equals(endCmd)) {
                System.out.println("Bye bye! See you again soon!");
                end = true;
            } else if (cmd.equals(listCmd)) {
                displayList();
            } else if (cmd.equals(doneCmd)) {
                Integer taskNum = Integer.parseInt(inputs[1]);
                markTaskDone(taskNum);
            } else {
                addTask(input);
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

    private static void addTask(String description) {
        todoList.add(new Task(description));
        System.out.println("added: " + description);
    }

    private static void markTaskDone(Integer taskNum) {
        Task task = todoList.get(taskNum-1);
        task.markAsDone();
        System.out.println("Good work! I've marked this task as done:");
        System.out.println(task.toString());
    }
}
