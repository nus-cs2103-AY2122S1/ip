import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Parser {
    protected static int parseTaskId(String index) throws UnableToParseException {
        int i;

        try {
            i = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new UnableToParseException("task id");
        }

        return i;
    }

    protected static Event parseEvent(String args) throws InvalidArgumentsException {
        String[] splitArgs = args.split(" /at ");
        if (splitArgs.length != 2) {
            throw new InvalidArgumentsException("event [task] /at [time period]");
        }
        return new Event(splitArgs[0], splitArgs[1]);
    }

    protected static Deadline parseDeadline(String args) throws InvalidArgumentsException {
        String[] splitArgs = args.split(" /by ");
        InvalidArgumentsException invalidArgsException = new InvalidArgumentsException("deadline [task] /by [YYYY-MM-DD]");
        if (splitArgs.length != 2) {
            throw invalidArgsException;
        }
        
        try {
            LocalDate by = LocalDate.parse(splitArgs[1]);
            return new Deadline(splitArgs[0], by);
        } catch (DateTimeParseException e) {
            throw invalidArgsException;
        }
    }
    
    protected static TaskList parseTxtFile(File f) throws FileNotFoundException, UnableToParseException {
            Scanner sc = new Scanner(f);
            ArrayList<Task> tasks = new ArrayList<>();
            try {
                while (sc.hasNext()) {
                    String curr = sc.nextLine();
                    String[] split = curr.split(Pattern.quote(Duke.DELIMITER));

                    if (split.length == 0) {
                        throw new UnableToParseException(f.getAbsolutePath());
                    }

                    String description;
                    boolean isDone;
                    UnableToParseException e = new UnableToParseException("\"" + curr + "\" at " + f.getAbsolutePath());

                    switch (split[0]) {
                    case "T":
                        if (split.length != 3) {
                            throw e;
                        }

                        isDone = parseStringToIsDone(split[1]);
                        description = split[2];
                        Todo todo = new Todo(description);
                        if (isDone) {
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                        break;
                    case "D":
                        if (split.length != 4) {
                            throw e;
                        }

                        isDone = parseStringToIsDone(split[1]);
                        description = split[2];
                        
                        LocalDate by;
                        try {
                            by = LocalDate.parse(split[3]);
                        } catch (DateTimeParseException exception) {
                            throw e;
                        }
                        
                        Deadline deadline = new Deadline(description, by);
                        if (isDone) {
                            deadline.markAsDone();
                        }
                        tasks.add(deadline);
                        break;
                    case "E":
                        if (split.length != 4) {
                            throw e;
                        }

                        isDone = parseStringToIsDone(split[1]);
                        description = split[2];
                        String at = split[3];
                        Event event = new Event(description, at);
                        if (isDone) {
                            event.markAsDone();
                        }
                        tasks.add(event);
                        break;
                    default:
                        throw e;
                    }
                }
            } finally {
                sc.close();
            }

        return new TaskList(tasks);
    }
    
    protected static String parseIsDoneToString(boolean isDone) {
        return isDone ? "1" : "0";
    }
    
    protected static boolean parseStringToIsDone(String s) throws UnableToParseException {
        switch (s) {
        case "1":
            return true;
        case "0":
            return false;
        default:
            throw new UnableToParseException("isDone value: " + s);
        }
    }
}
