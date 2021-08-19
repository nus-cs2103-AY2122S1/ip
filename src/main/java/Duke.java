import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

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
            return String.format("[%s] %s", this.getStatusIcon(), this.description);
        }

        public void complete() {
            this.isDone = true;
        }
    }

    public static class TaskList {
        private Task[] tasks;
        private int numTask;

        public TaskList(){
            this.tasks = new Task[100];
            this.numTask = 0;
        }

        public void add(String task) {
            tasks[this.numTask] = new Task(task);
            this.numTask++;
            System.out.println("added: " + task);
        }

        public void list() {
            int counter = 0;
            for (int i = 0; i < this.numTask; i++) {
                System.out.println((i + 1)+ ". " + tasks[i]);
            }
        }

        public void done(int taskNumber) {
            tasks[taskNumber - 1].complete();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("  " + tasks[taskNumber - 1]);
        }
    }

    public static void main(String[] args) throws IOException {

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        TaskList storage = new TaskList();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (true) {
            // Enter data using BufferReader
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            // Reading data using readLine
            String input = reader.readLine();

            // exit application
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            // print out list
            if (input.equals("list")) {
                storage.list();
                continue;
            }

            // complete a task
            if (Pattern.matches("done \\d", input)) {
                String[] items = input.split(" ");
                storage.done(Integer.parseInt(items[1]));
                continue;
            }

            // add to list
            storage.add(input);
        }
    }
}
