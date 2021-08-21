import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public Task initialiseByType(String task, Type type, Boolean done, LocalDateTime localDateTime) {
        if(type == Type.TODO) {
            return new Todo(task, type, done);
        } else if(type == Type.EVENT) {
            return new Event(task, type, done, localDateTime);
        } else {
            return new Deadline(task, type, done, localDateTime);
        }
    }

    /**
     * Method to add a task to our list.
     * @param task The string of the task.
     * @param type The type of the task: event, deadline, or other type.
     */
    public void addTask(String task, Type type, LocalDateTime localDateTime) {
        Task taskObj = initialiseByType(task, type, false, localDateTime);
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
        LocalDateTime localDateTime;
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
            localDateTime = null;
            addTask(task, type, localDateTime);
        } else if (input.startsWith("deadline ")) {
            task = input.substring(9);
            type = Type.DEADLINE;
            String[] tokens = task.split(" /by ");
            localDateTime = parseDate(tokens[1]);
            task = tokens[0];
            addTask(task, type, localDateTime);
        } else if (input.startsWith("event ")) {
            task = input.substring(6);
            type = Type.EVENT;
            String[] tokens = task.split(" /at ");
            localDateTime = parseDate(tokens[1]);
            task = tokens[0];
            addTask(task, type, localDateTime);
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
            lines += task.fileWriteString();
            if(i != tasks.size()) {
                lines += "\n";
            }
            i++;
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
            if(tokens.length < 3) {
                System.out.println("damn");
                throw new FileParseErrorException();
            }

            Type type;
            String interpretedString = "";
            Boolean done = false;

            if(tokens[1].equals("0")) {
                done = false;
            } else if (tokens[1].equals("1")) {
                done = true;
            } else {
                throw new FileParseErrorException();
            }

            LocalDateTime localDateTime;
            if(tokens[0].equals("D") && tokens.length == 4) {
                type = Type.DEADLINE;
                interpretedString = tokens[2];
                localDateTime = parseDate(tokens[3]);
            } else if (tokens[0].equals("E") && tokens.length == 4) {
                type = Type.EVENT;
                interpretedString = tokens[2];
                localDateTime = parseDate(tokens[3]);
            } else if (tokens[0].equals("T") && tokens.length == 3) {
                type = Type.TODO;
                interpretedString = tokens[2];
                localDateTime = null;
            } else {
                throw new FileParseErrorException();
            }
            Task task = initialiseByType(interpretedString, type, done, localDateTime);
            this.tasks.add(task);
        }
    }

    public LocalDateTime parseDate (String datetime) {
        if(datetime.length() == 10) {
            datetime += " 00:00";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(datetime, formatter);
        return localDateTime;
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
