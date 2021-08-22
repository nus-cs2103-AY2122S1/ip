import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String ENDING_COMMAND = "bye";

    private static final List<Task> taskList = new ArrayList<>();
    private static final String taskFilePath = "src/data.txt";

    private static void say(String message) {
        System.out.printf("Iris: %s%n", message);
    }

    private static void say(String message, boolean isFirst) {
        String name = isFirst ? "Iris:": "     ";
        System.out.printf("%s %s%n", name, message);
    }

    private static void sayError(IrisException exception) {
        say(exception.getMessage());
    }

    private static String prompt() {
        System.out.print("me: ");
        return scanner.nextLine();
    }

    private static void echo(String message) {
        say(message);
    }

    private static void sayTaskAdded() {
        int count = taskList.size();
        say("Got it. I've added this task:");
        say(taskList.get(count - 1).toString(), false);
        say(String.format("Now you have %d %s in the list.",
                count, count == 1 ? "task" : "tasks"), false);
    }

    private static void addTodo(String item) {
        taskList.add(new ToDo(item));
    }

    private static void addDeadline(String item, String by) throws IrisException {
        taskList.add(new Deadline(item, by));
    }

    private static void addEvent(String item, String at) throws IrisException {
        taskList.add(new Event(item, at));
    }

    private static void createTaskFile() {
        try {
            File taskFile = new File(taskFilePath);
            taskFile.createNewFile();
        } catch (IOException exception) {
            // TODO: handle IOException?
        }
    }

    private static void readFromFile() {
        File taskFile = new File(taskFilePath);
        try {
            Scanner scanner = new Scanner(taskFile);
            while (scanner.hasNextLine()) {
                handleCommand(scanner.nextLine(), true);
            }
        } catch (FileNotFoundException exception) {
            createTaskFile();
        } catch (IrisException exception) {
            say("data.txt has been corrupted");
        }
    }

    private static void appendToFile(String command) {
        try {
            FileWriter fw = new FileWriter(taskFilePath, true);
            fw.write(command);
            fw.close();
        } catch (IOException exception) {
            // TODO: handle IOException?
        }
    }

    private static void validateTaskIndex(int index) throws IrisException {
        if (index <= 0) throw new IrisException("Please enter a valid task index.");
        int count = taskList.size();
        if (index > count) throw new IrisException(String.format("Your task list only has %d items", count));
    }

    private static Task done(int index) throws IrisException {
        validateTaskIndex(index);
        Task task = taskList.get(index - 1);
        task.markComplete();
        return task;
    }

    private static Task delete(int index) throws IrisException {
        validateTaskIndex(index);
        Task task = taskList.get(index - 1);
        taskList.remove(index - 1);
        return task;
    }

    private static void listTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            say(String.format("%d. %s", i + 1, taskList.get(i)), i == 0);
        }
    }

    private static String getMetadata(String command) throws IrisException {
        String[] splitted = command.split(" ", 2);
        if (splitted.length == 1 || splitted[1].equals("")) {
            // TODO: make this error message more specific?
            throw new IrisException("The description cannot be empty");
        } else {
            return splitted[1];
        }
    }

    private static int parseInt(String text) throws IrisException {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException exception) {
            throw new IrisException("Please enter a valid integer");
        }
    }

    private static void handleCommand(String command) throws IrisException {
        handleCommand(command, false);
    }

    private static void handleCommand(String command, boolean loadFromFile) throws IrisException {
        boolean record = true;
        if (command.equals("list")) {
            listTasks();
            record = false;
        } else if (command.startsWith("done")) {
            Task task = done(parseInt(getMetadata(command)));
            if (!loadFromFile) say(String.format("Good job! I've marked this task as done: %s", task));
        } else if (command.startsWith("delete")) {
            Task task = delete(parseInt(getMetadata(command)));
            if (!loadFromFile) {
                say("Noted. I've removed this task:");
                say(task.toString(), false);
                int count = taskList.size();
                say(String.format("Now you have %d %s in the list.",
                        count, count == 1 ? "task" : "tasks"), false);
            }
        } else if (command.startsWith("todo")) {
            addTodo(getMetadata(command));
            if (!loadFromFile) sayTaskAdded();
        } else if (command.startsWith("deadline")) {
            String[] splitted = getMetadata(command).split(" /by ");
            if (splitted.length != 2) throw new IrisException("deadline should have 2 arguments: a name and a time");
            addDeadline(splitted[0], splitted[1]);
            if (!loadFromFile) sayTaskAdded();
        } else if (command.startsWith("event")) {
            String[] splitted = getMetadata(command).split(" /at ");
            if (splitted.length != 2) throw new IrisException("event should have 2 arguments: a name and a time");
            addEvent(splitted[0], splitted[1]);
            if (!loadFromFile) sayTaskAdded();
        } else {
            throw new IrisException("I'm sorry, but I don't know what that means.");
        }

        if (!loadFromFile && record) appendToFile(String.format("\n%s", command));
    }

    public static void main(String[] args) {
        say("Hello! I'm Iris. What can I do for you?");
        readFromFile();
        String command = prompt();
        while (!command.equals(ENDING_COMMAND)) {
            try {
                handleCommand(command);
            } catch (IrisException exception) {
                sayError(exception);
            }
            command = prompt();
        }

        say("Bye. Hope to see you again soon!");
    }
}
