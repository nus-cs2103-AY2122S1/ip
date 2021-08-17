import java.util.Scanner;

public class Duke {
    private static abstract class Task {
        private String task;
        private boolean isDone;

        private String isDone() {
            return (this.isDone ? "X" : " ");
        }

        private void markAsDone() {
            this.isDone = true;
        }

        @Override
        public String toString() {
            String str = String.format("[%s] %s", this.isDone(), this.task);
            return str;
        }
    }

    private static class Todo extends Task{
        public Todo(String task) {
            super.task = task;
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    private static class Deadline extends Task {
        private String by;

        public Deadline(String task, String by) {
            super.task = task;
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    private static class Event extends Task {
        private String at;

        public Event(String task, String at) {
            super.task = task;
            this.at = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }

    private static class ToDoList {
        private Task[] list;
        private int index;
        private boolean open;

        public ToDoList() {
            this.list = new Task[100];
            this.index = 0;
        }

        public void open() {
            this.open = true;
            System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        }

        public void exit() {
            this.open = false;
            System.out.println("Bye. Hope to see you again soon!");
        }

        public boolean isOpen() {
            return this.open;
        }

        public void add(Task task) {
            list[index] = task;
            index++;
            System.out.println("Got it. I've added this task:\n" + "  " + task + "\n" +
                    "Now you have " + index + " tasks in the list.");
        }

        public void showList() {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < index; i++) {
                String s = String.format("%d.%s", i + 1, list[i]);
                System.out.println(s);
            }
        }

        public void markAsDone(int index) {
            list[index - 1].markAsDone();
            System.out.println("Nice! I've marked this task as done:\n  " + list[index - 1]);
        }
    }
    public static void main(String[] args) {
        ToDoList tdl = new ToDoList();
        Scanner sc = new Scanner(System.in);
        tdl.open();
        while (tdl.isOpen()) {
            String input = sc.nextLine();
            String[] inputs = input.split(" ");
            String action = inputs[0];
            switch (action) {
                case "bye":
                    tdl.exit();
                    break;
                case "list":
                    tdl.showList();
                    break;
                case "done":
                    int index = Integer.parseInt(inputs[1]);
                    tdl.markAsDone(index);
                    break;
                case "todo":
                    int tFirst = input.indexOf(" ");
                    String tTask = input.substring(tFirst + 1);
                    Todo t = new Todo(tTask);
                    tdl.add(t);
                    break;
                case "deadline":
                    int dFirst = input.indexOf(" ");
                    int dSecond = input.indexOf("/");
                    String dTask = input.substring(dFirst + 1, dSecond - 1);
                    String by = input.substring(dSecond + 4);
                    Deadline d = new Deadline(dTask, by);
                    tdl.add(d);
                    break;
                case "event":
                    int eFirst = input.indexOf(" ");
                    int eSecond = input.indexOf("/");
                    String eTask = input.substring(eFirst + 1, eSecond - 1);
                    String at = input.substring(eSecond + 4);
                    Event e = new Event(eTask, at);
                    tdl.add(e);
                    break;
                default:
                    System.out.println("Sorry, I do not understand what was written\nPlease try again.");
                    break;
            }
        }
        sc.close();
    }
}
