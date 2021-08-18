import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
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
            if (input.equals("list")) {
                int counter = 1;
                StringBuilder sb = new StringBuilder();
                sb.append("这是您的菜单：\n");
                for (Task item:arrayList) {
                    sb.append(String.valueOf(counter) + ". " + item.toString() + "\n");
                    counter++;
                }
                printStatement(sb.toString());
            } else if (input.contains("done")) {
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
