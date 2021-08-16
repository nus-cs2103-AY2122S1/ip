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

        /** The task itself, in String form */
        private String task;

        /** The done status of the task */
        private Boolean done;

        /** The type of the task, defined by the enum Type */
        private Type type;

        /**
         * Constructor to initialise the task
         * @param task The task itself.
         * @param type The type of the task defined by the enum Type.
         */
        public Task(String task, Type type) {
            this.task = task;
            this.done = false;
            this.type = type;
        }

        /**
         * Getter to see if the task is done.
         * @return Boolean about whether the task is done.
         */
        public Boolean isDone() {
            return this.done;
        }

        /**
         * Setter to set the task as done or not done.
         * @param done Boolean about whether the task is done.
         */
        public void setDone(Boolean done) {
            this.done = done;
        }

        /**
         * String representation of the done status of task
         * @return String representation of the done status of task.
         */
        public String checkBox() {
            if(this.isDone()) {
                return "[X]";
            } else {
                return "[ ]";
            }
        }

        /**
         * String representation of the type of task.
         * @return String representation of the type of task.
         */
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

        /** Initialising an empty array into which tasks can be added/manipulated/deleted */
        private ArrayList<Task> tasks = new ArrayList<>();

        /**
         * This method provides a string that is the visual representation of the tasks seen by the user.
         * @return The visual representation of task list.
         */
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

        /**
         * Method to add a task to our list.
         * @param task The string of the task.
         * @param type The type of the task: event, deadline, or other type.
         */
        public void addTask(String task, Type type) {
            Task taskObj = new Task(task, type);
            tasks.add(taskObj);
            System.out.println(String.format("Got it. I've added this task:\n" + taskObj.toString()));
            System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
        }

        /**
         * Method to check the task off as done in the list.
         * @param taskNumber The number of the task in our list.
         */
        public void doneTask(int taskNumber) {
            taskNumber--;
            Task task = tasks.get(taskNumber);
            task.setDone(true);
            tasks.set(taskNumber, task);
            System.out.println("Nice! I've marked this task as done:\n" + task.toString());
        }

        /**
         * Method to delete the task from the list.
         * @param taskNumber The number of the task in our list.
         */
        public void deleteTask(int taskNumber) {
            taskNumber--;
            Task task = tasks.get(taskNumber);
            tasks.remove(taskNumber);
            System.out.println("Noted. I've removed this task:\n" + task.toString());
            System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
        }

        /**
         * The string that is outputted when the user terminates the chatbot.
         * @return Message to say bye to user.
         */
        public String byeString() {
            return "Bye. Hope to see you again soon!";
        }

        /**
         * Assesses the input and activates the necessary response.
         * @param input The string of input command.
         * @throws DukeException Exceptions specific to this chatbot.
         */
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

        /**
         * Method call to activate the chatbot.
         * If command bye is given, the chatbot terminates.
         * @param scanner Scanner that takes in the input.
         */
        public void run(Scanner scanner) {
            if(scanner.hasNext()) {
                String input = scanner.nextLine();
                try {
                    interpretInput(input);
                } catch(DukeException dukeException) {
                    System.out.println(dukeException.getMessage());
                    run(scanner);
                }
                if(input.equals("bye"))  {
                    scanner.close();
                } else {
                    run(scanner);
                }
            }
        }
    }

    public static void main(String[] args) {
        TaskManager taskManager = new Duke().generateTaskManager();
        String hello = "Hello! I'm Duke\n" +
                "What can I do for you?";
        System.out.println(hello);
        Scanner scanner = new Scanner(System.in);
        taskManager.run(scanner);
    }
}
