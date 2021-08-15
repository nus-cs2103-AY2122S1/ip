import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static class Task {
        protected boolean completed = false;
        protected String name;

        public Task(String name) {
            this.name = name;
            this.completed = false;
        }

        public void markComplete() {
            this.completed = true;
        }

        public String getStatusIcon() {
            return this.completed ? "X" : " ";
        }

        @Override
        public String toString() {
            return String.format("[%s] %s", this.getStatusIcon() , this.name);
        }
    }
    private static final Scanner scanner = new Scanner(System.in);
    private static final String ENDING_COMMAND = "bye";

    private static List<Task> taskList = new ArrayList<>();

    private static void say(String message) {
        System.out.println(String.format("Iris: %s", message));
    }

    private static void say(String message, boolean isFirst) {
        String name = isFirst ? "Iris:": "     ";
        System.out.printf("%s %s%n", name, message);
    }

    private static String prompt() {
        System.out.print("me: ");
        return scanner.nextLine();
    }

    private static void echo(String message) {
        say(message);
    }

    private static void add(String item) {
        say(String.format("added: %s", item));
        taskList.add(new Task(item));
    }

    private static void done(int index) {
        Task task = taskList.get(index - 1);
        task.markComplete();
        say(String.format("Good job! I've marked this task as done: %s", task));
    }

    private static void listTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            say(String.format("%d. %s", i + 1, taskList.get(i)), i == 0);
        }
    }

    public static void main(String[] args) {
        say("Hello! I'm Iris. What can I do for you?");
        String command = prompt();
        while (!command.equals(ENDING_COMMAND)) {
            if (command.equals("list")) {
                listTasks();
            } else if (command.startsWith("done")) {
                String[] splitted = command.split(" ");
                done(Integer.parseInt(splitted[1]));
            } else {
                add(command);
            }
            command = prompt();
        }

        say("Bye. Hope to see you again soon!");
    }
}
