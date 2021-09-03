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

            Type type;
            String interpretedString = "";
            Boolean done = false;

            if (tokens[1].equals("0")) {
                done = false;
            } else if (tokens[1].equals("1")) {
                done = true;
            } else {
                throw new FileParseErrorException();
            }

            LocalDateTime localDateTime;
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
        String task;
        Type type;
        LocalDateTime localDateTime;
        String response = "";

        // Use Java Assertions to check for unacceptable commands
        Boolean valid = true;
        if (input.equals("bye")) {
            System.out.println(BYE_STRING);
            response = BYE_STRING;
        } else if (input.equals("list")) {
            System.out.println(duke.TaskList.taskListString(tasks));
            response = duke.TaskList.taskListString(tasks);
        } else if (input.equals("hello")) {
            System.out.println("Hello! I'm duke.Duke\n"
                    + "What can I do for you?");
            response = "Hello! I'm duke.Duke\n"
                    + "What can I do for you?";
        } else if (input.startsWith("done ")) {
            response = Command.doneTask(Integer.parseInt(input.substring(5)), tasks);
        } else if (input.startsWith("todo ")) {
            // Remove all whitespaces to test if it is empty
            String testInput = input.replaceAll("\\s+", "");
            if (testInput.equals("todo")) {
                throw new EmptyTodoException();
            }
            task = input.substring(5);
            type = Type.TODO;
            localDateTime = null;
            response = Command.addTask(task, type, localDateTime, tasks);
        } else if (input.startsWith("deadline ")) {
            String testInput = input.replaceAll("\\s+", "");
            if (testInput.equals("deadline")) {
                throw new EmptyDeadlineException();
            }
            task = input.substring(9);
            type = Type.DEADLINE;
            String[] tokens = task.split(" /by ");
            localDateTime = Parser.parseDate(tokens[1]);
            task = tokens[0];
            response = Command.addTask(task, type, localDateTime, tasks);
        } else if (input.startsWith("event ")) {
            task = input.substring(6);
            type = Type.EVENT;
            String[] tokens = task.split(" /at ");
            localDateTime = Parser.parseDate(tokens[1]);
            task = tokens[0];
            response = Command.addTask(task, type, localDateTime, tasks);
        } else if (input.startsWith("delete ")) {
            response = Command.deleteTask(Integer.parseInt(input.substring(7)), tasks);
        } else if (input.startsWith("find ")) {
            List<Task> filteredTasks = Command.findTasks(input.substring(5), tasks);
            response = duke.TaskList.taskListString(filteredTasks);
        } else if (input.startsWith("sort events and deadlines")
                || input.startsWith("sort deadlines and events")) {
            response = duke.TaskList.taskListString(Command.sortTasks(tasks));
        } else {
            valid = false;
        }

        assert valid : "COMMAND NOT FOUND";

        try {
            Storage.writeToFile(tasks);
        } catch (IOException err) {
            System.out.println(err);
        }
        return response;
    }
}
