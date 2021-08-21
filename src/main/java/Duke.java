import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Duke {

    public TaskManager generateTaskManager() {
        return new TaskManager();
    }

    /**
     * Enum class for types of events.
     */
    enum Type {
        TODO,
        DEADLINE,
        EVENT;

        public static String typeString(Type type) {
            if(type == Type.EVENT) {
                return "E";
            } else if(type == Type.TODO) {
                return "T";
            } else if(type == Type.DEADLINE) {
                return "D";
            } else {
                throw new NullPointerException();
            }
        }

        public static String getConnector(Type type) {
            if(type == Type.EVENT) {
                return "at";
            } else if(type == Type.TODO) {
                return "";
            } else if(type == Type.DEADLINE) {
                return "by";
            } else {
                throw new NullPointerException();
            }
        }
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

        public Task() {
            this.task = "";
            this.done = false;
            this.type = Type.TODO;

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

        public void setTask(String task) {
            this.task = task;
        }

        public void setType(Type type) {
            this.type = type;
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

        public Type getType() {
            return this.type;
        }

        public String getTask() {
            return task;
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

        public String taskString() {
            if(this.type == Type.TODO) {
                return this.task;
            } else {
                String[] tokens = task.split(",");
                return String.format("%s (%s: %s)", tokens[0], Type.getConnector(this.type), tokens[1]);
            }
        }

        @Override
        public String toString() {
            return String.format("%s%s %s", typeString(), checkBox(), taskString());
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
                task = task.replace(" /by ", ",");
                addTask(task, type);
            } else if (input.startsWith("event ")) {
                task = input.substring(6);
                type = Type.EVENT;
                task = task.replace(" /at ", ",");
                addTask(task, type);
            } else if (input.startsWith("delete ")) {
                deleteTask(Integer.parseInt(input.substring(7)));
            } else {
                throw new CommandNotFoundException();
            }
            try {
                writeToFile();
            } catch(IOException err) {
                System.out.println(err);
            }

        }

        public void writeToFile() throws IOException {
            List<Task> tasks = this.tasks;
            int i = 1;
            String lines = "";

            for(Task task: tasks) {
                String line = "";
                line = line + Type.typeString(task.getType()) + ",";
                line = task.isDone() ? line + String.format("%d,", 1) : line + String.format("%d,", 0);
                String taskString = task.getTask();
                line += taskString;
                if(i != tasks.size()) {
                    line += "\n";
                }
                i++;
                lines += line;
            }
            FileWriter fileWriter = new FileWriter("data/duke.txt");
            fileWriter.write(lines);
            fileWriter.close();
        }

        public List<String> parseFile() {
            File file = new File("data/duke.txt");
            List<String> tasks = new ArrayList();
            try {
                Scanner readFile = new Scanner(file);
                while(readFile.hasNext()) {
                    String task = readFile.nextLine();
                    tasks.add(task);
                }
            } catch(Exception err) {
                System.out.println(err);
            }
            return tasks;
        }

        public void loadTasks(List<String> tasks) throws FileParseErrorException {
            this.tasks = new ArrayList<>();
            for(int i = 0; i<tasks.size();i++) {
                String taskString = tasks.get(i);
                String[] tokens = taskString.split(",");
                Task task = new Task();
                if(tokens.length < 3) {
                    throw new FileParseErrorException();
                }

                Type type;
                String interpretedString = "";
                int done = 0;

                if(tokens[1].equals("0")) {
                    done = 0;
                } else if (tokens[1].equals("1")) {
                    done = 1;
                    task.setDone(true);
                } else {
                    throw new FileParseErrorException();
                }
                if(tokens[0].equals("D") && tokens.length == 4) {
                    type = Type.DEADLINE;
                    interpretedString = tokens[2] + "," + tokens[3];
                } else if (tokens[0].equals("E") && tokens.length == 4) {
                    type = Type.EVENT;
                    interpretedString = tokens[2] + "," + tokens[3];
                } else if (tokens[0].equals("T") && tokens.length == 3) {
                    type = Type.TODO;
                    interpretedString = tokens[2];
                } else {
                    throw new FileParseErrorException();
                }
                task.setType(type);
                task.setTask(interpretedString);
                this.tasks.add(task);
            }
        }

        /**
         * Method call to activate the chatbot.
         * If command bye is given, the chatbot terminates.
         * @param scanner Scanner that takes in the input.
         */
        public void run(Scanner scanner) {
            List<String> tasks = parseFile();

            try {
                loadTasks(tasks);
            } catch (FileParseErrorException err) {
                System.out.println(err);
            }

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
