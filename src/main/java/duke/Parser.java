package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import duke.Storage.Storage;

public class Parser {

    public static final String BYE_STRING = "Bye. Hope to see you again soon!";

    /**
     * Loads task once files contents are read raw.
     * @param tasks File contents raw.
     * @return Processed file contents.
     * @throws FileParseErrorException
     */
    public static ArrayList<Task> loadTasks(List<String> tasks) throws FileParseErrorException {
        ArrayList<Task> finalTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            String taskString = tasks.get(i);
            String[] tokens = taskString.split(",");
            if (tokens.length < 3) {
                throw new FileParseErrorException();
            }
            String interpretedString = "";
            Boolean done = false;
            if (tokens[1].equals("0")) {
                done = false;
            } else if (tokens[1].equals("1")) {
                done = true;
            } else {
                throw new FileParseErrorException();
            }
            LocalDateTime localDateTime = LocalDateTime.now();
            Type type;
            if (tokens[0].equals("D") && tokens.length == 4) {
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
            Task task = TaskList.initialiseByType(interpretedString, type, done, localDateTime);
            finalTasks.add(task);
        }
        return finalTasks;
    }

    /**
     * Parses date and returns datetime object.
     * @param datetime Datetime string to parse.
     * @return Datetime object.
     */
    public static LocalDateTime parseDate (String datetime) {
        if (datetime.length() == 10) {
            datetime += " 00:00";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(datetime, formatter);
        return localDateTime;
    }

    /**
     * Assesses the input and activates the necessary response.
     * @param input The string of input command.
     * @throws DukeException Exceptions specific to this chatbot.
     */
    public static String interpretInput(String input, List<Task> tasks) throws DukeException {
        String response = "";
        // Use Java Assertions to check for unacceptable commands
        Boolean valid = true;
        if (input.equals("bye")) {
            response = BYE_STRING;
        } else if (input.equals("list")) {
            response = listResponse(tasks);
        } else if (input.equals("hello")) {
            response = helloResponse();
        } else if (input.startsWith("done ")) {
            response = doneResponse(input, tasks);
        } else if (input.startsWith("todo ")) {
            response = todoResponse(input, tasks);
        } else if (input.startsWith("deadline ")) {
            response = deadlineResponse(input, tasks);
        } else if (input.startsWith("event ")) {
            response = eventResponse(input, tasks)
        } else if (input.startsWith("delete ")) {
            response = deleteResponse(input, tasks);
        } else if (input.startsWith("find ")) {
            response = findResponse(input, tasks);
        } else if (input.startsWith("sort events and deadlines")
                || input.startsWith("sort deadlines and events")) {
            response = sortResponse(tasks);
        } else {
            valid = false;
        }

        assert valid : "COMMAND NOT FOUND";
        writeToStorage(tasks);
        System.out.println(response);
        return response;
    }

    private static void writeToStorage(List<Task> tasks) {
        try {
            Storage.writeToFile(tasks);
        } catch (IOException err) {
            System.out.println(err);
        }
    }

    private static String sortResponse(List<Task> tasks) {
        return duke.TaskList.taskListString(Command.sortTasks(tasks));
    }

    private static String findResponse(String input, List<Task> tasks) {
        List<Task> filteredTasks = Command.findTasks(input.substring(5), tasks);
        return duke.TaskList.taskListString(filteredTasks);
    }

    private static String deleteResponse(String input, List<Task> tasks) {
        Command.deleteTask(Integer.parseInt(input.substring(7)), tasks);
    }

    private static String eventResponse(String input, List<Task> tasks) {
        String task = input.substring(6);
        Type type = Type.EVENT;
        String[] tokens = task.split(" /at ");
        LocalDateTime localDateTime = Parser.parseDate(tokens[1]);
        task = tokens[0];
        return Command.addTask(task, type, localDateTime, tasks);
    }

    private static String deadlineResponse(String input, List<Task> tasks) throws EmptyDeadlineException {
        String testInput = input.replaceAll("\\s+", "");
        if (testInput.equals("deadline")) {
            throw new EmptyDeadlineException();
        }
        String task = input.substring(9);
        Type type = Type.DEADLINE;
        String[] tokens = task.split(" /by ");
        LocalDateTime localDateTime = Parser.parseDate(tokens[1]);
        task = tokens[0];
        return Command.addTask(task, type, localDateTime, tasks);
    }

    private static String todoResponse(String input, List<Task> tasks) throws EmptyTodoException {
        String testInput = input.replaceAll("\\s+", "");
        if (testInput.equals("todo")) {
            throw new EmptyTodoException();
        }
        String task = input.substring(5);
        Type type = Type.TODO;
        LocalDateTime localDateTime = null;
        return Command.addTask(task, type, localDateTime, tasks);
    }

    private static String doneResponse(String input, List<Task> tasks) {
        return Command.doneTask(Integer.parseInt(input.substring(5)), tasks);
    }

    private static String listResponse(List<Task> tasks) {
        return duke.TaskList.taskListString(tasks);
    }

    private static String helloResponse() {
        return "Hello! I'm duke.Duke\n"
                + "What can I do for you?";
    }
}
