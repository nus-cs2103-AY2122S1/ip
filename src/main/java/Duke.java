import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static class DukeException extends Exception {
        public DukeException(String errorMessage) {
            super(errorMessage);
        }
    }
    public static class TaskList {
        private ArrayList<Task> tasks;
        private int numTask;

        public TaskList(){
            this.tasks = new ArrayList<>();
            this.numTask = 0;
        }

        public void addCustom(Task task) {
            tasks.add(task);
            this.numTask++;
            System.out.println("Got it. I've added this task: ");
            System.out.println("  " + task);
            System.out.printf("Now you have %d %s in the list.\n", this.numTask, this.numTask == 1 ? "task" : "tasks");
        }


        public void list() {
            int counter = 0;
            for (int i = 0; i < this.numTask; i++) {
                System.out.println((i + 1)+ ". " + tasks.get(i));
            }
        }

        public void done(int taskNumber) {
            tasks.get(taskNumber - 1).complete();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("  " + tasks.get(taskNumber - 1));
        }

        public void delete(int taskNumber) {
            Task task = tasks.get(taskNumber - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + task);
            tasks.remove(taskNumber - 1);
            this.numTask--;
            System.out.printf("Now you have %d %s in the list.\n", this.numTask, this.numTask == 1 ? "task" : "tasks");
        }
    }

    public void init() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void run() throws IOException, DukeException {
        Pattern todoPattern = Pattern.compile("todo (.*)");
        Pattern deadlinePattern = Pattern.compile("deadline (.*) /by (.*)");
        Pattern eventPattern = Pattern.compile("event (.*) /at (.*)");

        TaskList storage = new TaskList();
        init();

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

            if (Pattern.matches("delete \\d", input)) {
                String[] items = input.split(" ");
                storage.delete(Integer.parseInt(items[1]));
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

            // identify reason for misinput
            if (input.substring(0, 4).equals("todo")) {
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
                continue;
            }
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        try {
            duke.run();
        } catch (IOException e) {
            System.out.println("Error found while parsing input, shutting down");
        } catch (DukeException e) {

        }
    }
}
