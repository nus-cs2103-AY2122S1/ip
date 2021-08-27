import java.util.regex.Pattern;

// Makes sense of the user's commands
public class Parser {
    private static boolean isStop = false;
    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static Command parse(String input) throws DukeException{
        Task currTask;
        if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.startsWith("done")) {
            int index = parseDone(input);
            return new DoneCommand(index);
        } else if (input.startsWith("delete")) {
            int index = parseDelete(input);
            return new DeleteCommand(index);
        } else {
            if (!input.startsWith("todo") && !input.startsWith("deadline") && !input.startsWith("event")) {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            Task task;
            if (input.startsWith("todo")) {
                task = parseTodo(input);
            }
            else if (input.startsWith("deadline")) {
                task = parseDeadline(input);
            }
            else {
                task = parseEvent(input);
            }
            return new AddCommand(task);
        }
    }

    public static int parseDone(String input) throws DukeException {
        String numberString = String.valueOf(input.charAt(5));
        if (!pattern.matcher(numberString).matches()) {
            throw new DukeException("Please enter \'done [index of item]\' to mark item as done.");
        }
        int number = Integer.parseInt(String.valueOf(numberString));
        if (number > TaskList.getSize() || number < 0) {
            throw new DukeException("Item does not exist, we cannot mark it as done.");
        }
        Task currTask = TaskList.get(number - 1);
        if (currTask.getStatusIcon().equals(String.valueOf('X'))) {
            throw new DukeException("Item is already marked as done, we cannot mark it as done again.");
        }
        return number - 1;
    }

    public static int parseDelete(String input) throws DukeException {
        // int number = Integer.parseInt(String.valueOf(input.charAt(7)));
        String numberString = String.valueOf(input.charAt(7));
        if (!pattern.matcher(numberString).matches()) {
            throw new DukeException("Please enter \'delete [index of item]\' to mark item as done.");
        }
        int number = Integer.parseInt(String.valueOf(numberString));
        if (number > TaskList.getSize() || number < 0) {
            throw new DukeException("Item does not exist, we cannot delete it.");
        }
        return number - 1;
    }

    public static Task parseTodo(String input) throws DukeException {
        if (input.length() == 4) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        String taskDesc = input.substring(5);
        return new Todo(taskDesc);

    }

    public static Task parseDeadline(String input) throws DukeException {
        if (input.length() == 8) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        int byIndex = input.indexOf("/");
        String by = input.substring(byIndex+4);
        String taskDesc = input.substring(9, byIndex-1);
        return new Deadline(taskDesc, by);
    }

    public static Task parseEvent(String input) throws DukeException {
        if (input.length() == 5) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        }
        int byIndex = input.indexOf("/");
        String by = input.substring(byIndex+4);
        String taskDesc = input.substring(6, byIndex-1);
        return new Event(taskDesc,by);
    }

}