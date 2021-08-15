import java.util.Scanner;

public class Duke {
    private static class Task {
        private String task;
        private boolean isDone;

        public Task(String task) {
            this.task = task;
            this.isDone = false;
        }

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
            System.out.println("Hello! I'm Duke \nWhat can I do for you?");
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
            System.out.println("added: " + task);
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
            System.out.println(String.format("Nice! I've marked this task as done:\n  %s",
                    list[index - 1]));
        }
    }
    public static void main(String[] args) {
        ToDoList tdl = new ToDoList();
        Scanner sc = new Scanner(System.in);
        tdl.open();
        while (tdl.isOpen()) {
            String str = sc.next();
            switch (str) {
                case "bye":
                    tdl.exit();
                    break;
                case "list":
                    tdl.showList();
                    break;
                case "done":
                    int index = sc.nextInt();
                    tdl.markAsDone(index);
                    break;
                default:
                    str += sc.nextLine();
                    Task t = new Task(str);
                    tdl.add(t);
                    break;
            }
        }
        sc.close();
    }
}
