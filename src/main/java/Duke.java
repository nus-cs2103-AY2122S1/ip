import javax.swing.*;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];
        int pointer = 0;

        System.out.println("──────────────────────────────────────────");
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can i do for you?");
        System.out.println("──────────────────────────────────────────");

        String input = scanner.next();

        while (!Objects.equals(input, "bye")) {
            switch(input) {
                case "list":
                    System.out.println("──────────────────────────────────────────");
                    for (int i = 0; i < pointer; i++) {
                        String cur = list[i].toString();
                        int label = i + 1;
                        System.out.println(label + ". " + cur);
                    }
                    System.out.println("──────────────────────────────────────────");
                    input = scanner.next();
                    break;

                case "done":
                    int temp = scanner.nextInt();
                    if (temp < 1) {
                        System.out.println("──────────────────────────────────────────");
                        System.out.println("invalid value!");
                        System.out.println("──────────────────────────────────────────");
                        input = scanner.next();
                        break;
                    }
                    Task cur = list[temp - 1];
                    cur.Done();
                    System.out.println("──────────────────────────────────────────");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(cur);
                    System.out.println("──────────────────────────────────────────");
                    input = scanner.next();
                    break;

                default:
                    String label = input + scanner.nextLine();
                    System.out.println("──────────────────────────────────────────");
                    Task task = new Task(label);
                    list[pointer] = task;
                    pointer++;
                    System.out.println("added: " + task);
                    System.out.println("──────────────────────────────────────────");
                    input = scanner.next();
                    break;
            }
        }
        System.out.println("──────────────────────────────────────────");
        System.out.println("Bye, hope to see you again soon!");
    }

    private static class Task {
        private boolean done = false;
        private String name;

        Task(String name) {
            this.name = name;
        }

        public void Done() {
            this.done = true;
        }

        @Override
        public String toString() {
            if (done) {
                return "[X] " + name;
            } else {
                return "[ ] " + name;
            }
        }
    }
}
