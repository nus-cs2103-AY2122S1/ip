import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static class Task {
        private final String name;
        private boolean completed;

        public Task(String name) {
            this.name = name;
            completed = false;
        }

        public String getName() {
            return this.name;
        }
        public String getCompleted() {
            return (completed ? "X" : " ");
        }

        private void toggleCompleted() {
            this.completed = !this.completed;
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        boolean loop = true;
        Scanner textInput = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        while (loop) {
            String input = textInput.nextLine();
            String[] inputSplit = input.split(" ");

            switch(inputSplit[0]) {
                case "bye":
                    loop = false;
                    break;
                case "list":
                    for (int i = 1; i <= list.size(); i++) {
                        Task task = list.get(i - 1);
                        System.out.println(i + "."
                                + "[" + task.getCompleted() + "] "
                                + task.getName());
                    }
                    break;
                case "done":
                    int index = Integer.parseInt(inputSplit[1]);
                    Task task = list.get(index - 1);
                    task.toggleCompleted();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("[X] " + task.getName());
                    break;
                default:
                    list.add(new Task(input));
                    System.out.println("added: " + input);
            }
        }
        System.out.println("Bye bye. Duke going to sleep now.");
    }
}
