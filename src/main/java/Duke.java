import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static class Task {
        private String description;
        private Boolean status;

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
                        System.out.println(i+1 + ". " + task.getStatusString() + " " + task.getDescription());
                    } else {
                        System.out.println(i+1 + ". " + task.getStatusString() + " " + task.getDescription());
                    }
                }
            } else if (input.contains("done")) {
                int idx = Integer.parseInt(input.split(" ")[1]);
                Task task = dataStore.get(idx-1);
                
                System.out.println("Nice! I've marked this task as done: ");
                task.setDone();
                System.out.println(task.getStatusString() + " " + task.getDescription());
            } else {
                Task task = new Task(input);
                dataStore.add(task);
                System.out.println("added: " + input);
            }
        }

    }
}