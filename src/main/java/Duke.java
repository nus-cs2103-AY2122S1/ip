import org.w3c.dom.events.Event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static class TaskList {
        private Task[] tasks;
        private int numTask;



        public TaskList(){
            this.tasks = new Task[100];
            this.numTask = 0;
        }

        public void add(Task task) {
            tasks[this.numTask] = task;
            this.numTask++;
            System.out.println("added: " + task.description);
        }

        public void addCustom(Task task) {
            tasks[this.numTask] = task;
            this.numTask++;
            System.out.println("Got it. I've added this task: ");
            System.out.println("  " + task);
            System.out.printf("Now you have %d tasks in the list.\n", this.numTask);
        }
//
//        public void addDeadline(String task) {
//            tasks[this.numTask] = new Deadline(task);
//            this.numTask++;
//            System.out.println("Got it. I've added this task: ");
//            System.out.println("  " + task);
//            System.out.printf("Now you have %d tasks in the list.", this.numTask);
//        }
//
//        public void addEvent(String task) {
//            tasks[this.numTask] = new Todo(task);
//            this.numTask++;
//            System.out.println("Got it. I've added this task: ");
//            System.out.println("  " + task);
//            System.out.printf("Now you have %d tasks in the list.", this.numTask);
//        }


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
        Pattern todoPattern = Pattern.compile("todo (.*)");
        Pattern deadlinePattern = Pattern.compile("deadline (.*) /by (.*)");
        Pattern eventPattern = Pattern.compile("event (.*) /at (.*)");

        TaskList storage = new TaskList();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

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

            Matcher todoMatcher = todoPattern.matcher(input);
            if (todoMatcher.find()) {
                storage.addCustom(new Todo(todoMatcher.group(1)));
                continue;
            }

            // add to list
            storage.add(new Task(input));
        }
    }
}
