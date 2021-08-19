import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static class DukeException extends Exception {
        public DukeException(String errorMessage) {
            super(errorMessage);
        }
    }
    public static class TaskList {
        private Task[] tasks;
        private int numTask;

        public TaskList(){
            this.tasks = new Task[100];
            this.numTask = 0;
        }

        public void addCustom(Task task) {
            tasks[this.numTask] = task;
            this.numTask++;
            System.out.println("Got it. I've added this task: ");
            System.out.println("  " + task);
            System.out.printf("Now you have %d tasks in the list.\n", this.numTask);
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

            Matcher deadlineMatcher = deadlinePattern.matcher(input);
            if (deadlineMatcher.find()) {
                storage.addCustom(new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2)));
                continue;
            }

            Matcher eventMatcher = eventPattern.matcher(input);
            if (eventMatcher.find()) {
                storage.addCustom(new Event(eventMatcher.group(1), eventMatcher.group(2)));
                continue;
            }
        }
    }
}
