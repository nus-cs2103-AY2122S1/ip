import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static class TaskList {
        private String[] tasks;
        private int numTask;

        public TaskList(){
            this.tasks = new String[100];
            this.numTask = 0;
        }

        public void add(String task) {
            tasks[this.numTask] = task;
            this.numTask++;
            System.out.println("added: " + task);
        }

        public void list() {
            int counter = 0;
            for (int i = 0; i < this.numTask; i++) {
                System.out.println((i + 1)+ ". " + tasks[i]);
            }
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

            // add to list
            storage.add(input);
        }
    }
}
