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

                case "todo":
                    String tdLabel = scanner.nextLine();
                    System.out.println("──────────────────────────────────────────");
                    Todo todo = new Todo(tdLabel);
                    list[pointer] = todo;
                    pointer++;
                    System.out.println("added: " + todo);
                    System.out.println("Now you have " + pointer + " tasks in the list");
                    System.out.println("──────────────────────────────────────────");
                    input = scanner.next();
                    break;

                case "deadline":
                    String dlLabel = scanner.nextLine();
                    System.out.println("──────────────────────────────────────────");
                    int dlSep = dlLabel.indexOf("by");
                    if (dlSep == -1) {
                        System.out.println("──────────────────────────────────────────");
                        System.out.println("No date/time provided!");
                        System.out.println("──────────────────────────────────────────");
                        input = scanner.next();
                        break;
                    }
                    String dl1 = dlLabel.substring(0, dlSep - 1);
                    String dl2 = dlLabel.substring(dlSep + 3);
                    Deadline deadline = new Deadline(dl1, dl2);
                    list[pointer] = deadline;
                    pointer++;
                    System.out.println("added: " + deadline);
                    System.out.println("Now you have " + pointer + " tasks in the list");
                    System.out.println("──────────────────────────────────────────");
                    input = scanner.next();
                    break;

                case "event":
                    String eLabel = scanner.nextLine();
                    System.out.println("──────────────────────────────────────────");
                    int eSep = eLabel.indexOf("at");
                    if (eSep == -1) {
                        System.out.println("──────────────────────────────────────────");
                        System.out.println("No date/time provided!");
                        System.out.println("──────────────────────────────────────────");
                        input = scanner.next();
                        break;
                    }
                    String e1 = eLabel.substring(0, eSep - 1);
                    String e2 = eLabel.substring(eSep + 3);
                    Event event = new Event(e1, e2);
                    list[pointer] = event;
                    pointer++;
                    System.out.println("added: " + event);
                    System.out.println("Now you have " + pointer + " tasks in the list");
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
                    System.out.println("Now you have " + pointer + " tasks in the list");
                    System.out.println("──────────────────────────────────────────");
                    input = scanner.next();
                    break;
            }
        }
        System.out.println("──────────────────────────────────────────");
        System.out.println("Bye, hope to see you again soon!");
    }

    private static class Task {
        protected boolean done = false;
        protected String name;

        public Task(String name) {
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

    private static class Todo extends Task {
        public Todo(String str) {
            super(str);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    private static class Deadline extends Task {
        protected String when;
        public Deadline(String str1, String str2) {
            super(str1);
            this.when = str2;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + when + ")";
        }
    }

    private static class Event extends Task {
        protected String when;
        public Event(String str1, String str2) {
            super(str1);
            this.when = str2;
        }
        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + when + ")";
        }
    }
}
