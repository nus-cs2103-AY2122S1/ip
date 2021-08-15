import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public TaskManager generateTaskManager() {
        return new TaskManager();
    }

    enum Type {
        TODO,
        DEADLINE,
        EVENT
    }

    public class Task {

        public String task;
        public Boolean done;
        public Type type;

        public Task(String task) {
            this.task = task;
            this.done = false;
            this.type = Type.TODO;
        }

        public Task(String task, Type type) {
            this.task = task;
            this.done = false;
            this.type = type;
        }

        public String getTask() {
            return this.task;
        }

        public Boolean isDone() {
            return this.done;
        }

        public void setDone(Boolean done) {
            this.done = done;
        }

        public String checkBox() {
            if(this.isDone()) {
                return "[X]";
            } else {
                return "[ ]";
            }
        }

        public String typeString() {
            if(this.type == Type.TODO) {
                return "[T]";
            } else if (this.type == Type.DEADLINE) {
                return "[D]";
            } else {
                return "[E]";
            }
        }

        @Override
        public String toString() {
            return String.format("%s%s %s", typeString(), checkBox(), this.task);
        }
    }

    public class TaskManager {

        public ArrayList<Task> tasks = new ArrayList<>();

        public TaskManager() {
            // empty constructor for now
        }

        public String taskListString() {
            StringBuilder ans = new StringBuilder();
            ans.append("Here are the tasks in your list:\n");
            for(int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                if(i != tasks.size()-1) {
                    ans.append(String.format("%d. %s\n", i + 1, task.toString()));
                } else {
                    ans.append(String.format("%d. %s", i + 1, task.toString()));
                }
            }
            return ans.toString();
        }

        public void addTask(String task, Type type) {
            Task taskObj = new Task(task, type);
            tasks.add(taskObj);
            System.out.println(String.format("Got it. I've added this task:\n" + taskObj.toString()));
            System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
        }

        public void doneTask(int taskNumber) {
            taskNumber--;
            Task task = tasks.get(taskNumber);
            task.setDone(true);
            tasks.set(taskNumber, task);
            System.out.println("Nice! I've marked this task as done:\n" + task.toString());
        }

        public void deleteTask(int taskNumber) {
            taskNumber--;
            Task task = tasks.get(taskNumber);
            tasks.remove(taskNumber);
            System.out.println("Noted. I've removed this task:\n" + task.toString());
            System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
        }

        public String byeString() {
            return "Bye. Hope to see you again soon!";
        }

        public void interpretInput(String input) throws DukeException{
            String task;
            Type type;
            if(input.equals("bye")) {
                System.out.println(byeString());
            } else if(input.equals("list")) {
                System.out.println(taskListString());
            } else if(input.equals("hello")) {
                System.out.println("Hello! I'm Duke\n" +
                        "What can I do for you?");
            } else if (input.startsWith("done ")) {
                doneTask(Integer.parseInt(input.substring(5)));
            } else if(input.startsWith("todo ")) {
                // Remove all whitespaces to test if it is empty
                String testInput = input.replaceAll("\\s+","");
                if(testInput.equals("todo")) {
                    throw new EmptyTodoException();
                }
                task = input.substring(5);
                type = Type.TODO;
                addTask(task, type);
            } else if (input.startsWith("deadline ")) {
                task = input.substring(9);
                type = Type.DEADLINE;
                task = task.replace("/by", "(by:");
                task += ")";
                addTask(task, type);
            } else if (input.startsWith("event ")) {
                task = input.substring(6);
                type = Type.EVENT;
                task = task.replace("/at", "(at:");
                task += ")";
                addTask(task, type);
            } else if (input.startsWith("delete ")) {
                deleteTask(Integer.parseInt(input.substring(7)));
            } else {
                throw new CommandNotFoundException();
            }
        }

        public void run() {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            try {
                interpretInput(input);
            } catch(DukeException dukeException) {
                System.out.println(dukeException.getMessage());
                run();
            }
            if(input.equals("bye"))  {
                return;
            } else {
                run();
            }
        }
    }

    public static void main(String[] args) {
        TaskManager taskManager = new Duke().generateTaskManager();
        String hello = "Hello! I'm Duke\n" +
                "What can I do for you?";
        System.out.println(hello);
        taskManager.run();
    }
}
