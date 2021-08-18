import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public void taskNumberAnnounce(int number) {
        System.out.println("你目前点了" + String.valueOf(number) + "样菜");
    }
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

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[" + getStatusIcon() + "]" + " " + description);
            return sb.toString();
        }

        public void markAsDone() {
            this.isDone = true;
        }
    }

    public static class Deadline extends Task {

        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public static class Event extends Task {

        protected String at;

        public Event(String description, String at) {
            super(description);
            this.at = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }

    public static class Todo extends Task {

        protected String by;

        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    private static void printLine() {
//        printPadding();
        for (int i = 0; i < 20; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static void printPadding() {
        for (int i = 0; i < 5; i++) {
            System.out.print(" ");
        }
    }

    private static void printStatement(String statement) {
        System.out.println();
        printLine();
//        printPadding();
        System.out.println(statement);
        System.out.println();
        printLine();
        System.out.println();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printStatement("你好! 我是杜克\n能为您做什么吗？\n");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ArrayList<Task> arrayList = new ArrayList<>(100);
        while (!input.equals("bye")) {
            String firstWord = input.split(" ")[0];
            if (input.equals("list")) {
                int counter = 1;
                StringBuilder sb = new StringBuilder();
                sb.append("这是您的菜单：\n");
                for (Task item:arrayList) {
                    sb.append(String.valueOf(counter) + ". " + item.toString() + "\n");
                    counter++;
                }
                printStatement(sb.toString());
            } else if (firstWord.equals("event") || firstWord.equals("deadline") || firstWord.equals("todo")) {
                StringBuilder sb = new StringBuilder();
                sb.append("Got it. I've added this task: \n");

                printStatement("");

                if (firstWord.equals("todo")) {
                    Todo newTodo = new Todo(input.substring(5));
                    arrayList.add(newTodo);
                    sb.append(newTodo + "\n");
                } else if (firstWord.equals("deadline")) {
                    String[] time = input.substring(9).split("/by");
                    Deadline newDeadline = new Deadline(time[0], time[1]);
                    arrayList.add(newDeadline);
                    sb.append(newDeadline + "\n");
                } else {//if (firstWord.equals("event")) {
                    String[] time = input.substring(9).split("/at");
                    Event newEvent = new Event(time[0], time[1]);
                    arrayList.add(newEvent);
                    sb.append(newEvent + "\n");
                }
                sb.append("Now you have " + String.valueOf(arrayList.size()) + " tasks in da list.\n");
                printStatement(sb.toString());
            }else if (input.substring(0, 4).equals("done")) {
                int number = Integer.valueOf(input.split(" ")[1]) - 1;
                Task task = arrayList.get(number);
                task.markAsDone();
                printStatement("Nice! 我帮你记下了：\n" + task);
            } else {
                Task newTask = new Task(input);
                arrayList.add(newTask);
                printStatement("added: " + input);
            }
            input = sc.nextLine();
        }
        printStatement("再见，请再光临！");



    }
}
