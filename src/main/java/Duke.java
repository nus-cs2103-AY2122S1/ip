import java.util.Scanner;

public class Duke {
    private static int listSize = 0;
    private static Task[] list = new Task[100]; // List to store all the tasks

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void complete() {
            this.isDone = true;
        }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + description;
        }
    }

    public static void handleInput(String input) {
        if (input.equals("list")) {
            for (int i = 0; i < listSize; i++) {
                System.out.println((i + 1) + "." + list[i]);
            }
        } else if (input.startsWith("done ")) {
            try {
                completeTask(input);
            } catch (NumberFormatException e) {
                addTask(input);
            }
        } else {
            addTask(input);
        }
    }

    public static void addTask(String desc) {
        list[listSize] = new Task(desc);
        listSize++;
        System.out.println("added: " + desc);
    }

    public static void completeTask(String input) {
        int completedTaskNumber = Integer.parseInt(input.substring(5)) - 1;
        list[completedTaskNumber].complete();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list[completedTaskNumber]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");



        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                // Exit loop and thus program when input is bye
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            handleInput(input);
        }
    }
}
