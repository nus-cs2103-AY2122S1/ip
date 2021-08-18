import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static class Task {
        protected String description;
        protected Boolean status;

        public Task(String desc) {
            this.description = desc;
            this.status = false;
        }

        public void setDone() {
            // sets the task as completed
            this.status = true;
        }

        public Boolean getStatus() {
            return this.status;
        }

        public String getStatusString() {
            return this.status ? "[X]" : "[ ]";
        }

        public String getDescription() {
            return this.description;
        }

        public Boolean equals(Task t) {
            if (t.getDescription().equals(this.description)) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return this.getStatusString() + " " + this.getDescription();
        }
    }

    static class ToDo extends Task {
        public ToDo(String desc) {
            super(desc);
        }
        
        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    static class Deadline extends Task {
        protected String deadline;

        public Deadline(String desc, String deadline) {
            super(desc);
            this.deadline = deadline;
        }

        @Override
        public String toString() {
            return "[D]" 
                    + super.toString()
                    + " (by: "
                    + this.deadline
                    + ")";
        }
    }

    static class Event extends Task {
        protected String timing;

        public Event(String desc, String timing) {
            super(desc);
            this.timing = timing;
        }

        @Override
        public String toString() {
            return "[E]" 
                    + super.toString()
                    + " (at: "
                    + this.timing
                    + ")";
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        ArrayList<Task> dataStore = new ArrayList<Task>();

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("");
            String input = sc.nextLine();
    
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < dataStore.size(); i++) {
                    Task task = dataStore.get(i);

                    if (task.getStatus()) {
                        System.out.println(i+1 + ". " + task.toString());
                    } else {
                        System.out.println(i+1 + ". " + task.toString());
                    }
                }
            } else if (input.contains("todo")) {
                ToDo todo = new ToDo(input);
                dataStore.add(todo);

                System.out.println("Got it. I've added this task: ");
                System.out.println("    " + todo.toString());
                System.out.println("Now you have " + dataStore.size() + " tasks in the list.");
            } else if (input.contains("deadline")) {
                String query = input.split("/by")[0];
                String limit = input.split("/by")[1];
                Deadline deadlineTask = new Deadline(query, limit);
                dataStore.add(deadlineTask);

                System.out.println("Got it. I've added this task: ");
                System.out.println("    " + deadlineTask.toString());
                System.out.println("Now you have " + dataStore.size() + " tasks in the list.");
            } else if (input.contains("event")) {
                String query = input.split("/at")[0];
                String datetime = input.split("/at")[1];
                Event eventTask = new Event(query, datetime);
                dataStore.add(eventTask);

                System.out.println("Got it. I've added this task: ");
                System.out.println("    " + eventTask.toString());
                System.out.println("Now you have " + dataStore.size() + " tasks in the list.");
            } else if (input.contains("done")) {
                int idx = Integer.parseInt(input.split(" ")[1]);
                Task task = dataStore.get(idx-1);
                
                System.out.println("Nice! I've marked this task as done: ");
                task.setDone();
                System.out.println("    " + task.toString());
            } else {
                Task task = new Task(input);
                dataStore.add(task);
                System.out.println("added: " + input);
            }
        }

    }
}