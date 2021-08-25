import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.util.stream.Collectors;

public class Duke {
    private static ArrayList<Task> todoList = new ArrayList<>();

    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
    
    private static final java.nio.file.Path dataPath = java.nio.file.Paths.get("data", "dukeData.txt"); // ~/data/dukeData.txt

    public static void main(String[] args) {
        try {
            initDatabase();
            loadDatabaseToList();
        } catch (DukeException e) {
            System.out.println(e.toString());
            return;
        }

        String separator = "------------------------------------------------------------------";

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello I'm Duke :)");
        System.out.println("What can I do for you?");
        System.out.println(separator);
        
        String endCmd = "bye";
        String listCmd = "list";
        String doneCmd = "done";
        String todoCmd = "todo";
        String deadlineCmd = "deadline";
        String eventCmd = "event";
        String deleteCmd = "delete";

        Scanner sc = new Scanner(System.in);
        boolean end = false;
        while (!end) {
            try {
                String input = sc.nextLine();
                String[] inputs = input.split(" ", 2);
                String cmd = inputs[0]; // get first word as command
                String description = inputs.length > 1 ? inputs[1] : "";
                System.out.println(separator);

                if (cmd.equals(endCmd)) {
                    System.out.println("Bye bye! See you again soon!");
                    end = true;
                } else if (cmd.equals(listCmd)) {
                    displayList();
                } else if (cmd.equals(doneCmd)) {
                    Integer taskNum = validateTaskNumber(description);
                    markTaskDone(taskNum);
                } else if (cmd.equals(todoCmd)) {
                    addTask(TaskType.TODO, description);
                } else if (cmd.equals(deadlineCmd)) {
                    addTask(TaskType.DEADLINE, description);
                } else if (cmd.equals(eventCmd)) {
                    addTask(TaskType.EVENT, description);
                } else if (cmd.equals(deleteCmd)) {
                    Integer taskNum = validateTaskNumber(description);
                    deleteTask(taskNum);
                } else {
                    throw new DukeException("Sorry, I don't know what that means.");
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            } finally {
                System.out.println(separator);
            }
        }
    }
    
    /** Creates a data file */
    private static void initDatabase() throws DukeException {
        try {
            Files.createDirectories(dataPath.getParent());
            if (!java.nio.file.Files.exists(dataPath)) {
                java.nio.file.Files.createFile(dataPath);
            }
        } catch (IOException e) {
            throw new DukeException("Could not initialise database. " + e.getMessage());
        }
    }

    /** Writes over data file to save current todoList contents */
    private static void writeListToDatabase() throws DukeException {
        try {
            // task saved as e.g. E|0|meeting|2pm
            List<String> dataStrings = todoList.stream().map(task -> task.toDataString("|")).collect(Collectors.toList());
            Files.write(dataPath, dataStrings);
        } catch (IOException e) {
            throw new DukeException("Could not save list to database. " + e.getMessage());
        }
    }

    /** Loads database into todoList */
    private static void loadDatabaseToList() throws DukeException {
        try {
            List<String> dataList = Files.lines(dataPath).collect(Collectors.toList());
            for (String line : dataList) {
                String[] details = line.split("\\|",4);
                String tag = details[0];
                Boolean done = details[1].equals("1"); 
                Task task;
                if (tag.equals("T")) {
                    task = new ToDo(details[2]);
                } else if (tag.equals("D")) {
                    task = new Deadline(details[2], details[3]);
                } else if (tag.equals("E")) {
                    task = new Event(details[2], details[3]);
                } else {
                    throw new DukeException("Unknown tag '" + tag + "'.");
                }
                if (done) {
                    task.markAsDone();
                }
                todoList.add(task);
            }
        } catch (IOException | DukeException e) {
            throw new DukeException("Could not load database into todo list. " + e.getMessage());
        }
    }


    /** Prints the to-do list in order */
    private static void displayList() {
        System.out.println("Your task list:");
        for (int i = 0; i < todoList.size(); i++) {
            Task task = todoList.get(i);
            int num = i+1;
            System.out.println(num + "." + task.toString());
        }
    }

    /** Add a task to the to-do list */
    private static void addTask(TaskType taskType, String details) throws DukeException {
        Task task;
        if (taskType.equals(TaskType.TODO)) {
            task = new ToDo(details);
        } else if (taskType.equals(TaskType.DEADLINE)) {
            int position = details.indexOf("/by");
            String description, by;
            if (position >= 0) {
                description = details.substring(0, position);
                by = details.substring(position + 3);
            } else {
                throw new DukeException("Please indicate the deadline eg \"/by Sunday\" ");
            }
            task = new Deadline(description.trim(), by.trim());
        } else if (taskType.equals(TaskType.EVENT)) {
            int position = details.indexOf("/at");
            String description, at;
            if (position >= 0) {
                description = details.substring(0, position);
                at = details.substring(position + 3);
            } else {
                throw new DukeException("Please indicate the event time eg \"/at Mon 2-4pm\" ");
            }
            task = new Event(description.trim(), at.trim());
        } else {
            // should not reach here
            return;
        }
        todoList.add(task);
        System.out.println("I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + todoList.size() + " tasks in the list.");
        
        writeListToDatabase();
    }

    /** Mark a task with given task number as done */
    private static void markTaskDone(Integer taskNum) throws DukeException {
        Task task = todoList.get(taskNum - 1);
        task.markAsDone();
        System.out.println("Good work! I've marked this task as done:");
        System.out.println(task.toString());

        writeListToDatabase();
    }

    /** Delete a task with given task number */
    private static void deleteTask(Integer taskNum) throws DukeException {
        Task task = todoList.remove(taskNum - 1);
        System.out.println("Ok, I've deleted this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + todoList.size() + " tasks in the list.");

        writeListToDatabase();
    }

    /** checks if input is a valid task number and returns task number if valid */
    private static Integer validateTaskNumber(String input) throws DukeException {
        Integer taskNum;
        try {
            taskNum = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DukeException("You did not specify the task number.");
        }
        int listLength = todoList.size();
        int taskIndex = taskNum - 1;
        if (listLength == 0) {
            throw new DukeException("The operation cannot be performed as you have 0 tasks in your list.");
        }
        if (taskIndex < 0 || taskIndex >= todoList.size()) {
            throw new DukeException("The task number must be from 1 to " + listLength + ".");
        }
        return taskNum;
    }
}
