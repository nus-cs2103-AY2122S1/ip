import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public ArrayList<Task> tasks = new ArrayList<>();

    public class Task {
        public String task;
        public Boolean done;

        public Task(String task, Boolean done) {
            this.task = task;
            this.done = done;
        }

        public Task(String task) {
            this.task = task;
            this.done = false;
        }

        public void setDone(Boolean done) {
            this.done = done;
        }

        public String checkBox() {
            if(this.done) {
                return "[X]";
            } else {
                return "[ ]";
            }
        }

        @Override
        public String toString() {
            return checkBox() + " " + task;
        }
    }

    public String byeString() {
        return "Bye. Hope to see you again soon!";
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("added: " + task);
    }

    public void doneTask(int taskNumber) {
        taskNumber--;
        Task task = tasks.get(taskNumber);
        task.setDone(true);
        tasks.set(taskNumber, task);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void taskListString() {
        StringBuilder ans = new StringBuilder();
        ans.append("Here are the tasks in your list:\n");
        for(int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if(i != tasks.size()-1) {
                ans.append(String.format("%d. %s\n", i + 1, task));
            } else {
                ans.append(String.format("%d. %s", i + 1, task));
            }
        }
        System.out.println(ans);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(input.equals("bye"))  {
            System.out.println(byeString());
        } else {
            interpretInput(input);
            run();
        }
    }

    public void interpretInput(String input) {
        if(input.equals("list")) {
            taskListString();
        } else if(input.startsWith("done")) {
            doneTask(Integer.parseInt(input.substring(5)));
        }
        else {
            addTask(new Task(input));
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        String hello = "Hello! I'm Duke\n" +
                "What can I do for you?";
        System.out.println(hello);
        duke.run();
    }
}
